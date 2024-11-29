package com.pollub.lab.controller.lab8;

import com.pollub.lab.payload.lab8.LoginRequest;
import com.pollub.lab.payload.lab8.SignupRequest;
import com.pollub.lab.service.lab8.UserService;
import com.pollub.lab.service.lab8.ValidationRequestService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final ValidationRequestService validationRequestService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        try {
            validationRequestService.validateRequest(bindingResult);
            userService.registerUser(signupRequest.getUsername(), signupRequest.getPassword(), signupRequest.getRole());
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        try {
            validationRequestService.validateRequest(bindingResult);
            String token = userService.loginUser(loginRequest);
            return ResponseEntity.ok()
                    .header("Set-Cookie", "AuthToken=" + token + "; HttpOnly; Path=/; Secure; SameSite=Strict")
                    .body("Login successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("AuthToken", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out successfully!");
    }
}
