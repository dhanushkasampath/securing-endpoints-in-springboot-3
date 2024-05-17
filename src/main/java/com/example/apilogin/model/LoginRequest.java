package com.example.apilogin.model;

import lombok.Builder;
import lombok.Getter;

/**
 * This is the model used to get email and password from user as an input
 */
@Getter
@Builder
public class LoginRequest {

    private String email;
    private String password;
}
