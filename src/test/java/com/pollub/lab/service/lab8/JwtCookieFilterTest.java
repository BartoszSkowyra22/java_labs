package com.pollub.lab.service.lab8;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

import static org.mockito.Mockito.*;

class JwtCookieFilterTest {

    private final JwtService jwtService = mock(JwtService.class);
    private final CustomUserDetailsService userDetailsService = mock(CustomUserDetailsService.class);
    private final JwtCookieFilter jwtCookieFilter = new JwtCookieFilter(jwtService, userDetailsService);
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final FilterChain filterChain = mock(FilterChain.class);

    @Test
    void testDoFilterInternal_ShouldAuthenticateUser() throws ServletException, IOException {
        Cookie[] cookies = {new Cookie("AuthToken", "test_token")};
        when(request.getCookies()).thenReturn(cookies);
        when(jwtService.extractUsername("test_token")).thenReturn("john_doe");
        when(jwtService.extractRole("test_token")).thenReturn("ROLE_USER");
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("john_doe")
                .password("password")
                .roles("USER")
                .build();
        when(userDetailsService.loadUserByUsername("john_doe")).thenReturn(userDetails);

        jwtCookieFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}
