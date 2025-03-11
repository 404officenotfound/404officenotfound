package com.office.notfound.member.model.dto;

public class SignupDTO {

    private String memberName;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private String memberEnrolldate;
    private String memberEnddate;
    private String memberEndstatus;

    public SignupDTO() {
    }

    public SignupDTO(String memberName, String memberId, String memberPassword, String memberEmail,
                     String memberPhone, String memberEnrolldate, String memberEnddate, String memberEndstatus) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberEnrolldate = memberEnrolldate;
        this.memberEnddate = memberEnddate;
        this.memberEndstatus = memberEndstatus;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getMemberEnrolldate() {
        return memberEnrolldate;
    }

    public void setMemberEnrolldate(String memberEnrolldate) {
        this.memberEnrolldate = memberEnrolldate;
    }

    public String getMemberEnddate() {
        return memberEnddate;
    }

    public void setMemberEnddate(String memberEnddate) {
        this.memberEnddate = memberEnddate;
    }

    public String getMemberEndstatus() {
        return memberEndstatus;
    }

    public void setMemberEndstatus(String memberEndstatus) {
        this.memberEndstatus = memberEndstatus;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "memberName='" + memberName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberEnrolldate='" + memberEnrolldate + '\'' +
                ", memberEnddate='" + memberEnddate + '\'' +
                ", memberEndstatus='" + memberEndstatus + '\'' +
                '}';
    }
}
