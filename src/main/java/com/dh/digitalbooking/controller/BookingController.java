package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.Booking;
import com.dh.digitalbooking.security.AuthenticationFacade;
import com.dh.digitalbooking.service.imp.BookingServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingServiceImp bookingServiceImp;
    private final AuthenticationFacade authenticationFacade;

    public BookingController(BookingServiceImp bookingServiceImp, AuthenticationFacade authenticationFacade) {
        this.bookingServiceImp = bookingServiceImp;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingServiceImp.allBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingServiceImp.getBookingById(id));
    }

    @PostMapping
    public ResponseEntity<Booking> saveBooking(
            @RequestBody @Valid Booking booking,
            Authentication authentication){
        UserDetailsDto userDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingServiceImp.saveBooking(booking, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingServiceImp.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Booking> updateBooking(@RequestBody @Valid Booking booking) {
        return ResponseEntity.ok(bookingServiceImp.updateBooking(booking));
    }
}
