package com.abs.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank(message = "email cannot be blank")
        @Email(message = "email format is invalid")
        String email,
        @NotBlank(message = "password cannot be blank")
        @Size(min = 8, message = "password length has to be minimum 8")
        String password,
        @NotBlank(message = "password cannot be blank")
        @Size(min = 8, message = "password length has to be minimum 8")
        String confirmPassword
) {
}
