package com.office.notfound.payment.model.service;

import org.springframework.stereotype.Service;

@Service
public interface PortOneService {
    String requestPayment(String merchantUid, int amount, String paymentMethod);

    String cancelPayment(String impUid, int paymentAmount);
}
