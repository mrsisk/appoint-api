package com.sisk.appoint.controller;

import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.model.*;
import com.sisk.appoint.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest body){
        System.out.println("registering");
        return  ResponseEntity.ok().body(authenticationService.register(body, RoleType.USER));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticateRequest body){

        return ResponseEntity.ok(authenticationService.authenticate(body));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshRequest request){
     return  ResponseEntity.ok(authenticationService.refresh(request));
    }

    @GetMapping("/users")
    public ResponseEntity<TestUser> test(){
       return ResponseEntity.ok(new TestUser("sisk"));
    }
}
