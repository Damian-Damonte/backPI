package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.booking.BookingRequest;
import com.dh.digitalbooking.dto.booking.BookingResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookingService {
    List<BookingResponse> allBookings();
    List<BookingResponse> getBookingsByUserId(Long id, Authentication authentication);
    BookingResponse getBookingById(Long id);
    BookingResponse saveBooking(BookingRequest bookingRequest, Authentication authentication);
    void deleteBooking(Long id, Authentication authentication);
}
