package com.abs.e_commerce.payment;

import com.abs.e_commerce.notification.NotificationProducer;
import com.abs.e_commerce.notification.PaymentNotificationRequest;
import com.abs.e_commerce.proto.PaymentRequest;
import com.abs.e_commerce.proto.PaymentResponse;
import com.abs.e_commerce.proto.PaymentServiceGrpc.PaymentServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.math.BigDecimal;

@GrpcService
@RequiredArgsConstructor
public class PaymentServiceGrpc extends PaymentServiceImplBase {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public void createPayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        var payment = repository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(request.getOrderRef(), new BigDecimal(request.getAmount().getUnits()), PaymentMethod.valueOf(request.getPaymentMethod().toString()),
                        request.getCustomer().getFirstName(), request.getCustomer().getLastName(), request.getCustomer().getEmail()));

        responseObserver.onNext(PaymentResponse.newBuilder().setId(payment.getId()).build());
        responseObserver.onCompleted();
    }
}
