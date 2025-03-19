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

import java.util.*;

@Service
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public MemberService(MemberMapper memberMapper,
                         PasswordEncoder passwordEncoder) {
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

        try {
            result2 = memberMapper.registMemberAuthority(new MemberAuthorityDTO(maxMemberCode, 2));
        } catch (DuplicateKeyException e) {
            result2 = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result2 = 0;
            e.printStackTrace();
        }
        System.out.println("#2-2 신규 사용자 및 권한 코드 삽입 결과 : " + result2);

        /* 위 세가지 트랜잭션이 모두 성공해야 '회원가입'이라는 비즈니스 로직이 성공했다고 판단.*/
        Integer finalResult = null;

        if (result1 == null || result2 == null) {
            finalResult = null;
        } else if (result1 == 1 && result2 == 1) {
            finalResult = 1;
        } else {
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

        // memberAuthorities가 null일 경우 빈 리스트로 설정
        if (foundUser != null && foundUser.getMemberAuthorities() == null) {
            foundUser.setMemberAuthorities(new ArrayList<>());
        }

        return foundUser; // foundUser가 null이라면 null이 그대로 반환됨
    }

    public List<AuthorityDTO> findAllAuthoritiesByMemberCode(int memberCode) {
        return memberMapper.findAllAuthoritiesByMemberCode(memberCode);
    }

    // 회원 정보 수정
    @Transactional
    public void update(MemberDTO updateMember) {
        // 업데이트 전에 memberAuthorities가 null이면 빈 리스트로 설정
        if (updateMember.getMemberAuthorities() == null) {
            updateMember.setMemberAuthorities(new ArrayList<>());
        }

        memberMapper.updatemember(updateMember);
    }

    // 관리자 회원 정보 수정
    @Transactional
    public void updateAdmin(MemberDTO updateAdminMember) {
        if (updateAdminMember.getMemberAuthorities() == null) {
            updateAdminMember.setMemberAuthorities(new ArrayList<>());
        }

        memberMapper.updateadmin(updateAdminMember);
    }

    //비밀번호 검증
    public boolean checkPassword(int memberCode, String inputPassword) {
        MemberDTO member = memberMapper.findMemberByCode(memberCode);

        // 로그 추가
        System.out.println("Found Member: " + member);

        if (member == null) return false;

        // 비밀번호 검증 결과 로그
        boolean isPasswordCorrect = passwordEncoder.matches(inputPassword, member.getMemberPassword());
        System.out.println("비밀번호 검증 결과: " + isPasswordCorrect);

        return isPasswordCorrect;
    }


    // 회원 탈퇴
    @Transactional
    public boolean withdraw(int memberCode) {
        int result = memberMapper.withdrawMember(memberCode);

        // 로그 추가
        System.out.println("회원 탈퇴 결과: " + result);

        return result > 0; // 업데이트된 행이 1 이상이어야 성공
    }
    // ID찾기
    public String findMemberIdByNameAndEmail(String memberName, String memberEmail) {
        return memberMapper.findMemberIdByNameAndEmail(memberName, memberEmail);
    }
    // 비밀번호 변경
    @Transactional
    public void updatePassword(int memberCode, String newPassword) {
        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(newPassword);

        // 디버깅 로그
        System.out.println("암호화된 새 비밀번호: " + encryptedPassword);

        // 데이터베이스 업데이트
        int rowsUpdated = memberMapper.updatePassword(memberCode, encryptedPassword);
        if (rowsUpdated == 0) {
            throw new RuntimeException("비밀번호 변경 실패: 데이터베이스에 저장되지 않았습니다.");
        }
    }


    //비밀번호 변경
    @Transactional
    public void changePassword(int memberCode, String currentPassword, String newPassword) {
        // 사용자 조회
        MemberDTO member = memberMapper.findMemberByCode(memberCode);
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        // 현재 비밀번호 검증(현재 비밀번호 = 인증번호 확인)
        if (!Objects.equals(member.getMemberPassword(), currentPassword)) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새 비밀번호 저장 (암호화 후 저장)
        String encryptedPassword = passwordEncoder.encode(newPassword);
        memberMapper.updatePassword(memberCode, encryptedPassword);
    }

    @Transactional
    public String resetPassword(String memberId, String memberName, String memberEmail) {
        // 디버깅 추가
        System.out.println("resetPassword 호출 - 입력된 데이터: memberId=" + memberId +
                ", memberName=" + memberName + ", memberEmail=" + memberEmail);

        // 사용자 조회
        MemberDTO member = memberMapper.findMemberByIdNameEmail(memberId, memberName, memberEmail);
        if (member == null) {
            throw new IllegalArgumentException("입력하신 정보와 일치하는 계정을 찾을 수 없습니다.");
        }
        System.out.println("사용자 조회 성공 - memberId=" + member.getMemberId());

        // 6자리 비밀번호 생성
        String newPassword = generateRandomPassword(6);
        System.out.println("생성된 새로운 비밀번호: " + newPassword);

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(newPassword);
        System.out.println("암호화된 비밀번호: " + encodedPassword);

        // DB 업데이트
        int rowsUpdated = memberMapper.resetPassword(memberId, memberName, memberEmail, encodedPassword);
        if (rowsUpdated == 0) {
            throw new RuntimeException("비밀번호를 업데이트하는 데 실패했습니다.");
        }
        System.out.println("비밀번호 DB 업데이트 성공");

        return newPassword; // 사용자에게 변경된 비밀번호 반환
    }


    private String generateRandomPassword(int length) {
        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder newPassword = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSet.length());
            newPassword.append(charSet.charAt(randomIndex));
        }
        return newPassword.toString();
    }


}







