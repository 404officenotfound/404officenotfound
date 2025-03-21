package com.office.notfound.payment.model.dao;

import com.office.notfound.payment.model.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaymentMapper {
    List<PaymentDTO> searchPayment(@Param("memberCode") Integer memberCode,
                                   @Param("paymentCode") Integer paymentCode,
                                   @Param("paymentDate") String paymentDate,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate);

    List<PaymentDTO> findAllPayments(@Param("memberCode") Integer memberCode);

    void updatePaymentStatus(@Param("paymentCode") int paymentCode, @Param("paymentStatus") String paymentStatus);

    PaymentDTO findPaymentById(int paymentCode);

    List<Integer> getReservationCodesByPayment(@Param("paymentCode") int paymentCode);

    void insertPayment(PaymentDTO paymentDTO);

    void insertReservationPayment(Map<String, Integer> paramMap);

    List<PaymentDTO> searchAdminPayment(@Param("paymentCode") Integer paymentCode,
                                        @Param("memberCode") Integer memberCode,
                                        @Param("paymentDate") String paymentDate,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);
}

