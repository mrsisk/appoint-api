package com.sisk.appoint.service;

import com.sisk.appoint.model.AuthenticationResponse;
import com.sisk.appoint.model.MessageResponse;
import com.sisk.appoint.model.AuthenticationRequest;
import com.sisk.appoint.model.RefreshRequest;

public interface AuthenticationService {

    MessageResponse register(AuthenticationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refresh(RefreshRequest request);


}
