package com.sisk.appoint.service;

import com.sisk.appoint.entity.Booking;
import com.sisk.appoint.model.BookingRequest;
import org.springframework.data.domain.Page;

public interface BookingService {

    Booking saveBooking(BookingRequest request, String email);

    Page<Booking> bookings();
}
