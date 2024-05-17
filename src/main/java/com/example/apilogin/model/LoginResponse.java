package com.example.apilogin.model;

import lombok.Builder;
import lombok.Getter;

/**
 * This is the model used to return the access token when user entered correct username and password
 */
@Getter
@Builder
public class LoginResponse {

    private final String accessToken;
}
