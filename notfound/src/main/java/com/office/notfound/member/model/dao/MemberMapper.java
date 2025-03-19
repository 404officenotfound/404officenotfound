package com.office.notfound.member.model.dao;

import com.office.notfound.member.model.dto.AuthorityDTO;
import com.office.notfound.member.model.dto.MemberAuthorityDTO;
import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.member.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 회원 정보 등록
    Integer regist(SignupDTO signupDTO);
    // 회원 코드 최대값 조회
    int findMaxMemberCode();
    // 회원 권한 등록
    Integer registMemberAuthority(MemberAuthorityDTO memberAuthorityDTO);

    // 중복 사용자 조회
    Integer countMemberById(@Param("memberId") String memberId);

    // 이메일 중복 체크 메서드 추가
    Integer countMemberByEmail(@Param("memberEmail") String memberEmail);
    // 사용자 정보 조회
    MemberDTO findByUsername(String username);
    // 사용자 권한 조회
    List<AuthorityDTO> findAllAuthoritiesByMemberCode(int memberCode);

    // 회원정보수정
    int updatemember(MemberDTO updateMember);
    // 어드민정보수정
    int updateadmin(MemberDTO updateAdminMember);
    // 회원 코드로 회원정보 조회
    MemberDTO findMemberByCode(@Param("memberCode") int memberCode);
    // 회원탈퇴
    int withdrawMember(@Param("memberCode") int memberCode);

    // 이름과 이메일로 아이디를 조회
    String findMemberIdByNameAndEmail(@Param("memberName") String memberName,
                                      @Param("memberEmail") String memberEmail);
    // 비밀번호 변경
    int updatePassword(@Param("memberCode") int memberCode, @Param("memberPassword") String memberPassword);

    // 비밀번호 초기화
    int resetPassword(@Param("memberId") String memberId,
                      @Param("memberName") String memberName,
                      @Param("memberEmail") String memberEmail,
                      @Param("newPassword") String newPassword);

    // ID, 이름, 이메일로 사용자 조회
    MemberDTO findMemberByIdNameEmail(@Param("memberId") String memberId,
                                      @Param("memberName") String memberName,
                                      @Param("memberEmail") String memberEmail);




}
