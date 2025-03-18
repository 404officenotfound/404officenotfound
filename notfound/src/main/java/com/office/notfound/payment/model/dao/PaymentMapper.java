package com.office.notfound.payment.model.dao;

import com.office.notfound.payment.model.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentMapper {
    List<PaymentDTO> searchPayment(@Param("memberCode") Integer memberCode,
                                   @Param("paymentDate") String paymentDate,
                                   @Param("startDate") String startDate,
                                   @Param("endDate") String endDate);
    List<PaymentDTO> findAllPayments(@Param("memberCode") Integer memberCode);

    void insertPayment(PaymentDTO payment);

    void insertReservationPayment(int reservationCode, int paymentCode);

    int getReservationPrice(int reservationCode);

    void updatePaymentStatus(@Param("paymentCode") int paymentCode, @Param("paymentStatus") String paymentStatus);

    PaymentDTO findPaymentById(int paymentCode);

    int getReservationCodeByPayment(@Param("paymentCode") int paymentCode);
}
