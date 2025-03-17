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

    /*MyBatis의 Mapper 인터페이스에서 사용하는 어노테이션으로 SQL 쿼리에서 사용할 파라미터 이름을 지정하며 #{memberId}와 매핑 */
    Integer countMemberById(@Param("memberId") String memberId);

    // 이메일 중복 체크 메서드 추가
    Integer countMemberByEmail(@Param("memberEmail") String memberEmail);
    // 사용자 정보 조회
    MemberDTO findByUsername(String username);
    // 사용자 권한 조회
    List<AuthorityDTO> findAllAuthoritiesByMemberCode(int memberCode);
}
