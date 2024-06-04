package com.example.apilogin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties properties;
    /**
     * Algorithm.HMAC256() is the algorithm we used to encode. So we have to use the same algorithm to decode
     * @param token
     * @return
     */
    public DecodedJWT decode(String token){
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))// when decoding the secret-key of the application is used. that also used to generate the token. this is to verify the token is issued by this spring boot application
                .build()
                .verify(token);
    }
}
