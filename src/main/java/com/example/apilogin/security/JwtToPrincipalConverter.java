package com.example.apilogin.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.catalina.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {

    public UserPrincipal convert(DecodedJWT jwt){
        return UserPrincipal.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .email(jwt.getClaim("e").asString())
                .authorities(extractAuthorituiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthorituiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if(claim.isNull() || claim.isMissing()){
            return List.of();
        }

        return claim.asList(SimpleGrantedAuthority.class);
    }
}
