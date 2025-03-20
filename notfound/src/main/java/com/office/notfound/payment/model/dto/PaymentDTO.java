package com.office.notfound.payment.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentDTO {

    private int paymentCode;
    private int memberCode;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private int paymentAmount;
    private String paymentStatus;
    private String impUid;
    private String merchantUid;
    private List<ReservationPayment> reservations;
    private String apiParm;

    public void parseJson() {
        if (this.reservations != null && !this.reservations.isEmpty()) {
            for (ReservationPayment res : this.reservations) {
                System.out.println("예약 코드: " + res.getReservationCode() + ", 가격: " + res.getPrice());
            }
        }
    }

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentCode, int memberCode, LocalDateTime paymentDate, String paymentMethod, int paymentAmount, String paymentStatus, String impUid, String merchantUid, List<ReservationPayment> reservations, String apiParm) {
        this.paymentCode = paymentCode;
        this.memberCode = memberCode;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
        this.reservations = reservations;
        this.apiParm = apiParm;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
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

    public String getImpUid() {
        return impUid;
    }

    public void setImpUid(String impUid) {
        this.impUid = impUid;
    }

    public String getMerchantUid() {
        return merchantUid;
    }

    public void setMerchantUid(String merchantUid) {
        this.merchantUid = merchantUid;
    }

    public List<ReservationPayment> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationPayment> reservations) {
        this.reservations = reservations;
    }

    public String getApiParm() {
        return apiParm;
    }

    public void setApiParm(String apiParm) {
        this.apiParm = apiParm;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentCode=" + paymentCode +
                ", memberCode=" + memberCode +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", impUid='" + impUid + '\'' +
                ", merchantUid='" + merchantUid + '\'' +
                ", reservations=" + reservations +
                ", apiParm='" + apiParm + '\'' +
                '}';
    }
}