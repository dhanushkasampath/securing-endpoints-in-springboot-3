package com.example.apilogin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    /**
     * This method is used to generate a JWT token
     * the signature part of the jwt token is created by Base64UrlEncoding of header and payload and a
     * secretkey stored in the application.
     * When the client calls comes in the next time with jwt token, spring boot application verify that signature.
     * if that is incorrect authentication failure is sent without allowing to log in
     * @param userId
     * @param email
     * @param roles
     * @return
     */
    public String issue(long userId, String email, List<String> roles){
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e", email)
                .withClaim("a", roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }
}
