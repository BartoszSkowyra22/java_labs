package com.pollub.lab.service.lab8;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void testGenerateToken_ShouldReturnValidToken() {
        jwtService.SECRET_KEY = "testsecretkeyfbfgdgdfhgdngdgfdhgdhgksdjvkhsduvhsbhvdkjbfdg";

        String token = jwtService.generateToken("john_doe", "USER");

        Claims claims = Jwts.parser()
                .setSigningKey(jwtService.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        assertThat(claims.getSubject()).isEqualTo("john_doe");
        assertThat(claims.get("role")).isEqualTo("ROLE_USER");
    }

    @Test
    void testExtractUsername_ShouldReturnUsername() {
        jwtService.SECRET_KEY = "testsecretkeyfbfgdgdfhgdngdgfdhgdhgksdjvkhsduvhsbhvdkjbfdg";
        String token = Jwts.builder()
                .setSubject("john_doe")
                .claim("role", "ROLE_USER")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, jwtService.SECRET_KEY)
                .compact();

        String username = jwtService.extractUsername(token);

        assertThat(username).isEqualTo("john_doe");
    }
}
