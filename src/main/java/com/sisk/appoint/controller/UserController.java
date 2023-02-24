package com.sisk.appoint.controller;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Profile;
import com.sisk.appoint.model.ProfileRequest;
import com.sisk.appoint.repository.ProfileRepository;
import com.sisk.appoint.security.AppointUserDetails;
import com.sisk.appoint.service.AppointUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    AppointUserService userService;
    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/userinfo")
    public ResponseEntity<AppointUser> userInfo(@AuthenticationPrincipal AppointUserDetails user){

        return ResponseEntity.ok().body(userService.getUserByEmail(user.getUsername()));
    }

    @PostMapping("/profile")
    public ResponseEntity<Profile> profile(@AuthenticationPrincipal AppointUserDetails user, @RequestBody ProfileRequest profileRequest){
        System.out.println(profileRequest);
        return ResponseEntity.ok(userService.saveProfile(profileRequest, user.getUsername()));
    }


}
