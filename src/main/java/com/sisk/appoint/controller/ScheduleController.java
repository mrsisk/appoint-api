package com.sisk.appoint.controller;

import com.sisk.appoint.datetime.DateFactory;
import com.sisk.appoint.datetime.WorkDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private DateFactory dateFactory;

    @GetMapping("/days")
    public ResponseEntity<List<WorkDay>> workDays(){
        return ResponseEntity.ok(dateFactory.workingDays(7));
    }
}
