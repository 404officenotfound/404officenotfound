package com.office.notfound.review.model.dto;

import java.time.LocalDate;

public class ReviewDTO {

    private int reviewCode;
    private int memberCode;
    private int paymentCode;
    private String memberId;
    private String reviewTitle;
    private String reviewContent;
    private LocalDate reviewDate;
    private int reviewRating;
    private String reviewImage;

    public ReviewDTO() {
    }

    public ReviewDTO(int reviewCode, int memberCode, int paymentCode, String memberId, String reviewTitle, String reviewContent, LocalDate reviewDate, int reviewRating, String reviewImage) {
        this.reviewCode = reviewCode;
        this.memberCode = memberCode;
        this.paymentCode = paymentCode;
        this.memberId = memberId;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.reviewRating = reviewRating;
        this.reviewImage = reviewImage;
    }

    public int getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(int reviewCode) {
        this.reviewCode = reviewCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
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

    public void setReviewImage(String reviewImage) {
        this.reviewImage = reviewImage;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "reviewCode=" + reviewCode +
                ", memberCode=" + memberCode +
                ", paymentCode=" + paymentCode +
                ", memberId='" + memberId + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                ", reviewRating='" + reviewRating + '\'' +
                ", reviewImage='" + reviewImage + '\'' +
                '}';
    }
}
