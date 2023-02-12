package com.sisk.appoint.model;

public record AuthenticationResponse(String token, String refresh_token) {
}
