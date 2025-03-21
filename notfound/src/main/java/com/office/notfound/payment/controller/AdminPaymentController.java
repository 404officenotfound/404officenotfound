package com.office.notfound.payment.controller;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/payment")
public class AdminPaymentController {

    private final PaymentService paymentService;

    public AdminPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 🔹 관리자: 모든 결제 내역 전체 조회 (Thymeleaf 반환)
     */

    @GetMapping("/search/all")
    public String getAllPayments(Model model) {

        List<PaymentDTO> Adminpayments = paymentService.findAllPaymentsForAdmin();

        model.addAttribute("searchExecuted", true);  // ✅ 검색 실행 여부 추가
        model.addAttribute("Adminpayments", Adminpayments != null ? Adminpayments : List.of());
        model.addAttribute("message", Adminpayments.isEmpty() ? "검색 결과가 없습니다." : "");

        return "admin/payment/search";
    }

    /**
     * 🔹 관리자: 특정 조건으로 결제 내역 검색 (Thymeleaf 반환)
     */
    @GetMapping("/search")
    public String searchAllPayments(
            @RequestParam(required = false) Integer memberCode,
            @RequestParam(required = false) Integer paymentCode,
            @RequestParam(required = false) String paymentDate,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        List<PaymentDTO> searchAdminPayment = paymentService.searchAdminPayment(paymentCode, memberCode, paymentDate, startDate, endDate);

        model.addAttribute("searchExecuted", true);  // ✅ 검색 실행 여부 추가
        model.addAttribute("searchAdminPayment", searchAdminPayment != null ? searchAdminPayment : List.of());
        model.addAttribute("message", searchAdminPayment.isEmpty() ? "검색 결과가 없습니다." : "");

        return "admin/payment/search";
    }
}
