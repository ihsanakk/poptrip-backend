package com.poptrip.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMS}")
    private int jwtExpirationMS;


    public String generateJWT(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMS))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String extractUsernameFromJWT(String jwt){

        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

    public boolean validateJWT(String jwt){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT signature: "+e.getMessage());
        }catch (MalformedJwtException e){
            logger.error("Malformed JWT: " + e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired JWT: "+e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT: "+ e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("Illegal Argument: "+ e.getMessage());
        }

        return false;
    }

}
