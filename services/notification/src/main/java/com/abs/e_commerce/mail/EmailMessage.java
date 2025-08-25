package com.abs.e_commerce.mail;

import lombok.Getter;

public enum EmailMessage {
    PAYMENT_MESSAGE("payment was succesful"),
    ORDER_MESSAGE("order placed succesfully");

    @Getter
    private final String message;

    EmailMessage(String string) {
        this.message = string;
    }

}
