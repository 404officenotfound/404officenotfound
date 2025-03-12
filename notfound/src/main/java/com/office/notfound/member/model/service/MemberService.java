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
        /*  tbl_member_role 테이블에 사용자 별 권한 INSERT */
        /*  사용자 db에 등록된 member_code 최대 pk값 조회 */
        int maxUserCode = memberMapper.findMaxUserCode();
        System.out.println("#2-1 현재 tbl_member의 PK 최대값 : " + maxUserCode);

        /* 목차. 2-2 tbl_user_role 테이블에 신규 등록된 사용자의 PK와 디폴트 권한(일반 사용자) PK인 2를 조합하여 INSERT*/
        Integer result2 = null;

        try{
            result2 = memberMapper.registUserAuthority(new UserAuthorityDTO(maxUserCode, 2));
        } catch(DuplicateKeyException e){
            result2 = 0;
            e.printStackTrace();
        } catch(BadSqlGrammarException e){
            result2 = 0;
            e.printStackTrace();
        }

        System.out.println("#2-2 신규 사용자 및 권한 코드 삽입 결과 : " + result2);

    }

    }
}

