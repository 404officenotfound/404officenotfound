package com.office.notfound.member.model.dto;

public class AuthorityDTO {
    private int authorityCode;
    private String authorityName;

    public AuthorityDTO() {
    }

    public AuthorityDTO(int authorityCode, String authorityName) {
        this.authorityCode = authorityCode;
        this.authorityName = authorityName;
    }

    public int getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(int authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "authorityCode=" + authorityCode +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }
}
