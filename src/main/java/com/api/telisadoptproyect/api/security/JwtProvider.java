package com.api.telisadoptproyect.api.security;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.api.request.OwnerRequests.PrincipalOwner;
import com.api.telisadoptproyect.library.entity.Owner;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtProvider {
    @Autowired
    private PropertiesConfig propertiesConfig;
    public String generateToken(Authentication authentication, Owner owner){
        PrincipalOwner principalOwner = (PrincipalOwner) authentication.getPrincipal();
        final Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ownerId", owner.getId());
        extraClaims.put("name", principalOwner.getName());
        extraClaims.put("email", principalOwner.getEmail());

        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (long) 7 * 24 * 60 & 60 + 1000))
                .signWith(SignatureAlgorithm.HS256, propertiesConfig.getJwtSecret())
                .compact();
    }

    public String generate2FABearerToken(String email){
        final Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("email", email);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (long) 5 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, propertiesConfig.getJwtSecret())
                .compact();
    }
}
