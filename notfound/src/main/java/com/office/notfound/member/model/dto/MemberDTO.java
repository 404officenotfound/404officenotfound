package com.office.notfound.member.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class MemberDTO implements UserDetails {

    private int memberCode;
    private String memberName;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private LocalDateTime memberEnddate = null;  // 탈퇴 날짜 기본값 null
    private String memberEndstatus = "N";  // 기본 탈퇴 상태 'N'
    private List<AuthorityDTO> memberAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    public MemberDTO() {
    }

    public MemberDTO(int memberCode, String memberName, String memberId, String memberPassword, String memberEmail,
                     String memberPhone, LocalDateTime memberEnddate, String memberEndstatus, List<AuthorityDTO> memberAuthorities) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberEnddate = memberEnddate;
        this.memberEndstatus = memberEndstatus;
        this.memberAuthorities = memberAuthorities;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
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

    public List<AuthorityDTO> getMemberAuthorities() {
        return memberAuthorities;
    }

    public void setMemberAuthorities(List<AuthorityDTO> memberAuthorities) {
        this.memberAuthorities = memberAuthorities;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "memberCode=" + memberCode +
                ", memberName='" + memberName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberEnddate=" + memberEnddate +
                ", memberEndstatus='" + memberEndstatus + '\'' +
                ", memberAuthorities=" + memberAuthorities +
                '}';
    }


}
