package com.example.apilogin.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * This is an entity class which maps to a db table
 */
@Getter
@Setter
public class UserEntity {

    private Long id;
    private String email;
    private String password;
    private String role;
    private String extraInfo;
}
