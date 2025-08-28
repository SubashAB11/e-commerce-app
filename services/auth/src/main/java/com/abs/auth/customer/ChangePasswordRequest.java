package com.abs.auth.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "currentPassword cannot be blank")
        @Size(min = 8, message = "currentPassword length has to be minimum 8")
        String currentPassword,
        @NotBlank(message = "newPassword cannot be blank")
        @Size(min = 8, message = "newPassword length has to be minimum 8")
        String newPassword,
        @NotBlank(message = "confirmNewPassword cannot be blank")
        @Size(min = 8, message = "confirmNewPassword length has to be minimum 8")
        String confirmNewPassword
) {
}
