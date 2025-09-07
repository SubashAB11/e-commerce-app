package com.abs.e_commerce.payment;

import com.abs.e_commerce.proto.PaymentRequest;
import com.google.type.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        Money amount = request.getAmount();
        return Payment.builder()
                .amount(new BigDecimal(amount.getUnits()))
                .paymentMethod(PaymentMethod.valueOf(request.getPaymentMethod().toString()))
                .orderId(request.getOrderId())
                .build();
    }

}
