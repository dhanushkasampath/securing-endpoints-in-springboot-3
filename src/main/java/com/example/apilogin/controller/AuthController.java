package com.example.apilogin.controller;

import com.example.apilogin.model.LoginRequest;
import com.example.apilogin.model.LoginResponse;
import com.example.apilogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    //when user successfully logged in he should get a jwt token
    @PostMapping("/auth/login")//we need to give access to this endpoint by security config file
    public LoginResponse login(@RequestBody @Validated LoginRequest request){
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}
