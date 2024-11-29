package com.pollub.lab.service.lab8;

import com.pollub.lab.model.lab8.User;
import com.pollub.lab.payload.lab8.LoginRequest;
import com.pollub.lab.repository.lab8.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void registerUser(String username, String password, String role) {
        if (getUserByUsername(username) != null) {
            throw new RuntimeException("Username already exists.");
        }
        User user = new User(
                username,
                passwordEncoder.encode(password),
                role
        );
        userRepository.save(user);
    }

    public String loginUser(LoginRequest loginRequest) {
        User user = getUserByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password!");
        }
        return jwtService.generateToken(loginRequest.getUsername(), user.getRole());
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
