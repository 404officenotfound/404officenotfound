package com.office.notfound.member.model.service;

import com.office.notfound.member.model.dao.MemberMapper;
import com.office.notfound.member.model.dto.AuthorityDTO;
import com.office.notfound.member.model.dto.MemberAuthorityDTO;
import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.member.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

        // 아이디 중복 체크
        boolean isIdDuplicate = memberMapper.countMemberById(signupDTO.getMemberId()) > 0;
        // 이메일 중복 체크
        boolean isEmailDuplicate = memberMapper.countMemberByEmail(signupDTO.getMemberEmail()) > 0;

        if (isIdDuplicate || isEmailDuplicate) {
            return 0; // 중복이 있는 경우 실패
        }

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
        /*  tbl_member_role 테이블에 사용자 별 권한 삽입 */
        /*  사용자 db에 등록된 member_code 최대 pk값 조회 */
        int maxMemberCode = memberMapper.findMaxMemberCode();


        /* tbl_user_role 테이블에 신규 등록된 사용자의 PK와 일반사용자 권한 2(user)를 조합하여 삽입*/
        Integer result2 = null;

        try{
            result2 = memberMapper.registMemberAuthority(new MemberAuthorityDTO(maxMemberCode, 2));
        } catch(DuplicateKeyException e){
            result2 = 0;
            e.printStackTrace();
        } catch(BadSqlGrammarException e){
            result2 = 0;
            e.printStackTrace();
        }
        System.out.println("#2-2 신규 사용자 및 권한 코드 삽입 결과 : " + result2);

        /* 위 세가지 트랜잭션이 모두 성공해야 '회원가입'이라는 비즈니스 로직이 성공했다고 판단.*/
        Integer finalResult = null;

        if(result1 == null || result2 == null){
            finalResult = null;
        }else if(result1 == 1 && result2 == 1){
            finalResult = 1;
        }else{
            finalResult = 0;
        }
        /* 작업 결과 반환 */
        return finalResult;

    }
    /* Ajax 방식으로 서버에 요청하고, 서버에서 응답으로 JSON 데이터를 반환받아 처리 */
    // 아이디 중복 체크
    public boolean memberIdDuplicate(String memberId) {
        Integer count = memberMapper.countMemberById(memberId);
        return count != null && count > 0;
    }

    public MemberDTO findByUsername(String username) {
        MemberDTO foundUser = memberMapper.findByUsername(username);

        if(!Objects.isNull(foundUser)){
            return foundUser;
        }else{
            return null;
        }
    }

    public List<AuthorityDTO> findAllAuthoritiesByMemberCode(int memberCode) {
        return memberMapper.findAllAuthoritiesByMemberCode(memberCode);
    }
}




