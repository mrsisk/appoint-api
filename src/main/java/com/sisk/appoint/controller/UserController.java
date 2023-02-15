package com.sisk.appoint.controller;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.security.AppointUserDetails;
import com.sisk.appoint.service.AppointUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    AppointUserService userService;
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<TestUser> hello(@AuthenticationPrincipal AppointUserDetails user){

        System.out.println("principal is " + user);
        System.out.println("p " + user.getAuthorities());
       // userService.saveUserRole(user.getEmail(), role.getName());
        return ResponseEntity.ok(new TestUser("Mary"));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<AppointUser> userInfo(@AuthenticationPrincipal AppointUserDetails user){

        return ResponseEntity.ok().body(userService.getUserByEmail(user.getUsername()));
    }
}
