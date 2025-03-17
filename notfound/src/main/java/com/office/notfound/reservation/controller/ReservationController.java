package com.office.notfound.reservation.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.service.ReservationService;
import com.office.notfound.store.model.dto.StoreDTO;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.store.model.service.StoreService;
import com.office.notfound.samusil.model.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final StoreService storeService;
    private final OfficeService officeService;

    public ReservationController(ReservationService reservationService, StoreService storeService, OfficeService officeService) {
        this.reservationService = reservationService;
        this.storeService = storeService;
        this.officeService = officeService;
    }

    /**
     * 🔹 회원 예약 전체 조회 (본인의 예약만)
     */
    @GetMapping("/search/all")
    public String getAllReservations(@AuthenticationPrincipal MemberDTO member, Model model) {

        // 로그인 여부 확인
        if (member == null) {
            return "redirect:/auth/login"; // 로그인되지 않은 경우 로그인 페이지로 리디렉트
        }

        // 현재 로그인한 사용자의 memberCode 가져오기
        int memberCode = member.getMemberCode();

        // 회원의 예약 내역 조회
        List<ReservationDTO> reservationList = reservationService.findAllReservations(memberCode, false);
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("searchExecuted", true);

        if (reservationList.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }

        return "reservation/search";
    }

    /**
     * 🔹 회원 예약 검색 (본인의 예약만)
     */
    @GetMapping("/search")
    public String searchReservation(
            @RequestParam(required = false) String reservationCode,
            @RequestParam(required = false) String reservationDate,
            @RequestParam(required = false) String startDatetime,
            @RequestParam(required = false) String endDatetime,
            Model model,
            @AuthenticationPrincipal MemberDTO member) {

        // 로그인 여부 확인
        if (member == null) {
            return "redirect:/auth/login"; // 로그인되지 않은 경우 로그인 페이지로 리디렉트
        }

        // 현재 로그인한 사용자의 memberCode 가져오기
        int memberCode = member.getMemberCode();

        // 검색이 실행되지 않은 경우
        if ((reservationCode == null || reservationCode.isEmpty()) &&
                (reservationDate == null || reservationDate.isEmpty()) &&
                (startDatetime == null || startDatetime.isEmpty()) &&
                (endDatetime == null || endDatetime.isEmpty())) {
            model.addAttribute("searchExecuted", false);
            return "reservation/search";
        }

        // 예약번호 입력값 검증 (숫자가 아닌 경우 예외 처리)
        Integer reservationCodeInt = null;
        if (reservationCode != null && !reservationCode.isEmpty()) {
            try {
                reservationCodeInt = Integer.parseInt(reservationCode);
            } catch (NumberFormatException e) {
                model.addAttribute("noResultsMessage", "예약번호는 숫자로 입력해주세요.");
                model.addAttribute("searchExecuted", true);
                return "reservation/search";
            }
        }

        // 검색 수행 (reservationCodeInt를 String으로 변환)
        List<ReservationDTO> searchReservation = reservationService.searchReservations(
                memberCode, false,
                reservationCodeInt != null ? String.valueOf(reservationCodeInt) : null,
                reservationDate, startDatetime, endDatetime);

        model.addAttribute("searchExecuted", true);
        if (searchReservation.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        model.addAttribute("searchReservation", searchReservation);

        return "reservation/search";
    }

    /**
     * 🔹 회원 예약 취소 (선택한 예약들)
     */
    @PostMapping("/cancel-multiple")
    public String cancelMultipleReservations(@RequestParam("reservationCodes") List<Integer> reservationCodes,
                                             @AuthenticationPrincipal MemberDTO member) {

        // 로그인 여부 확인
        if (member == null) {
            return "redirect:/auth/login"; // 로그인되지 않은 경우 로그인 페이지로 리디렉트
        }

        // 현재 로그인한 사용자의 memberCode 가져오기
        int memberCode = member.getMemberCode();

        // 선택한 예약들을 취소
        if (reservationCodes != null && !reservationCodes.isEmpty()) {
            reservationService.cancelMemberReservations(memberCode, reservationCodes);
        }

        return "redirect:/reservation/search";
    }

    /**
     * 🔹 예약 등록 페이지 이동
     */
    @GetMapping("/register/{officeCode}")
    public String showRegisterForm(@PathVariable int officeCode, Model model,
                                   @AuthenticationPrincipal MemberDTO member) {
        if (member == null) {
            return "redirect:/auth/login";
        }

        // 📌 디버깅 로그 추가
        System.out.println("📌 전달된 officeCode: " + officeCode);

        // officeCode가 0이면 잘못된 요청이므로 예외 발생
        if (officeCode <= 0) {
            throw new RuntimeException("📌 유효하지 않은 officeCode: " + officeCode);
        }

        OfficeDTO office = officeService.findOfficeDetail(officeCode);

        if (office == null) {
            throw new RuntimeException("📌 office가 null입니다. officeCode: " + officeCode);
        }

        StoreDTO store = storeService.findStoreByCode(office.getStoreCode());

        model.addAttribute("office", office);
        model.addAttribute("store", store);

        return "reservation/register";
    }


    /**
     * 🔹 예약 가능한 시간대 조회
     */
    @PostMapping("/api/reservations/available-times")
    @ResponseBody
    public ResponseEntity<List<String>> getAvailableTimes(@RequestBody Map<String, Object> request) {
        int officeCode = (int) request.get("officeCode");
        String date = (String) request.get("date");
        
        List<String> availableTimes = reservationService.getAvailableTimeSlots(officeCode, date);
        return ResponseEntity.ok(availableTimes);
    }

    /**
     * 🔹 예약 등록 처리
     */
    @PostMapping("/api/reservations/register")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> registerReservation(
            @RequestBody ReservationDTO reservation,
            @AuthenticationPrincipal MemberDTO member) {
        
        Map<String, Object> response = new HashMap<>();
        
        if (member == null) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        try {
            reservation.setMemberCode(member.getMemberCode());
            reservationService.registerReservation(reservation);
            
            response.put("success", true);
            response.put("message", "예약이 완료되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
