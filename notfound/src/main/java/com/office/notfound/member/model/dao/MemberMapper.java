package com.office.notfound.member.model.dao;

import com.office.notfound.member.model.dto.MemberAuthorityDTO;
import com.office.notfound.member.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    Integer regist(SignupDTO signupDTO);

    int findMaxMemberCode();

    Integer registMemberAuthority(MemberAuthorityDTO memberAuthorityDTO);
}
