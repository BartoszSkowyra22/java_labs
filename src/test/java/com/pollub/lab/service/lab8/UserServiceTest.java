package com.pollub.lab.service.lab8;

import com.pollub.lab.model.lab8.User;
import com.pollub.lab.payload.lab8.LoginRequest;
import com.pollub.lab.repository.lab8.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final JwtService jwtService = mock(JwtService.class);
    private final UserService userService = new UserService(userRepository, passwordEncoder, jwtService);

    @Test
    void testRegisterUser_ShouldSaveUser() {
        when(userRepository.findByUsername("john_doe")).thenReturn(null);
        when(passwordEncoder.encode("password123")).thenReturn("encoded_password");

        userService.registerUser("john_doe", "password123", "USER");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getUsername()).isEqualTo("john_doe");
    }

    @Test
    void testLoginUser_ShouldReturnToken() {
        User user = new User("john_doe", "encoded_password", "USER");
        when(userRepository.findByUsername("john_doe")).thenReturn(user);
        when(passwordEncoder.matches("password123", "encoded_password")).thenReturn(true);
        when(jwtService.generateToken("john_doe", "USER")).thenReturn("test_token");

        String token = userService.loginUser(new LoginRequest("john_doe", "password123"));

        assertThat(token).isEqualTo("test_token");
    }

    @Test
    void testLoginUser_ShouldThrowExceptionForInvalidCredentials() {
        when(userRepository.findByUsername("john_doe")).thenReturn(null);

        Exception exception = catchException(() ->
                userService.loginUser(new LoginRequest("john_doe", "wrong_password"))
        );

        assertThat(exception).isInstanceOf(BadCredentialsException.class).hasMessageContaining("Invalid username or password");
    }
}
