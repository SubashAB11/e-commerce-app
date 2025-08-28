package com.abs.auth.auth;

import com.abs.auth.customer.Customer;
import com.abs.auth.customer.CustomerMapper;
import com.abs.auth.customer.CustomerRepository;
import com.abs.auth.exception.BusinessException;
import com.abs.auth.exception.ErrorCode;
import com.abs.auth.security.JWTService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder encoder;

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        Customer customer = (Customer) authenticate.getPrincipal();
        String accessToken = this.jwtService.generateAccessToken(customer.getUsername());
        String refreshToken = this.jwtService.generateRefreshToken(customer.getUsername());
        String tokenType = "Bearer";

        return new AuthenticationResponse(accessToken,refreshToken,tokenType);
    }

    @Transactional
    public void register(RegistrationRequest request) {
        if(this.customerRepository.existsByEmailIgnoreCase(request.email())) throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS, request.email());
        if(request.password().isBlank() || !request.password().equals(request.confirmPassword())) throw new BusinessException(ErrorCode.PASSWORD_NOT_MATCH);
        Customer customer = this.customerMapper.toCustomer(request);
        customer.setPassword(encoder.encode(customer.getPassword()));
        log.debug("saving user to db {}", customer);
        this.customerRepository.save(customer);
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) {
        return this.jwtService.refreshAccessToken(request.refreshToken());
    }
}
