package com.office.notfound.member.controller;


import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.member.model.dto.SignupDTO;
import com.office.notfound.member.model.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv,
                               SignupDTO signupDTO,
                               RedirectAttributes rAttr) {

        Integer result = memberService.regist(signupDTO);

        /* 비즈니스 로직 성공 여부에 따라서 Dispatcher Servlet에 반환할 View와 Model을 세팅. */
        String message = null;

        if (result == 0) {
            message = "이미 해당 아이디 또는 이메일로 가입된 회원이 있습니다.<br> 다시 입력해주세요.";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/member/signup");
        } else if (result >= 1) {
            message = "회원 가입이 성공적으로 완료되었습니다.";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/");
        } else {
            message = "알 수 없는 오류가 발생했습니다. 관리자에게 문의하세요";
            System.out.println("message = " + message);
            mv.setViewName("redirect:/member/signup");
        }

        rAttr.addFlashAttribute("message", message); // 리다이렉트
        return mv;
    }

    // 아이디 중복 확인
    @GetMapping("/check-duplicate")
    @ResponseBody //메서드 반환값인 Map<String, Boolean>을 JSON 형식으로 변환하여 클라이언트에게 응답으로 전송함(html파일쪽으로)
    public Map<String, Boolean> checkDuplicate(@RequestParam String memberId) { //post요청으로 부터 파라미터 전달 받음.
        boolean isDuplicate = memberService.memberIdDuplicate(memberId); //조회한 API 경로에서 중복확인은 Service클래스 메서드에서 처리
        Map<String, Boolean> response = new HashMap<>(); // 객체생성
        response.put("duplicate", isDuplicate); //  duplicate키에 중복여부 저장
        return response;
    }

    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal MemberDTO member, Model model) {
        model.addAttribute("member", member);
        return "member/mypage"; // 일반 사용자 마이페이지
    }

    // 관리자 마이페이지
    @GetMapping("/adminmypage")
    public String adminMypage(@AuthenticationPrincipal MemberDTO member, Model model) {
        // 관리자 권한 체크
        boolean isAdmin = member.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            model.addAttribute("member", member);
            return "member/adminmypage"; // 관리자 마이페이지
        } else {
            return "redirect:/"; // 권한이 없으면 메인 페이지로 리디렉션
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO updateMember,
                         @AuthenticationPrincipal MemberDTO member,
                         RedirectAttributes rttr, Model model,
                         HttpSession session) {
        try {
            updateMember.setMemberCode(member.getMemberCode());

            memberService.update(updateMember);
            // 수정된 정보 바로 반영하여 마이페이지로 이동
            MemberDTO updatedMember = memberService.findByUsername(member.getMemberId());

            // 수정된 회원 정보에서 authorities가 null이면 빈 리스트로 설정
            if (updatedMember.getMemberAuthorities() == null) {
                updatedMember.setMemberAuthorities(new ArrayList<>());
            }
            model.addAttribute("member", updatedMember); // 수정된 회원 정보를 마이페이지에 전달

            // Spring Security의 SecurityContext에 있는 사용자 정보를 새로 갱신
            Authentication authentication = new UsernamePasswordAuthenticationToken(updatedMember, member.getPassword(), member.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            rttr.addFlashAttribute("successMessage", "회원정보가 수정되었습니다.");


            return "redirect:/member/mypage";
        } catch (Exception e) {
            rttr.addFlashAttribute("errorMessage", "회원정보 수정 실패: " + e.getMessage());

            return "redirect:/member/mypage";
        }
    }

    @PostMapping("/adminupdate")
    public String updateAdmin(@ModelAttribute MemberDTO updateAdminMember,
                         @AuthenticationPrincipal MemberDTO adminmember,
                         RedirectAttributes rttr) {
        try {
            updateAdminMember.setMemberCode(adminmember.getMemberCode());

            memberService.updateAdmin(updateAdminMember);
            rttr.addFlashAttribute("successMessage", "회원정보가 수정되었습니다.");

            return "redirect:/member/adminmypage";
        } catch (Exception e) {
            rttr.addFlashAttribute("errorMessage", "회원정보 수정 실패: " + e.getMessage());

            return "redirect:/member/adminmypage";
        }
    }


}