package com.abs.auth.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshRequest(
        @NotBlank(message = "refresh token cannot be null")
        String refreshToken
) {
}
