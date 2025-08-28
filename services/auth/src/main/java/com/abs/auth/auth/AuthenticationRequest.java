package com.abs.auth.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "email cannot be blank")
        @Email(message = "email format is invalid")
        String email,
        @NotBlank(message = "password cannot be blank")
        String password
) {
}
