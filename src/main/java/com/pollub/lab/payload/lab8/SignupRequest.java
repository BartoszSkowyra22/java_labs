package com.pollub.lab.payload.lab8;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 6, max = 30, message = "Nazwa użytkownika musi mieć od 6 do 30 znaków")
    private String username;

    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 6, max = 40, message = "Hasło musi mieć od 6 do 40 znaków")
    private String password;

    @NotBlank(message = "Rola użytkownika nie może być pusta")
    @Pattern(regexp = "ADMIN|USER", message = "Niepoprawna rola użytkownika")
    private String role;
}
