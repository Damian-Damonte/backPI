package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> allBookings();
    Booking getBookingById(Long id);
    Booking saveBooking(Booking booking, UserDetailsDto userDetailsDto);
    void deleteBooking(Long id);
    Booking updateBooking(Booking booking);
}
