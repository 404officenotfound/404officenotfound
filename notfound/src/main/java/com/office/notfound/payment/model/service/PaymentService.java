package com.office.notfound.payment.model.service;

import com.office.notfound.payment.model.dao.PaymentMapper;
import com.office.notfound.payment.model.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private  final PaymentMapper paymentMapper;

    @Autowired
    public PaymentService(PaymentMapper paymentMapper) {this.paymentMapper = paymentMapper;}

    public List<PaymentDTO> findAllPayment() { return paymentMapper.findAllPayment();}

}


