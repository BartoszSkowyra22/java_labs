package com.pollub.lab.service.lab8;

import com.pollub.lab.model.lab8.User;
import com.pollub.lab.repository.lab8.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository);

    @Test
    void testLoadUserByUsername_ShouldReturnUserDetails() {
        User user = new User("john_doe", "encoded_password", "USER");
        when(userRepository.findByUsername("john_doe")).thenReturn(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername("john_doe");

        assertThat(userDetails.getUsername()).isEqualTo("john_doe");
        assertThat(userDetails.getAuthorities()).hasSize(1);
    }
}
