package com.office.notfound.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        HttpSession session = request.getSession();

        // 로그인 실패 시 세션에 에러 메시지 저장
        String errorMessage = "아이디 또는 비밀번호가 잘못되었습니다.";

        // UsernameNotFoundException을 체크하여 아이디가 존재하지 않는 경우 처리
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "아이디가 존재하지 않습니다.";
        }
        // BadCredentialsException을 체크하여 비밀번호가 틀린 경우 처리
        else if (exception instanceof BadCredentialsException) {
            errorMessage = "비밀번호가 틀렸습니다.";
        }

        // 에러 메시지를 세션에 설정
        session.setAttribute("loginError", errorMessage);

        // 로그인 실패 후 로그인 페이지로 리다이렉트
        response.sendRedirect("/auth/login");
    }
}

