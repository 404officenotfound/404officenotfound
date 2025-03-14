package com.office.notfound.member.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class SignupDTO {

    // 문자열 필드가 null, 빈 문자열(""), 공백 문자만 포함된 문자열(" ")불가로 @Valid를 사용하면 @NotBlank 등의 검증을함
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하로 입력해주세요.")
    private String memberName;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하로 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 3, message = "비밀번호는 최소 3자 이상 입력해주세요.")
    private String memberPassword;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String memberEmail;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{1,20}$", message = "전화번호는 20자리 숫자이하만 입력 가능합니다.")
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
