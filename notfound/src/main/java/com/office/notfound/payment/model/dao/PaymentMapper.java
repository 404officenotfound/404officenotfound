package com.office.notfound.payment.model.dao;

import com.office.notfound.payment.model.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentMapper {
    List<PaymentDTO> findAllPayment();

}
