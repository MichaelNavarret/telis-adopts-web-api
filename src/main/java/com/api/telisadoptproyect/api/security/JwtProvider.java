package com.api.telisadoptproyect.api.security;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.api.request.OwnerRequests.PrincipalOwner;
import com.api.telisadoptproyect.library.entity.Owner;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtProvider {
    private final List<String> excludeUrls = List.of("/auth/login", "/auth/resend-otp-code");
    @Autowired
    private PropertiesConfig propertiesConfig;
    public String generateToken(Authentication authentication, Owner owner){
        PrincipalOwner principalOwner = (PrincipalOwner) authentication.getPrincipal();
        final Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ownerId", owner.getId());
        extraClaims.put("name", principalOwner.getNickName());
        extraClaims.put("email", principalOwner.getEmail());

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(principalOwner.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (long) 840000000 + 1000))
                .signWith(SignatureAlgorithm.HS256, propertiesConfig.getJwtSecret())
                .compact();
    }

    public String generate2FABearerToken(String email){
        final Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("onlyLogin", true);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (long) 840000 + 1000))
                .signWith(SignatureAlgorithm.HS256, propertiesConfig.getJwtSecret())
                .compact();
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(propertiesConfig.getJwtSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(propertiesConfig.getJwtSecret()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            throw new BadRequestException("Invalid JWT token");
        }catch (UnsupportedJwtException e){
            throw new BadRequestException("Unsupported JWT token");
        }catch (ExpiredJwtException e){
            throw new BadRequestException("Expired JWT token");
        }catch (IllegalArgumentException e){
            throw new BadRequestException("JWT claims string is empty");
        }catch (SignatureException e){
            throw new BadRequestException("Invalid JWT signature");
        }
    }

    private boolean isOnlyLoginToken(String token){
        Object object = (Jwts.parser().setSigningKey(propertiesConfig.getJwtSecret()).parseClaimsJws(token).getBody().get("onlyLogin"));
        if(object != null){
            return (boolean) object;
        }
        return false;
    }

    public boolean validateIfTokenIsOnlyForLogin(String token, HttpServletRequest httpRequest) {
        boolean onExcludeUrl = excludeUrls.contains(httpRequest.getRequestURI());
        return !isOnlyLoginToken(token) || onExcludeUrl;
    }
}
