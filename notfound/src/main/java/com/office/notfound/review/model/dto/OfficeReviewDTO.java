package com.office.notfound.review.model.dto;

public class OfficeReviewDTO {

    private int officeCode;
    private int storeCode;
    private String officeType;
    private int officeNum;
    private int officePrice;
    private String memberId;
    private String reviewTitle;
    private String reviewContent;
    private String reviewDate;
    private int reviewRating;
    private String reviewImage;
    private int paymentCode;        // tbl_payment의 payment_code 필드 추가

    public OfficeReviewDTO() {
    }


    public OfficeReviewDTO(int officeCode, int storeCode, String officeType, int officeNum, int officePrice, String memberId, String reviewTitle, String reviewContent, String reviewDate, int reviewRating, String reviewImage, int paymentCode) {
        this.officeCode = officeCode;
        this.storeCode = storeCode;
        this.officeType = officeType;
        this.officeNum = officeNum;
        this.officePrice = officePrice;
        this.memberId = memberId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.reviewRating = reviewRating;
        this.reviewImage = reviewImage;
        this.paymentCode = paymentCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(int officeCode) {
        this.officeCode = officeCode;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public int getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(int officeNum) {
        this.officeNum = officeNum;
    }

    public int getOfficePrice() {
        return officePrice;
    }

    public void setOfficePrice(int officePrice) {
        this.officePrice = officePrice;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewImage() {
        return reviewImage;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public void setReviewImage(String reviewImage) {
        this.reviewImage = reviewImage;
    }


    @Override
    public String toString() {
        return "OfficeReviewDTO{" +
                "officeCode=" + officeCode +
                ", storeCode=" + storeCode +
                ", officeType='" + officeType + '\'' +
                ", officeNum=" + officeNum +
                ", officePrice=" + officePrice +
                ", memberId='" + memberId + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                ", reviewRating=" + reviewRating +
                ", reviewImage='" + reviewImage + '\'' +
                ", paymentCode=" + paymentCode +
                '}';
    }
}
