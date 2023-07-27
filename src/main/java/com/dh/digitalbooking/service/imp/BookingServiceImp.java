package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.booking.BookingRequest;
import com.dh.digitalbooking.dto.booking.BookingResponse;
import com.dh.digitalbooking.dto.user.UserDetailsSlim;
import com.dh.digitalbooking.entity.Booking;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.ForbiddenException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Product;
import com.dh.digitalbooking.mapper.BookingMapper;
import com.dh.digitalbooking.repository.BookingRepository;
import com.dh.digitalbooking.service.AuthenticationUserService;
import com.dh.digitalbooking.service.BookingService;
import com.dh.digitalbooking.service.ProductService;
import com.dh.digitalbooking.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService {
    private final BookingRepository bookingRepository;
    private final ProductService productService;
    private final UserService userService;
    private final BookingMapper bookingMapper;
    private final AuthenticationUserService authenticationUserService;

    public BookingServiceImp(BookingRepository bookingRepository, ProductService productService, UserService userService, BookingMapper bookingMapper, AuthenticationUserService authenticationUserService) {
        this.bookingRepository = bookingRepository;
        this.productService = productService;
        this.userService = userService;
        this.bookingMapper = bookingMapper;
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    public List<BookingResponse> allBookings() {
        return bookingRepository.findAll().stream().map(bookingMapper::bookingToBookingResponse).toList();
    }

    @Override
    public List<BookingResponse> getBookingsByUserId(Long id, Authentication authentication) {
        UserDetailsSlim userDetails = authenticationUserService.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!id.equals(userDetails.id()))
                throw new ForbiddenException("You cannot see the bookings of this user");
        }

        return bookingRepository.getBookingByUserId(id).stream().map(bookingMapper::bookingToBookingResponse).toList();
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        return bookingMapper.bookingToBookingResponse(existByIdValidation(id));
    }

    @Override
    @Transactional
    public BookingResponse saveBooking(BookingRequest bookingRequest, Authentication authentication) {
        datesValidation(bookingRequest.checkIn(), bookingRequest.checkOut());
        checkAvailability(bookingRequest);

        Product product = productService.existById(bookingRequest.product().id());
        User user = userService.existById(authenticationUserService.getUserDetailsFromAuthentication(authentication).id());
        Booking booking = bookingMapper.bookingRequestToBooking(bookingRequest);
        booking.setTotal(getTotal(bookingRequest.checkIn(), bookingRequest.checkOut(), product.getPricePerNight()));
        booking.setUser(user);

        product.getBookings().add(booking);
        user.setCity(bookingRequest.userCity());

        return bookingMapper.bookingToBookingResponse(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public void deleteBooking(Long id, Authentication authentication) {
        Booking booking = existByIdValidation(id);
        UserDetailsSlim userDetails = authenticationUserService.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!booking.getUser().getId().equals(userDetails.id()))
                throw new ForbiddenException("You cannot modify this booking as it does not belong to you");
        }
        bookingRepository.deleteById(id);
    }

    public Booking existByIdValidation(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Booking with id " + id + " not found"));
    }

    private void checkAvailability(BookingRequest bookingRequest) {
        boolean datesAvailable = bookingRepository.existsBookingsByDates(bookingRequest.product().id(),
                bookingRequest.checkIn(),bookingRequest.checkOut());
        if (!datesAvailable) throw new BadRequestException("Product not available for the entered dates");
    }

    private void datesValidation(LocalDate checkIn, LocalDate checkOut) {
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
