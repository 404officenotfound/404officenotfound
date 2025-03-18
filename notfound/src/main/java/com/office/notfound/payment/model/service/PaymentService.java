package com.office.notfound.payment.model.service;

import com.office.notfound.payment.model.dao.PaymentMapper;
import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.reservation.model.dao.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PaymentService {

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
        // 🔥 memberCode가 null이면 전체 조회
        if (memberCode == null) {
            return paymentMapper.findAllPayments(null); // 모든 회원 조회
        }
        return paymentMapper.findAllPayments(memberCode);
    }

    public List<PaymentDTO> searchPayment(Integer memberCode, String paymentDate, String startDate, String endDate) {
        return paymentMapper.searchPayment(memberCode, paymentDate, startDate, endDate);
    }

    /**
     * 🔹 관리자: 모든 결제 내역 조회
     */
    public List<PaymentDTO> findAllPaymentsForAdmin() {
        return paymentMapper.findAllPayments(null); // 모든 회원 조회
    }

    @Transactional
    public void processPayment(List<Map<String, Object>> reservations) {
        try {
            for (Map<String, Object> res : reservations) {
                int reservationCode = (int) res.get("reservationCode");
                String paymentMethod = (String) res.getOrDefault("paymentMethod", "카드");
                int paymentAmount = paymentMapper.getReservationPrice(reservationCode);

                if (paymentAmount <= 0) {
                    throw new RuntimeException("유효하지 않은 예약 가격입니다. reservationCode: " + reservationCode);
                }

                // 가맹점 주문번호 생성
                String merchantUid = "ORD-" + System.currentTimeMillis();

                // PortOne API 호출
                String apiResponse = portOneService.requestPayment(merchantUid, paymentAmount, paymentMethod);

                // 결제 정보 저장
                PaymentDTO payment = new PaymentDTO();
                payment.setPaymentDate(LocalDateTime.now());
                payment.setPaymentMethod(paymentMethod);
                payment.setPaymentAmount(paymentAmount);
                payment.setPaymentStatus("결제완료");
                payment.setMerchantUid(merchantUid);
                payment.setApiParm(apiResponse);

                // 결제 정보 저장 및 예약-결제 연결 정보 저장
                paymentMapper.insertPayment(payment);
                paymentMapper.insertReservationPayment(reservationCode, payment.getPaymentCode());

                // 예약 상태 업데이트
                reservationMapper.updateReservationStatus(reservationCode, "결제완료");
            }
        } catch (Exception e) {
            // 결제 실패 시 롤백
            throw new RuntimeException("결제 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 🔹 결제 취소 서비스 로직
     */
    @Transactional
    public boolean cancelPayment(int paymentCode) {
        try {
            // 🔥 1. 결제 정보 조회
            PaymentDTO payment = paymentMapper.findPaymentById(paymentCode);
            if (payment == null) {
                throw new RuntimeException("해당 결제 정보를 찾을 수 없습니다.");
            }

            // 🔥 2. 이미 취소된 결제인지 확인
            if ("결제취소".equals(payment.getPaymentStatus())) {
                return false;
            }

            // 🔥 3. PortOne API를 사용하여 결제 취소 요청
            String cancelResponse = portOneService.cancelPayment(payment.getImpUid(), payment.getPaymentAmount());
            if (cancelResponse == null || cancelResponse.isEmpty()) {
                throw new RuntimeException("결제 취소 요청 실패");
            }

            // 🔥 4. DB에서 결제 상태 업데이트
            paymentMapper.updatePaymentStatus(paymentCode, "결제취소");
            
            // 🔥 5. 연결된 예약 상태도 취소로 업데이트
            int reservationCode = paymentMapper.getReservationCodeByPayment(paymentCode);
            if (reservationCode > 0) {
                reservationMapper.updateReservationStatus(reservationCode, "예약취소");
            }

            return true;
        } catch (Exception e) {
            throw new RuntimeException("결제 취소 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}


