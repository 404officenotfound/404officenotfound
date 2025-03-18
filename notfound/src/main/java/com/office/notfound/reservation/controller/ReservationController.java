package com.office.notfound.reservation.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.service.ReservationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
}
