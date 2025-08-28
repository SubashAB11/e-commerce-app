package com.abs.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND","customer not found with email: %s", HttpStatus.NOT_FOUND),
    PASSWORD_NOT_MATCH("PASSWORD_NOT_MATCH","password does not match for customer", HttpStatus.BAD_REQUEST),
    WRONG_CURRENT_PASSWORD("WRONG_CURRENT_PASSWORD", "the current password is wrong", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS","email already exists: %s" , HttpStatus.BAD_REQUEST);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;
}
