package com.office.notfound.reservation.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.dto.ReservationStatus;
import com.office.notfound.reservation.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    /**
     * 🔹 예약 가능한 시간대 및 예약된 시간 조회 (Rest API)
     */
    @PostMapping("/available-times")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAvailableTimes(@RequestBody Map<String, Object> request) {
        try {
            System.out.println("🔍 요청 데이터: " + request); // 디버깅 로그 추가

            // officeCode와 date 유효성 검사
            if (!request.containsKey("officeCode") || !request.containsKey("date")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }

            // officeCode가 String으로 들어올 경우 변환 처리
            int officeCode = Integer.parseInt(request.get("officeCode").toString());
            String date = request.get("date").toString();

            // 예약 가능한 시간 조회
            List<String> availableTimes = reservationService.getAvailableTimeSlots(officeCode, date);
            // 예약된 시간 조회
            List<String> bookedTimes = reservationService.getBookedTimeSlots(officeCode, date);

            if (availableTimes == null) {
                availableTimes = List.of(); // 빈 리스트 반환
            }
            if (bookedTimes == null) {
                bookedTimes = List.of(); // 빈 리스트 반환
            }

            // 결과 맵 생성
            Map<String, Object> response = new HashMap<>();
            response.put("availableTimes", availableTimes);
            response.put("bookedTimes", bookedTimes);

            System.out.println("✅ 반환할 예약 가능 시간대: " + availableTimes);
            System.out.println("🚫 예약된 시간대: " + bookedTimes);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("❌ 예약 가능 시간 조회 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /**
     * 🔹 예약 등록 처리 (Rest API)
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerReservations(
            @RequestBody Map<String, Object> request,
            @AuthenticationPrincipal MemberDTO member) {

        Map<String, Object> response = new HashMap<>();

        if (member == null) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        try {
            List<Map<String, Object>> reservations = (List<Map<String, Object>>) request.get("reservations");

            if (reservations == null || reservations.isEmpty()) {
                response.put("success", false);
                response.put("message", "예약 데이터가 없습니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            for (Map<String, Object> res : reservations) {
                ReservationDTO reservation = new ReservationDTO();
                reservation.setMemberCode(member.getMemberCode());
                reservation.setOfficeCode(Integer.parseInt(res.get("officeCode").toString()));

                String dateString = res.get("reservationDate").toString();
                LocalDateTime reservationDateTime = LocalDate.parse(dateString).atStartOfDay().plusHours(9);
                reservation.setReservationDate(reservationDateTime);

                reservation.setStartDatetime(LocalDateTime.parse(res.get("reservationDate") + "T" + res.get("startTime")));
                reservation.setEndDatetime(LocalDateTime.parse(res.get("reservationDate") + "T" + res.get("endTime")));

                reservation.setTotalPrice(Integer.parseInt(res.get("totalPrice").toString()));
                reservation.setReservationStatus(ReservationStatus.예약완료);

                // 🔹 여러 개의 예약을 하나씩 저장
                reservationService.registerReservation(reservation);
            }

            response.put("success", true);
            response.put("message", "모든 예약이 완료되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "예약 처리 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
