package com.example.blog.component;

import com.example.blog.entity.enums.Roles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    final String secretWord = "ThisIsSecretWord12345678";
    public String generateToken(String username, Roles roles){
        final long expirationTime = 1000*60*60*24L;
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("role",roles.getAuthority());
        return Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
                .signWith(SignatureAlgorithm.HS512, secretWord)
                .setClaims(map)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims body = Jwts
                .parser()
                .setSigningKey(secretWord)
                .parseClaimsJws(token)
                .getBody();
        return (String) body.get("username");
    }
}
