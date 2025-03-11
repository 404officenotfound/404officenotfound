package com.office.notfound.member.model.service;

import com.office.notfound.member.model.dao.MemberMapper;
import com.office.notfound.member.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Integer regist(SignupDTO signupDTO) {

        //비밀번호 암호화 표시
        System.out.println("암호화 전 PW : " + signupDTO.getMemberPassword());
        signupDTO.setMemberPassword(passwordEncoder.encode(signupDTO.getMemberPassword()));
        System.out.println("암호화 후 PW : " + signupDTO.getMemberPassword());

        //회원 등록이 성공했을 때 1 (성공적으로 등록된 행의 수)을 반환할 것이고,
        // 실패한 경우 0 (아무것도 등록되지 않음)을 반환
        Integer result1 = null;
        try {
            result1 = memberMapper.regist(signupDTO);
        } catch (DuplicateKeyException e) {
            result1 = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result1 = 0;
            e.printStackTrace();
        }

    }
}

