package com.office.notfound.payment.controller;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/payment")
public class AdminPaymentController {

    private final PaymentService paymentService;

    public AdminPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 🔹 관리자: 모든 결제 내역 전체 조회
     */
    @GetMapping("/search/all")
    public ResponseEntity<Map<String, Object>> getAllPayments() {
        Map<String, Object> response = new HashMap<>();

        List<PaymentDTO> paymentList = paymentService.findAllPaymentsForAdmin();

        if (paymentList.isEmpty()) {
            response.put("success", false);
            response.put("message", "검색 결과가 없습니다.");
            return ResponseEntity.ok(response);
        }

        response.put("success", true);
        response.put("payments", paymentList);
        return ResponseEntity.ok(response);
    }

    /**
     * 🔹 관리자: 특정 조건으로 결제 내역 검색 (memberCode 추가됨)
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchAllPayments(
            @RequestParam(required = false) Integer memberCode,
            @RequestParam(required = false) String paymentDate,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        Map<String, Object> response = new HashMap<>();

        List<PaymentDTO> searchPayment = paymentService.searchPayment(memberCode, paymentDate, startDate, endDate);

        if (searchPayment.isEmpty()) {
            response.put("success", false);
            response.put("message", "검색 결과가 없습니다.");
            return ResponseEntity.ok(response);
        }

        response.put("success", true);
        response.put("payments", searchPayment);
        return ResponseEntity.ok(response);
    }
}

