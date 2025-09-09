package com.abs.auth.controller;

import com.abs.auth.dto.ChangePasswordRequest;
import com.abs.auth.dto.AuthenticationRequest;
import com.abs.auth.dto.AuthenticationResponse;
import com.abs.auth.dto.RefreshRequest;
import com.abs.auth.dto.RegistrationRequest;
import com.abs.auth.model.Customer;
import com.abs.auth.service.CustomerService;
import com.abs.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Customer Auth", description = "Customer Auth API")
public class AuthController {

    private final CustomerService customerService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(this.authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        this.authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody @Valid RefreshRequest request) {
        return ResponseEntity.ok(this.authService.refreshToken(request));
    }

    @PostMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody @Valid ChangePasswordRequest request, Authentication principal) {
        this.customerService.changePassword(request, ((Customer) principal.getPrincipal()).getId());
    }
}
