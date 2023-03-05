package com.sisk.appoint.datetime;

import java.time.LocalTime;

public class WorkPeriod implements Comparable<WorkPeriod> {

    private LocalTime start;
    private LocalTime end;

    private SessionType session;

    public WorkPeriod(LocalTime start, LocalTime end, SessionType session) {
        this.start = start;
        this.end = end;
        this.session = session;
    }


    @Override
    public int compareTo(WorkPeriod o) {
        return start.compareTo(o.start);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public SessionType getSession() {
        return session;
    }

    public void setSession(SessionType session) {
        this.session = session;
    }


}
