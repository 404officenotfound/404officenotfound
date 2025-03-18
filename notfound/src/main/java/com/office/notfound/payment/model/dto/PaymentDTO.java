package com.office.notfound.payment.model.dto;

import java.time.LocalDateTime;

public class PaymentDTO {

    private int paymentCode;
    private int memberCode;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private int PaymentAmount;
    private String paymentStatus;
    private String impUid;
    private String merchantUid;
    private String apiParm;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentCode, int memberCode, LocalDateTime paymentDate, String paymentMethod, int paymentAmount, String paymentStatus, String impUid, String merchantUid, String apiParm) {
        this.paymentCode = paymentCode;
        this.memberCode = memberCode;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        PaymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
        this.impUid = impUid;
        this.merchantUid = merchantUid;
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
        return PaymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        PaymentAmount = paymentAmount;
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
                ", PaymentAmount=" + PaymentAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", impUid='" + impUid + '\'' +
                ", merchantUid='" + merchantUid + '\'' +
                ", apiParm='" + apiParm + '\'' +
                '}';
    }
}
