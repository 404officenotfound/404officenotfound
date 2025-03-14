package com.office.notfound.payment.model.dto;

import java.time.LocalDateTime;

public class PaymentDTO {

    private int paymentCode;
    private int reservationCode;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private int paymentAmount;
    private String paymentStatus;
    private int memberCode;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentCode, int reservationCode, LocalDateTime paymentDate, String paymentMethod, int paymentAmount, String paymentStatus, int memberCode) {
        this.paymentCode = paymentCode;
        this.reservationCode = reservationCode;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.memberCode = memberCode;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public int getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(int reservationCode) {
        this.reservationCode = reservationCode;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentCode=" + paymentCode +
                ", reservationCode=" + reservationCode +
                ", reservationTime=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", memberCode=" + memberCode +
                '}';
    }
}
