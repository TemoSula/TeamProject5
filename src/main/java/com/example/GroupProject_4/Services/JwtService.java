package com.example.GroupProject_4.Services;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

   /* String securityKey = " ";


    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator generateKey = KeyGenerator.getInstance("hmacSHA256");
        SecretKey secretKey = generateKey.generateKey();
        byte[] key = secretKey.getEncoded();
        securityKey = Base64.getEncoder().encodeToString(key);
    }*/

    private String securityKey = "ksjkdfjlksdjlfkjskldfjklsjdlkfjksjdf";


    public String generateToken(String username)
    {
        return Jwts.builder()
                .claim("username",username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 7 * 24 * 60 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(securityKey.getBytes()))
                .compact();
    }

    public Claims parseToken(String token)
    {

        Jws<Claims> getClaims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(securityKey.getBytes()))
                .build().parseSignedClaims(token);
         Claims payload = getClaims.getPayload();
         return payload;
    }


    public boolean isExpirationToken(String token)
    {
        Date expired = parseToken(token).getExpiration();
        return expired.before(new Date(System.currentTimeMillis()));
    }



}
