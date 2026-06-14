package com.portfolio.taskmanager.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.PublicJwk;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
     private static final String SECRET_KEY="thisismysecretkeywhichistoolongtobecracked123456";
     private static final Long EXPIRATION_TIME=1000L*60*60*24;
     private SecretKey getSigningKey(){
         return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
     }
     public String generateToken(String email){
    return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
            .signWith(getSigningKey())
            .compact();
     }
     public String extractEmail(String token){
         return Jwts.parser()
                 .verifyWith(getSigningKey())
                 .build()
                 .parseSignedClaims(token)
                 .getPayload()
                 .getSubject();
     }
     public boolean isTokenValid(String token){
         try{
             Jwts.parser()
                     .verifyWith(getSigningKey())
                     .build()
                     .parseSignedClaims(token);
             return true;
         }catch(Exception e){
             return false;
         }
     }
}
