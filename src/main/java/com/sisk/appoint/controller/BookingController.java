package com.sisk.appoint.controller;

import com.sisk.appoint.entity.Booking;
import com.sisk.appoint.model.BookingRequest;
import com.sisk.appoint.security.AppointUserDetails;
import com.sisk.appoint.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    @PostMapping
    ResponseEntity<Booking> save(@AuthenticationPrincipal AppointUserDetails user, @RequestBody BookingRequest request){

        System.out.println(request);
        return ResponseEntity.ok(bookingService.saveBooking(request, user.getUsername()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<Booking>> all(){
        return ResponseEntity.ok(bookingService.bookings());
    }
}
