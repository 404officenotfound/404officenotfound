package com.office.notfound.payment.model.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface PortOneService {
    String requestPayment(String merchantUid, int amount, String paymentMethod);

    String cancelPayment(String impUid, int paymentAmount);

    String getAccessToken();

    Map<String, Object> getPaymentData(String impUid, String token);
}
