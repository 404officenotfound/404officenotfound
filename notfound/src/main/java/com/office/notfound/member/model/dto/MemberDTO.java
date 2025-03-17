package com.office.notfound.member.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemberDTO implements UserDetails {

    private int memberCode;               // 회원번호
    private String memberId;              // 사용자 ID
    private String memberPassword;        // 비밀번호
    private String memberName;            // 이름
    private String memberEmail;           // 이메일
    private String memberPhone;           // 전화번호
    private String memberEndstatus;       // 탈퇴여부
    private LocalDateTime memberEnddate;  // 탈퇴 날짜
    private List<AuthorityDTO> memberAuthorities;  // 권한 정보


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        this.memberAuthorities.forEach(authority ->
                authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName())));

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /* 설명.
     *  계정 비활성화 여부로 사용자가 사용할 수 없는 상태를 나타내며,
     *  false면 Spring Security가 인증 작업을 수행해주지 않아 계정을 사용할 수 없다.
     *  (디폴트는 true)
     * */
    @Override
    public boolean isEnabled() {
        return !"Y".equals(memberEndstatus); // 탈퇴 상태가 'Y'이면 false 반환
    }

    public MemberDTO() {
    }

    public MemberDTO(int memberCode, String memberId, String memberPassword, String memberName, String memberEmail, String memberPhone,
                     String memberEndstatus, LocalDateTime memberEnddate, List<AuthorityDTO> memberAuthorities) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberEndstatus = memberEndstatus;
        this.memberEnddate = memberEnddate;
        this.memberAuthorities = memberAuthorities;
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

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public String getMemberEndstatus() {
        return memberEndstatus;
    }

    public void setMemberEndstatus(String memberEndstatus) {
        this.memberEndstatus = memberEndstatus;
    }

    public LocalDateTime getMemberEnddate() {
        return memberEnddate;
    }

    public void setMemberEnddate(LocalDateTime memberEnddate) {
        this.memberEnddate = memberEnddate;
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
                ", memberId='" + memberId + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberEndstatus='" + memberEndstatus + '\'' +
                ", memberEnddate=" + memberEnddate +
                ", memberAuthorities=" + memberAuthorities +
                '}';
    }
}
