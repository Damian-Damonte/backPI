package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.booking.BookingRequest;
import com.dh.digitalbooking.dto.booking.BookingResponse;
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

    public BookingController(BookingServiceImp bookingServiceImp) {
        this.bookingServiceImp = bookingServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingServiceImp.allBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingServiceImp.getBookingById(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> saveBooking(
                @RequestBody @Valid BookingRequest bookingRequest, Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingServiceImp.saveBooking(bookingRequest, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id, Authentication authentication) {
        bookingServiceImp.deleteBooking(id, authentication);
        return ResponseEntity.noContent().build();
    }
}
