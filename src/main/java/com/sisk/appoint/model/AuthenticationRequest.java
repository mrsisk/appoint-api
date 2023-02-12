package com.sisk.appoint.model;

import jakarta.validation.constraints.Email;

public record AuthenticationRequest(@Email(message = "Invalid email") String email, String password) {
}
