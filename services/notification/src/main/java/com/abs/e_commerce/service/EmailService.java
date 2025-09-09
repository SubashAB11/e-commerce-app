package com.abs.e_commerce.service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.abs.e_commerce.dto.EmailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.abs.e_commerce.dto.Product;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sentPaymentSuccessful(String email, String customerName, BigDecimal amount, String orderRef)
            throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("subash@abs.com");

        final String message = EmailMessage.PAYMENT_MESSAGE.getMessage();

        messageHelper.setSubject(message);

        String body = "<h2>Payment Successful</h2>" +
                "<p>Dear " + customerName + ",</p>" +
                "<p>Your payment of <b>" + amount + "</b> for order <b>" + orderRef + "</b> was successful.</p>";

        try {
            messageHelper.setText(body, true);
            messageHelper.setTo(email);
            mailSender.send(mimeMessage);
            log.info("mail sent for payment successfully to " + email);
        } catch (Exception e) {
            log.warn("cannot send email to " + email + " " + e.getMessage());
        }
    }

    @Async
    public void sentOrderConfirmation(String email, String customerName, BigDecimal amount, String orderRef,
            List<Product> products) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("subash@abs.com");

        final String message = EmailMessage.ORDER_MESSAGE.getMessage();

        messageHelper.setSubject(message);

        StringBuilder sb = new StringBuilder();
        sb.append("<h2>Order Confirmation</h2>")
                .append("<p>Dear ").append(customerName).append(",</p>")
                .append("<p>Your order ").append(orderRef)
                .append(" with amount <b>").append(amount).append("</b> includes:</p><ul>");
        for (Product p : products) {
            sb.append("<li>").append(p.name())
                    .append(" - Qty: ").append(p.quantity())
                    .append("</li>");
        }
        sb.append("</ul>");

        try {
            messageHelper.setText(sb.toString(), true);
            messageHelper.setTo(email);
            mailSender.send(mimeMessage);
            log.info("mail sent for order successfully to " + email);
        } catch (Exception e) {
            log.warn("cannot send email to " + email + " " + e.getMessage());
        }
    }
}
