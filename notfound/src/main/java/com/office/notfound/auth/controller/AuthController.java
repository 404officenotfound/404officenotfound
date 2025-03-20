package com.office.notfound.auth.controller;

import com.office.notfound.auth.model.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        // 로그인 페이지로 이동 시 세션에 실패 메시지가 있으면 출력
        if (session.getAttribute("loginError") != null) {
            model.addAttribute("loginError", session.getAttribute("loginError"));
            session.removeAttribute("loginError"); // 메시지 처리 후 삭제
        }
        return "auth/login";
    }

    // 로그인 POST 요청 시 실패 처리
    @PostMapping("/login")
    public String login(@RequestParam("memberId") String memberId,
                        @RequestParam("memberPassword") String memberPassword,
                        HttpSession session) {
        try {
            // 인증 서비스에 대한 로그인 처리 코드
            authService.loadUserByUsername(memberId);
            return "redirect:/";  // 로그인 성공 시 홈으로 리다이렉트
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            // 로그인 실패 시 메시지 세션에 저장
            session.setAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/auth/login";  // 로그인 실패 시 다시 로그인 페이지로 리다이렉트
        }
    }
}