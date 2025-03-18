package com.office.notfound.faq.model.dto;

public class FaqDTO {

    private int faqCode;
    private String faqTitle;
    private String faqContent;

    public FaqDTO() {
    }

    public FaqDTO(int faqCode, String faqTitle, String faqContent) {
        this.faqCode = faqCode;
        this.faqTitle = faqTitle;
        this.faqContent = faqContent;
    }

    public int getFaqCode() {
        return faqCode;
    }

    public void setFaqCode(int faqCode) {
        this.faqCode = faqCode;
    }

    public String getFaqTitle() {
        return faqTitle;
    }

    public void setFaqTitle(String faqTitle) {
        this.faqTitle = faqTitle;
    }

    public String getFaqContent() {
        return faqContent;
    }

    public void setFaqContent(String faqContent) {
        this.faqContent = faqContent;
    }

    @Override
    public String toString() {
        return "FaqDTO{" +
                "faqCode=" + faqCode +
                ", faqTitle='" + faqTitle + '\'' +
                ", faqContent='" + faqContent + '\'' +
                '}';
    }
}
