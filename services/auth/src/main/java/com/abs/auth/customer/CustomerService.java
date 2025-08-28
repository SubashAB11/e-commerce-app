package com.abs.auth.customer;

import com.abs.auth.exception.BusinessException;
import com.abs.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements UserDetailsService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return this.repository.findByEmailIgnoreCase(userEmail)
                .orElseThrow(() -> new BusinessException(ErrorCode.CUSTOMER_NOT_FOUND, userEmail));
    }

    public void changePassword(ChangePasswordRequest request, String customerId) {
        if(!request.newPassword().equals(request.confirmNewPassword())) throw new BusinessException(ErrorCode.PASSWORD_NOT_MATCH);
        Customer savedCustomer = this.repository.findById(customerId).orElseThrow(() -> new BusinessException(ErrorCode.CUSTOMER_NOT_FOUND, customerId));
        if (!encoder.matches(request.currentPassword(), savedCustomer.getPassword())) throw new BusinessException(ErrorCode.WRONG_CURRENT_PASSWORD, customerId);
        savedCustomer.setPassword(this.encoder.encode(request.newPassword()));
        this.repository.save(savedCustomer);
    }
}
