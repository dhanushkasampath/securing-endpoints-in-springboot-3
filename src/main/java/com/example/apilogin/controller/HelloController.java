package com.example.apilogin.controller;

import com.example.apilogin.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String greeting(){
        return "Hello, World";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal){
        return "If you see this, then you're logged in as user " + principal.getEmail()
                + " User ID: " + principal.getUserId();
    }

    @GetMapping("/admin")//this will be an endpoint accessible only for admin
    public String admin(@AuthenticationPrincipal UserPrincipal principal){
        return "If you see this, then you're an admin. user " + principal.getEmail()
                + " User ID: " + principal.getUserId();
    }

}
