package com.sisk.appoint.service;

import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.model.*;

public interface AuthenticationService {

    MessageResponse register(RegisterRequest request, RoleType roleType);

    AuthenticationResponse authenticate(AuthenticateRequest request);

    AuthenticationResponse refresh(RefreshRequest request);


}
