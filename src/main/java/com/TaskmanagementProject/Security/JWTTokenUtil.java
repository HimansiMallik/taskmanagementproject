package com.TaskmanagementProject.Security;



import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.stereotype.Component;

import com.TaskmanagementProject.Entity.UserAuthentication;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@Component
public class JWTTokenUtil {

    // ✅ Must be at least 256 bits for HS256
    private static final String JWT_SECRET =
            "ThisIsASecretKeyForJWTTokenGenerationThatIsSecure12345";

    private static final long JWT_EXPIRATION = 8_400_000; // ~2.3 hours

    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(UserAuthentication user) {

        return Jwts.builder()
                .setSubject(user.getUserOfficialEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;

        } catch (JwtException | IllegalArgumentException ex){
            return false;
        }
    }
}
