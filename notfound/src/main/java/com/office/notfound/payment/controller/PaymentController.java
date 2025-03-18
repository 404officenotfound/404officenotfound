package com.office.notfound.payment.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 🔹 로그인한 회원의 전체 결제 내역 조회
     */
    @GetMapping("/search/all")
    public ResponseEntity<Map<String, Object>> getAllPayments(@AuthenticationPrincipal MemberDTO member) {
        Map<String, Object> response = new HashMap<>();

        if (member == null) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        List<PaymentDTO> paymentList = paymentService.findAllPayments(member.getMemberCode());

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
     * 🔹 로그인한 회원의 특정 조건 결제 내역 검색
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchPayments(
            @AuthenticationPrincipal MemberDTO member,
            @RequestParam(required = false) String paymentDate,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        Map<String, Object> response = new HashMap<>();

        if (member == null) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        List<PaymentDTO> searchPayment = paymentService.searchPayment(member.getMemberCode(), paymentDate, startDate, endDate);

        if (searchPayment.isEmpty()) {
            response.put("success", false);
            response.put("message", "검색 결과가 없습니다.");
            return ResponseEntity.ok(response);
        }

        response.put("success", true);
        response.put("payments", searchPayment);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody List<Map<String, Object>> reservations) {
        Map<String, Object> response = new HashMap<>();

        try {
            paymentService.processPayment(reservations);
            response.put("success", true);
            response.put("message", "결제가 완료되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "결제 처리 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/cancel/{paymentCode}")
    public ResponseEntity<Map<String, Object>> cancelPayment(@PathVariable int paymentCode) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean isCanceled = paymentService.cancelPayment(paymentCode);
            if (!isCanceled) {
                response.put("success", false);
                response.put("message", "결제 취소 실패: 이미 취소된 결제입니다.");
                return ResponseEntity.ok(response);
            }
            response.put("success", true);
            response.put("message", "결제가 취소되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "결제 취소 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
