package com.office.notfound.member.model.dto;

//사용자별 권한 DTO
public class MemberAuthorityDTO {

    private int memberCode;
    private int authorityCode;

    public MemberAuthorityDTO() {
    }

    public MemberAuthorityDTO(int memberCode, int authorityCode) {
        this.memberCode = memberCode;
        this.authorityCode = authorityCode;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(int authorityCode) {
        this.authorityCode = authorityCode;
    }

    @Override
    public String toString() {
        return "MemberAuthorityDTO{" +
                "memberCode=" + memberCode +
                ", authorityCode=" + authorityCode +
                '}';
    }
}
