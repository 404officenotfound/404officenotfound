package com.office.notfound.member.model.dto;

import java.time.LocalDateTime;

public class SignupDTO {

    private String memberName;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private LocalDateTime memberEnddate = null;  // 탈퇴 날짜 기본값 null
    private String memberEndstatus = "N";  // 기본 탈퇴 상태 'N'

    public SignupDTO() {
    }

    public SignupDTO(String memberName, String memberId, String memberPassword,
                     String memberEmail, String memberPhone, LocalDateTime memberEnddate, String memberEndstatus) {
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
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

    public LocalDateTime getMemberEnddate() {
        return memberEnddate;
    }

    public void setMemberEnddate(LocalDateTime memberEnddate) {
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
                ", memberEnddate=" + memberEnddate +
                ", memberEndstatus='" + memberEndstatus + '\'' +
                '}';
    }
}
