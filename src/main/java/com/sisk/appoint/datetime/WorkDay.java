package com.sisk.appoint.datetime;

import java.util.TreeSet;

public record WorkDay(String dayOfWeek, String dayOfMonth, String fullDate, TreeSet<WorkPeriod> workPeriods) {
}
