package com.example.apilogin.service;

import com.example.apilogin.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final String EXISTING_EMAIL = "test@gmail.com";
    private static final String ANOTHER_EMAIL = "next@gmail.com";

    public Optional<UserEntity> findByEmail(String email){
        //TODO: Move this to a database
        if(EXISTING_EMAIL.equalsIgnoreCase(email)) {
            var user = new UserEntity();
            user.setId(1L);
            user.setEmail(EXISTING_EMAIL);
            user.setPassword("$2a$12$sGzm5Vzcu5gYJBw25sLGDeK4bEC20AHuzFrumEtopOuawX3omBVZ.");//test
            user.setRole("ROLE_ADMIN");
            user.setExtraInfo("My nice admin");
            return Optional.of(user);

        } else if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
            var user = new UserEntity();
            user.setId(99L);
            user.setEmail(ANOTHER_EMAIL);
            user.setPassword("$2a$12$sGzm5Vzcu5gYJBw25sLGDeK4bEC20AHuzFrumEtopOuawX3omBVZ.");//test
            user.setRole("ROLE_USER");
            user.setExtraInfo("My nice user");
            return Optional.of(user);

        }

        return Optional.empty();

    }
}
