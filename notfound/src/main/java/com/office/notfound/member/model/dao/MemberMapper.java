package com.office.notfound.member.model.dao;

import com.office.notfound.member.model.dto.MemberAuthorityDTO;
import com.office.notfound.member.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    Integer regist(SignupDTO signupDTO);

    int findMaxMemberCode();

    Integer registMemberAuthority(MemberAuthorityDTO memberAuthorityDTO);

    /*MyBatis의 Mapper 인터페이스에서 사용하는 어노테이션으로 SQL 쿼리에서 사용할 파라미터 이름을 지정하며 #{memberId}와 매핑 */
    Integer countMemberById(@Param("memberId") String memberId);

    // 이메일 중복 체크 메서드 추가
    Integer countMemberByEmail(@Param("memberEmail") String memberEmail);
}
