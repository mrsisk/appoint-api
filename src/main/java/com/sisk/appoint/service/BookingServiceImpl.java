package com.sisk.appoint.service;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Booking;
import com.sisk.appoint.model.BookingRequest;
import com.sisk.appoint.repository.AppointUserRepository;
import com.sisk.appoint.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{

    @Autowired
    AppointUserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;
    @Override
    public Booking saveBooking(BookingRequest request, String email) {
        AppointUser user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));

        Booking booking = new Booking(request.start(), request.end(), request.description(), request.category(), request.location());
        booking.setUser(user);
        return  bookingRepository.save(booking);
    }

    @Override
    public Page<Booking> bookings() {
        return bookingRepository.findAll(PageRequest.of(0, 5));
    }
}
