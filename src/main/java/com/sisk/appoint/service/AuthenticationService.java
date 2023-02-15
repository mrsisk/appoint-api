package com.sisk.appoint.service;

import com.sisk.appoint.model.*;

public interface AuthenticationService {

    MessageResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticateRequest request);

    AuthenticationResponse refresh(RefreshRequest request);


}
