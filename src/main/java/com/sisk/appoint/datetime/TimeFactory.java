package com.sisk.appoint.datetime;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TimeFactory {

    public TreeSet<WorkPeriod> generate(LocalDate date) {

        DayOfWeek dayOfWeek = DayOfWeek.from(date);
        Map<String, Session> sessions = generateSessions(dayOfWeek);

        return sessions.entrySet()
                .stream()
                .flatMap(v -> Stream.of(generateWorkPeriods(v.getValue())))
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private TreeSet<WorkPeriod> generateWorkPeriods(Session session) {
        long remainder = session.sessionDuration() % session.interval().toMinutes();
        WorkPeriod seed = new WorkPeriod(session.start(), session.start().plus(session.interval()), session.type());
        TreeSet<WorkPeriod> workPeriods = Stream
                .iterate(seed, workPeriod -> workPeriod.getEnd().isBefore(session.end()) || workPeriod.getEnd().equals(session.end()),
                        p -> new WorkPeriod(p.getEnd(), p.getEnd().plus(session.interval()), session.type()))
                .collect(Collectors.toCollection(TreeSet::new));

        if (remainder > 0){
            WorkPeriod lastPeriod = workPeriods.pollLast();
            if (lastPeriod != null){
                WorkPeriod copy = new WorkPeriod(lastPeriod.getStart(), lastPeriod.getEnd().plusMinutes(remainder), lastPeriod.getSession());
                workPeriods.add(copy);
            }
        }
        return workPeriods;
    }



    private Map<String, Session> generateSessions(DayOfWeek dayOfWeek) {

        Map<String, Session> sessions = new HashMap<>();

        Session morning = switch (dayOfWeek) {

            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY ->
                    new Session(LocalTime.of(8, 0), LocalTime.of(13, 0), Duration.ofMinutes(60), SessionType.MORNING);

            case FRIDAY -> new Session(LocalTime.of(9, 0), LocalTime.of(13, 0), Duration.ofMinutes(60), SessionType.MORNING);

            default -> throw new IllegalArgumentException("Day of week out of range" + dayOfWeek);
        };
        sessions.put("Morning", morning);
        Session afternoon = switch (dayOfWeek) {

            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY ->
                    new Session(LocalTime.of(14, 0), LocalTime.of(17, 0), Duration.ofMinutes(60), SessionType.AFTERNOON);

            case FRIDAY -> new Session(LocalTime.of(14, 0), LocalTime.of(16, 0), Duration.ofMinutes(60), SessionType.AFTERNOON);

            default -> throw new IllegalArgumentException("Day of week out of range" + dayOfWeek);
        };
        sessions.put("Afternoon", afternoon);

        return sessions;

    }


}
