package com.sisk.appoint.model;

import com.sisk.appoint.entity.Category;

import java.time.ZonedDateTime;

public record BookingRequest(ZonedDateTime start, ZonedDateTime end, String description, Category category, String location) {
}
