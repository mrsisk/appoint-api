package com.sisk.appoint.service;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.model.AuthenticationResponse;
import com.sisk.appoint.model.MessageResponse;
import com.sisk.appoint.model.AuthenticationRequest;
import com.sisk.appoint.model.RefreshRequest;
import com.sisk.appoint.repository.RoleRepository;
import com.sisk.appoint.security.AppointUserDetails;
import com.sisk.appoint.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private AppointUserService userService;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public MessageResponse register(AuthenticationRequest request) {
        AppointUser user = new AppointUser(request.email(), encoder.encode(request.password()));
        //TODO create custom exception
        Role role = roleRepository.findByName(RoleType.USER).orElseThrow(()-> new RuntimeException("Role note found"));
        user.addRole(role);
        userService.saveUser(user);
        return new MessageResponse("User successfully registered");
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        AppointUser user = userService.getUserByEmail(request.email());
        AppointUserDetails appointUserDetails = new AppointUserDetails(user);
        String token  = jwtService.generateToken(appointUserDetails);
        String refresh = jwtService.generateRefreshToken(appointUserDetails);
        return new AuthenticationResponse(token, refresh);
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest request) {

        String subject = jwtService.getUsernameFromToken(request.refresh_token());

        AppointUser user = userService.getUserByEmail(subject);
        AppointUserDetails appointUserDetails = new AppointUserDetails(user);
        if (jwtService.validateToken(request.refresh_token(), appointUserDetails)){
            String token  = jwtService.generateToken(appointUserDetails);
            String refresh = jwtService.generateRefreshToken(appointUserDetails);
            return new AuthenticationResponse(token, refresh);
        }

        throw new RuntimeException("INVALID JWT");
    }
}
