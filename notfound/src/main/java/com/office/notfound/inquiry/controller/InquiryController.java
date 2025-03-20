package com.office.notfound.inquiry.controller;

import com.office.notfound.inquiry.model.dto.InquiryDTO;
import com.office.notfound.inquiry.model.service.InquiryService;
import com.office.notfound.member.model.dto.MemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/list")
    public String selectAllInquiryList(Model model, @AuthenticationPrincipal MemberDTO loginUser) {

        List<InquiryDTO> inquiryList;

        // 관리자(권한 코드 1) 여부 확인
        boolean isAdmin = loginUser.getMemberAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthorityCode() == 1);

        if (isAdmin) {
            // 관리자라면 모든 문의 목록 조회
            inquiryList = inquiryService.selectAllInquiryList();
            System.out.println("inquiryList --------------= " + inquiryList);
        } else {
            // 일반 사용자라면 본인의 문의 목록만 조회
            inquiryList = inquiryService.selectMyInquiryList(loginUser.getMemberCode());
            System.out.println("inquiryList--------------- " + inquiryList);
        }

        model.addAttribute("inquiryList", inquiryList);



/*        List<InquiryDTO> inquiryList = inquiryService.selectAllInquiryList();

        model.addAttribute("inquiryList", inquiryList);*/
//        System.out.println("inquiryList " + inquiryList);

        return "inquiry/inquiry";
    }

    @GetMapping("/detail/{code}")
    public String selectInquiryDetail(@PathVariable("code") int code, Model model) {

        InquiryDTO inquiry = inquiryService.selectInquiryByCode(code);

        model.addAttribute("inquiry", inquiry);
//        System.out.println("inquiry " + inquiry);

        return "inquiry/inquiryDetail";
    }
}
