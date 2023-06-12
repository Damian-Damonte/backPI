package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.booking.BookingRequest;
import com.dh.digitalbooking.dto.booking.BookingResponse;
import com.dh.digitalbooking.entity.Booking;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BookingMapper {
    BookingResponse bookingToBookingResponse(Booking booking);
    Booking bookingRequestToBooking(BookingRequest bookingRequest);
}
