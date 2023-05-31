package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.Booking;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.repository.BookingRepository;
import com.dh.digitalbooking.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final ProductoServiceImp productoServiceImp;
    private final UserServiceImp usuarioServiceImp;

    public BookingServiceImp(BookingRepository bookingRepository, ProductoServiceImp productoServiceImp, UserServiceImp usuarioServiceImp) {
        this.bookingRepository = bookingRepository;
        this.productoServiceImp = productoServiceImp;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @Override
    public List<Booking> allBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    @Transactional
    public Booking saveBooking(Booking booking, UserDetailsDto userDetailsDto) {
        Long bookingUserId = booking.getUser().getId();

        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!bookingUserId.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("The provided user information does not match the currently authenticated user");
        }

        checkAvailability(booking, true);
        return getBooking(booking);
    }

    @Override
    @Transactional
    public void deleteBooking(Long id) {
        existByIdValidation(id);
        bookingRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Booking updateBooking(Booking booking) {
        existByIdValidation(booking.getId());
        checkAvailability(booking, false);
        return getBooking(booking);
    }

    private Booking getBooking(Booking booking) {
        datesValidation(booking);
        Producto product = productoServiceImp.existByIdValidation(booking.getProduct().getId());
        User user = usuarioServiceImp.existByIdValidation(booking.getUser().getId());

        booking.setTotal(getTotal(booking.getCheckIn(), booking.getCheckOut(), product.getPrecioPorNoche()));

        product.getBookings().add(booking);
        booking.setProduct(product);

        user.setCity(booking.getUserCity());
        user.getBookings().add(booking);
        booking.setUser(user);

        return bookingRepository.save(booking);
    }

    public Booking existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Booking id is required");
        return bookingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Booking with id " + id + " not found"));
    }

    private void checkAvailability(Booking booking, boolean save) {
        int count = bookingRepository.countBookingsByDates(
                booking.getProduct().getId(),
                booking.getCheckIn(),
                booking.getCheckOut()
        );
        if (count > 1)
            throw new BadRequestException("Product not available for the entered dates");
        if (save && count > 0)
            throw new BadRequestException("Product not available for the entered dates");
        if (!save && count == 1) {
            Booking bookingByDates = bookingRepository.findBookingsByDates(
                    booking.getProduct().getId(),
                    booking.getCheckIn(),
                    booking.getCheckOut()
            );
            if (!(bookingByDates.getId().equals(booking.getId())))
                throw new BadRequestException("Product not available for the entered dates");
        }
    }

    private void datesValidation(Booking booking) {
        LocalDate checkIn = booking.getCheckIn();
        LocalDate checkOut = booking.getCheckOut();

        if (checkIn.isAfter(checkOut))
            throw new BadRequestException("The checkout date must be after the check-in date");
    }

    private BigDecimal getTotal(LocalDate checkin, LocalDate checkout, BigDecimal precio) {
        if (checkin.isEqual(checkout)) {
            return precio;
        } else {
            long daysBetween = ChronoUnit.DAYS.between(checkin, checkout);
            return BigDecimal.valueOf(daysBetween).multiply(precio);
        }
    }
}
