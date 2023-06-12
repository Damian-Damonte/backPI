package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.booking.BookingRequest;
import com.dh.digitalbooking.dto.booking.BookingResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookingService {
    List<BookingResponse> allBookings();
    BookingResponse getBookingById(Long id);
    BookingResponse saveBooking(BookingRequest bookingRequest, UserDetailsDto userDetailsDto);
    void deleteBooking(Long id, UserDetailsDto userDetailsDto);
}
