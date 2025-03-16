package com.office.notfound.auth.model;

import com.office.notfound.member.model.dto.AuthorityDTO;
import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class AuthService implements UserDetailsService {

    private final MemberService memberService;

    @Autowired
    public AuthService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("AuthService의 loadUserByUsername 호출됨...");

        System.out.println("사용자가 입력한 ID확인 : " + username);

        MemberDTO foundUser = memberService.findByUsername(username);
        System.out.println("#1. username 파라미터로 조회된 사용자 : " + foundUser);

        if(Objects.isNull(foundUser)){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        /* 검색된 사용자의 PK값을 사용해 tbl_member_role 테이블로부터 해당 사용자가 인가받을 수 있는 모든 권한을 조회 */
        int memberCode = foundUser.getMemberCode();

        List<AuthorityDTO> authorities = memberService.findAllAuthoritiesByMemberCode(memberCode);
        System.out.println("#2. 해당 사용자가 인가받을 수 있는 권한들 : " + authorities);

        /* 사용자가 인가받을 모든 권한(List<AuthorityDTO>을 foundUser에 추가 */
        foundUser.setMemberAuthorities(authorities);
        System.out.println("#3. 완성된 UserDetails 타입의 사용자 정보 : " + foundUser);

        /* 사용자의 모든 인증/인가 정보가 담긴 UserDetails 타입의 데이터를 반환 */
        return foundUser; // null을 안바꾸면 이상한 상태로 로그인 구현
    }
}

