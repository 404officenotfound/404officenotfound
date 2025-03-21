package com.office.notfound.payment.model.service;

import com.office.notfound.payment.model.dao.PaymentMapper;
import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.payment.model.dto.ReservationPayment;
import com.office.notfound.reservation.model.dao.ReservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentMapper paymentMapper;
    private final PortOneService portOneService;
    private final ReservationMapper reservationMapper;

    @Autowired
    public PaymentService(PaymentMapper paymentMapper, PortOneService portOneService, ReservationMapper reservationMapper) {
        this.paymentMapper = paymentMapper;
        this.portOneService = portOneService;
        this.reservationMapper = reservationMapper;
    }

    /**
     * 🔹 로그인한 회원의 전체 결제 내역 조회
     */

    public List<PaymentDTO> findAllPayments(Integer memberCode) {

        if (memberCode == null) {
            return paymentMapper.findAllPayments(null); // 모든 회원 조회
        }
        return paymentMapper.findAllPayments(memberCode);
    }


    public List<PaymentDTO> searchPayment(Integer memberCode, Integer paymentCode, String paymentDate, String startDate, String endDate) {
        System.out.println("🔍 [DEBUG] 검색 조건 - memberCode: " + memberCode + ", paymentCode: " + paymentCode + ", paymentDate: " + paymentDate + ", startDate: " + startDate + ", endDate: " + endDate);
        return paymentMapper.searchPayment(memberCode, paymentCode, paymentDate, startDate, endDate);
    }

    public List<PaymentDTO> searchAdminPayment(Integer paymentCode,Integer memberCode, String paymentDate, String startDate, String endDate) {
        return paymentMapper.searchAdminPayment(paymentCode, memberCode, paymentDate, startDate, endDate);
    }

    /**
     * 🔹 관리자: 모든 결제 내역 조회
     */
    public List<PaymentDTO> findAllPaymentsForAdmin() {
        return paymentMapper.findAllPayments(null); // 모든 회원 조회
    }

    public void processPayment(PaymentDTO paymentDTO) {

        paymentDTO.parseJson();

        // 🔹 결제 정보 저장 (먼저 payment 테이블에 insert)
        paymentMapper.insertPayment(paymentDTO);


        // 🔹 paymentCode가 정상적으로 설정되었는지 확인
        Integer paymentCode = paymentDTO.getPaymentCode();
        if (paymentCode == null || paymentCode <= 0) {
            throw new RuntimeException("결제 코드 생성 실패");
        }

        // 🔹 여러 개의 예약을 저장 (tbl_reservation_payment에 데이터 추가)
        if (paymentDTO.getReservations() != null) {
            for (ReservationPayment res : paymentDTO.getReservations()) {
                Map<String, Integer> paramMap = new HashMap<>();
                paramMap.put("reservationCode", res.getReservationCode());
                paramMap.put("paymentCode", paymentCode);
                paymentMapper.insertReservationPayment(paramMap);
            }
        }
    }

    // 포트원 결제 검증
    private void verifyPortOnePayment(String impUid, int amount) {
        try {
            // 포트원 서버에서 결제 정보 조회
            String token = portOneService.getAccessToken();
            Map<String, Object> paymentData = portOneService.getPaymentData(impUid, token);

            // 결제 금액 검증
            int paidAmount = (int) paymentData.get("amount");
            if (paidAmount != amount) {
                throw new RuntimeException("결제 금액이 일치하지 않습니다.");
            }

            // 결제 상태 검증
            String status = (String) paymentData.get("status");
            if (!"paid".equals(status)) {
                throw new RuntimeException("결제가 정상적으로 완료되지 않았습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException("결제 검증 실패: " + e.getMessage());
        }
    }

    // 결제 롤백 처리
    private void rollbackPayment(String impUid, int amount, String reason) {
        try {
            // 포트원 결제 취소 API 호출
            portOneService.cancelPayment(impUid, amount);
        } catch (Exception e) {
            // 롤백 실패 로그 기록
            log.error("Payment rollback failed for impUid: " + impUid + ", reason: " + reason, e);
        }
    }

    public boolean validatePaymentAmount(PaymentDTO request) {
        int totalAmount = request.getReservations().stream()
                .mapToInt(ReservationPayment::getPrice)
                .sum();
        return totalAmount == request.getPaymentAmount();
    }

    /**
     * 🔹 결제 취소 서비스 로직
     */
    @Transactional
    public boolean cancelPayment(int paymentCode) {
        try {
            // 1. 결제 정보 조회
            PaymentDTO payment = paymentMapper.findPaymentById(paymentCode);
            if (payment == null) {
                throw new RuntimeException("해당 결제 정보를 찾을 수 없습니다.");
            }

            // 2. 이미 취소된 결제인지 확인
            if ("결제취소".equals(payment.getPaymentStatus())) {
                return false;
            }

            // 3. 포트원 API를 통한 결제 취소
            String cancelResponse = portOneService.cancelPayment(payment.getImpUid(), payment.getPaymentAmount());
            if (cancelResponse == null || cancelResponse.isEmpty()) {
                throw new RuntimeException("결제 취소 요청 실패");
            }

            // 4. DB에서 결제 상태 업데이트
            paymentMapper.updatePaymentStatus(paymentCode, "결제취소");

            // 5. 연결된 예약 상태도 취소로 업데이트
            List<Integer> reservationCodes = paymentMapper.getReservationCodesByPayment(paymentCode);
            for (Integer reservationCode : reservationCodes) {
                reservationMapper.updateReservationStatus(reservationCode, "예약취소");
            }

            return true;
        } catch (Exception e) {
            throw new RuntimeException("결제 취소 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}


