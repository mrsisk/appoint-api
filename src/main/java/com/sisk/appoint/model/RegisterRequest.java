package com.sisk.appoint.model;

import com.sisk.appoint.utils.ValidPassword;
import jakarta.validation.constraints.Email;

public record RegisterRequest(@Email(message = "Invalid email") String email, @ValidPassword String password) {
}
