package com.office.notfound.payment.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.service.PaymentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 🔹 로그인한 회원의 전체 결제 내역 조회 (HTML 반환)
     */
    @GetMapping("/search/all")
    public String getAllPayments(@AuthenticationPrincipal MemberDTO member, Model model) {
        if (member == null) {
            return "redirect:/login";
        }

        List<PaymentDTO> paymentList = paymentService.findAllPayments(member.getMemberCode()); // 🔹 `memberCode` 필수 적용
        model.addAttribute("searchExecuted", true);
        model.addAttribute("paymentList", paymentList);  // 🔹 `paymentsList` → `paymentList`로 변경하여 통일

        return "payment/search";
    }

    /**
     * 🔹 특정 조건으로 결제 내역 검색
     */
    @GetMapping("/search")
    public String searchPayments(@AuthenticationPrincipal MemberDTO member,
                                 @RequestParam(required = false) String paymentCodeInt,
                                 @RequestParam(required = false) String paymentDate,
                                 @RequestParam(required = false) String startDate,
                                 @RequestParam(required = false) String endDate,
                                 Model model) {
        if (member == null) {
            return "redirect:/login";
        }

        Integer paymentCode = null;
        if (paymentCodeInt != null && !paymentCodeInt.trim().isEmpty()) {
            try {
                paymentCode = Integer.valueOf(paymentCodeInt.trim());
            } catch (NumberFormatException e) {
                System.out.println("🚨 잘못된 결제번호 입력: " + paymentCodeInt);
                return "redirect:/error";
            }
        }

        // ✅ 검색 조건 디버깅 로그 추가
        System.out.println("🔍 [DEBUG] 검색 요청:");
        System.out.println("   - memberCode: " + member.getMemberCode());
        System.out.println("   - paymentCode: " + (paymentCode != null ? paymentCode : "❌ 미입력"));
        System.out.println("   - paymentDate: " + (paymentDate != null && !paymentDate.isEmpty() ? paymentDate : "❌ 미입력"));
        System.out.println("   - startDate: " + (startDate != null && !startDate.isEmpty() ? startDate : "❌ 미입력"));
        System.out.println("   - endDate: " + (endDate != null && !endDate.isEmpty() ? endDate : "❌ 미입력"));

        // 🔥 memberCode를 MyBatis로 전달
        List<PaymentDTO> searchPayment = paymentService.searchPayment(member.getMemberCode(), paymentCode, paymentDate, startDate, endDate);

        model.addAttribute("searchExecuted", true);
        model.addAttribute("searchPayment", searchPayment != null ? searchPayment : List.of());

        return "payment/search";
    }
}
