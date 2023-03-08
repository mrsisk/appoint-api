package com.sisk.appoint.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public record Session(ZonedDateTime start, ZonedDateTime end, Duration interval, SessionType type) {

    public long sessionDuration(){
        return start.until(end, ChronoUnit.MINUTES);
    }

}
