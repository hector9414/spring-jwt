package com.example.sprinsecurityjwt.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {


    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    // Create JWT Session Token
    public String generateJwtToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get Signature Key


    //Validate Token
    public Boolean isTokenValid(String token){

        try {
            parseJwtToken(token);
            return true;
        }catch (Exception e){
            log.error("Token invalido ".concat(e.getMessage()));
            return false;
        }

    }

    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }
    public String getUserFromToken(String token) {
        Claims claims = parseJwtToken(token);
        return claims.getSubject();
    }

    public Claims parseJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(getSignatureKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }



}
