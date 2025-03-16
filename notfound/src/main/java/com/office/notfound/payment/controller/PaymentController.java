package com.office.notfound.payment.controller;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/search/all")
    public String allSearchPayments(Model model) {
        // 전체 결제 내역을 가져옴
        List<PaymentDTO> paymentList = paymentService.findAllPayment();
        model.addAttribute("paymentList", paymentList);  // 결과를 paymentList로 전달
        // 검색 결과가 없으면 메시지 표시
        if (paymentList.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        model.addAttribute("searchExecuted", true);  // 검색 실행 표시
        return "payment/search";  // 검색 페이지에 전체 결제 내역을 표시
    }
    @GetMapping("/search")
    public String searchPayment(
            @RequestParam(required = false) String paymentCode,
            @RequestParam(required = false) String paymentDate,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        // 현재 선택된 검색 유형을 판별
        String selectedSearchType = "paymentCode"; // 기본값: 결제번호 검색
        if (paymentDate != null && !paymentDate.isEmpty()) {
            selectedSearchType = "paymentDate";
        } else if (startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()) {
            selectedSearchType = "paymentPeriod";
        }


        // 검색이 실행되지 않은 경우 -> 결과 없이 반환
        if ((paymentCode == null || paymentCode.isEmpty()) &&
                (paymentDate == null || paymentDate.isEmpty()) &&
                (startDate == null || startDate.isEmpty()) &&
                (endDate == null || endDate.isEmpty())) {
            model.addAttribute("searchExecuted", false);
            return "payment/search";
        }

        // 결제번호 입력값 검증 (숫자가 아닌 경우 예외 처리)
        Integer paymentCodeInt = null;
        if (paymentCode != null && !paymentCode.isEmpty()) {
            try {
                paymentCodeInt = Integer.parseInt(paymentCode);
            } catch (NumberFormatException e) {
                model.addAttribute("noResultsMessage", "결제번호는 숫자로 입력해주세요.");
                model.addAttribute("searchExecuted", true);
                return "payment/search";
            }
        }

        // 검색 수행
        List<PaymentDTO> searchPayment = paymentService.searchPayment(paymentCodeInt, paymentDate, startDate, endDate);

        // 검색이 실행되었음을 표시
        model.addAttribute("searchExecuted", true);

        // 검색 결과가 없을 경우 메시지 추가
        if (searchPayment.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        model.addAttribute("selectedSearchType", selectedSearchType);
        model.addAttribute("searchPayment", searchPayment);
        return "payment/search";
    }

}