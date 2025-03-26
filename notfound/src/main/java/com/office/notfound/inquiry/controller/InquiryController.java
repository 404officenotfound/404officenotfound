package com.office.notfound.inquiry.controller;

import com.office.notfound.inquiry.model.dto.InquiryDTO;
import com.office.notfound.inquiry.model.service.InquiryService;
import com.office.notfound.member.model.dto.MemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        } else {
            // 일반 사용자라면 본인의 문의 목록만 조회
            inquiryList = inquiryService.selectMyInquiryList(loginUser.getMemberCode());
        }

        model.addAttribute("inquiryList", inquiryList);

        return "inquiry/inquiry";
    }

    @GetMapping("/detail/{inquiryCode}")
    public String selectInquiryDetail(@PathVariable("inquiryCode") int inquiryCode, Model model) {

        InquiryDTO inquiry = inquiryService.selectInquiryByCode(inquiryCode);

        model.addAttribute("inquiry", inquiry);

        return "inquiry/inquiryDetail";
    }


    // 1:1 문의 등록 페이지
    @GetMapping("regist")
    public String registPage() {
        return "inquiry/inquiryRegist";
    }

    // 1:1 문의 등록
    @PostMapping("regist")
    public String registInquiry(@ModelAttribute InquiryDTO newInquiry,
                               @AuthenticationPrincipal MemberDTO member,
                               RedirectAttributes rAttr) {
        try {
            // 로그인한 회원의 ID 및 memberCode 넣기
            newInquiry.setMemberId(member.getMemberId());
            // 로그인한 회원번호 찾아서 새 리뷰 객체에 넣기
            newInquiry.setMemberCode(member.getMemberCode());
            newInquiry.setInquiryAnswerState("답변 대기");
            newInquiry.setInquiryAdminAnswer(null);

            inquiryService.registNewInquiry(newInquiry);

            rAttr.addFlashAttribute("message", "문의글을 등록하였습니다.");

/*            if (newInquiry.getMemberCode() == 1) {
                return "redirect:/inquiry/list";
            } else {
                return "redirect:/review/my-reviews";

            }*/
            return "redirect:/inquiry/list";

        } catch (IllegalArgumentException e) {
            rAttr.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/inquiry/regist";
        }   catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("errorMessage", "등록 실패: " + e.getMessage());
            return "redirect:/inquiry/regist";
        }

    }


    // 문의글 수정 페이지
    @GetMapping("/edit/{inquiryCode}")
    public ModelAndView userInquiryEditPage(@PathVariable int inquiryCode,
                                           ModelAndView mv) {

        InquiryDTO myInquiry = inquiryService.findMyInquiryByCode(inquiryCode);

        mv.addObject("myInquiry", myInquiry);
        mv.setViewName("inquiry/inquiryEdit");

        return mv;
    }


    @PostMapping("/edit/{inquiryCode}")
    public String userInquiryEdit(@PathVariable int inquiryCode,
                                  @ModelAttribute InquiryDTO myInquiry,
                                  @AuthenticationPrincipal MemberDTO member,
                                  RedirectAttributes rAttr) {

        try {
            myInquiry.setInquiryCode(inquiryCode);
            myInquiry.setMemberId(member.getMemberId());
            myInquiry.setMemberCode(member.getMemberCode());

            if (myInquiry.getMemberCode() == 1) {
                myInquiry.setInquiryAnswerState("답변 완료");
            } else {
                myInquiry.setInquiryAnswerState("답변 대기");
                myInquiry.setInquiryAdminAnswer(null);
            }

            inquiryService.updateMyInquiry(myInquiry);

            rAttr.addFlashAttribute("message", "문의글이 수정되었습니다.");

                return "redirect:/inquiry/list";

        }   catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("message", "문의글 수정에 실패했습니다: " + e.getMessage());
            return "redirect:/inquiry/edit/" + inquiryCode;
        }
    }



    // 문의글 삭제 핸들러
    @PostMapping("/delete/{inquiryCode}")
    public String InquiryDelete(@PathVariable int inquiryCode,
                                   @AuthenticationPrincipal MemberDTO member,
                                   RedirectAttributes rAttr) {

        try {
            inquiryService.deleteInquiry(inquiryCode);
            rAttr.addFlashAttribute("message", "리뷰가 삭제되었습니다.");
                return "redirect:/inquiry/list";


        } catch (Exception e) {
            e.printStackTrace();
            rAttr.addFlashAttribute("message", "리뷰가 실패했습니다: " + e.getMessage());
            return "redirect:/detail/{inquiryCode}";
        }
    }

}
