package com.office.notfound.payment.controller;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * 🔹 결제 처리 API
     */
    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processPayment(@RequestBody PaymentDTO paymentDTO) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 🔹 JSON 데이터를 DTO로 변환 후 개별 필드로 DB에 저장
            paymentService.processPayment(paymentDTO);

            response.put("success", true);
            response.put("message", "결제가 완료되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "결제 처리 중 오류 발생: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 🔹 결제 취소 API (AJAX 요청용)
     */
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
