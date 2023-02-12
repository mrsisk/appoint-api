package com.sisk.appoint.controller;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.security.AppointUserDetails;
import com.sisk.appoint.service.AppointUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    AppointUserService userService;
    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal AppointUserDetails user){

        System.out.println("principal is " + user.getUsername());
       // userService.saveUserRole(user.getEmail(), role.getName());
        return "Hello sisk";
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppointUser>> hie(){
        List<AppointUser> list = userService.getUsers();
        System.out.println("geting users");
        System.out.println(list);
        return ResponseEntity.ok().body(list);
    }
}
