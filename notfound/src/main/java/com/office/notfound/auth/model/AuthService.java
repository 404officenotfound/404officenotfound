package com.office.notfound.auth.model;

import com.office.notfound.member.model.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/* UserDetailsService:
Security에서 사용자의 아이디를 인증하기 위한 인터페이스다.
loadUserByUsername() 메서드를 필수로 구현해야 하며,
로그인 인증 시 해당 메서드에 login 요청 시 전달된 사용자의 ID(=username)를 매개변수로 하여 DB로부터 조회한다.
*/
@Service
public class AuthService implements UserDetailsService {

    private final MemberService memberService;

    @Autowired
    public AuthService(MemberService memberService) {
        this.memberService = memberService;
    }

