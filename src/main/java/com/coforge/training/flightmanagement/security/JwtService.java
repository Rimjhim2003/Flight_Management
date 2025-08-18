package com.coforge.training.flightmanagement.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;


@Service
public class JwtService {

    private static final String SECRET = "LSIyX1mEv9+lx9QTI7f5RPuaQ6JP6CLS7ksrA6yJXl+QZyfN3fDi0miLxjqxOef/Wf35kVQwC5YWUqB8ymS/8A==";

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
