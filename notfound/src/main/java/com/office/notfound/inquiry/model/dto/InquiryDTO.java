package com.office.notfound.inquiry.model.dto;

import java.time.LocalDate;

public class InquiryDTO {

    private int inquiryCode;
    private int memberCode;
    private String memberId;
    private String inquiryTitle;
    private String inquiryContent;
    private LocalDate inquiryDate;
    private String inquiryAnswerState;
    private String inquiryAdminAnswer;

    public InquiryDTO() {
    }

    public InquiryDTO(int inquiryCode, int memberCode, String memberId, String inquiryTitle, String inquiryContent, LocalDate inquiryDate, String inquiryAnswerState, String inquiryAdminAnswer) {
        this.inquiryCode = inquiryCode;
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryDate = inquiryDate;
        this.inquiryAnswerState = inquiryAnswerState;
        this.inquiryAdminAnswer = inquiryAdminAnswer;
    }

    public int getInquiryCode() {
        return inquiryCode;
    }

    public void setInquiryCode(int inquiryCode) {
        this.inquiryCode = inquiryCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public String getInquiryContent() {
        return inquiryContent;
    }

    public void setInquiryContent(String inquiryContent) {
        this.inquiryContent = inquiryContent;
    }

    public LocalDate getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(LocalDate inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public String getInquiryAnswerState() {
        return inquiryAnswerState;
    }

    public void setInquiryAnswerState(String inquiryAnswerState) {
        this.inquiryAnswerState = inquiryAnswerState;
    }

    public String getInquiryAdminAnswer() {
        return inquiryAdminAnswer;
    }

    public void setInquiryAdminAnswer(String inquiryAdminAnswer) {
        this.inquiryAdminAnswer = inquiryAdminAnswer;
    }

    @Override
    public String toString() {
        return "InquiryDTO{" +
                "inquiryCode=" + inquiryCode +
                ", memberCode=" + memberCode +
                ", memberId='" + memberId + '\'' +
                ", inquiryTitle='" + inquiryTitle + '\'' +
                ", inquiryContent='" + inquiryContent + '\'' +
                ", inquiryDate='" + inquiryDate + '\'' +
                ", inquiryAnswerState='" + inquiryAnswerState + '\'' +
                ", inquiryAdminAnswer='" + inquiryAdminAnswer + '\'' +
                '}';
    }
}
