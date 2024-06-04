package com.example.apilogin.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "customActuator")
public class CustomActuator {

    @ReadOperation
    public String currentDbDetails(){
        return "Return current DB status of the application";
    }

}