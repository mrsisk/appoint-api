package com.sisk.appoint.controller;

import com.sisk.appoint.model.AuthenticationResponse;
import com.sisk.appoint.model.MessageResponse;
import com.sisk.appoint.model.AuthenticationRequest;
import com.sisk.appoint.model.RefreshRequest;
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
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody AuthenticationRequest body){
        System.out.println("registering");
        return  ResponseEntity.ok().body(authenticationService.register(body));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest body){

        return ResponseEntity.ok(authenticationService.authenticate(body));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody RefreshRequest request){
     return  ResponseEntity.ok(authenticationService.refresh(request));
    }
}
