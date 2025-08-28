package com.abs.auth.exception;

public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;
    private final Object[] args;

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(getFormattedMessage(errorCode, args));
        this.errorCode = errorCode;
        this.args = args;
    }

    private static String getFormattedMessage(ErrorCode errorCode, Object[] args) {
        if(args != null && args.length > 0) return String.format(errorCode.getDefaultMessage(),args);
        return errorCode.getDefaultMessage();
    }
}
