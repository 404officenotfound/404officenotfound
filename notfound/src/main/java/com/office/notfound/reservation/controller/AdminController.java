package com.office.notfound.reservation.controller;

import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ReservationService reservationService;

    public AdminController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation/search")
    public String searchReservation(
            @RequestParam(required = false) String reservationCode,
            @RequestParam(required = false) Integer memberCode,
            @RequestParam(required = false) String reservationDate,
            @RequestParam(required = false) String startDatetime,
            @RequestParam(required = false) String endDatetime,
            Model model) {

        // 현재 선택된 검색 유형을 판별
        String selectedSearchType = "reservationCode"; // 기본값: 예약번호 검색
        if (memberCode != null) {
            selectedSearchType = "memberCode";
        } else if (reservationDate != null && !reservationDate.isEmpty()) {
            selectedSearchType = "reservationDate";
        } else if (startDatetime != null && endDatetime != null && !startDatetime.isEmpty() && !endDatetime.isEmpty()) {
            selectedSearchType = "reservationPeriod";
        }

        // 검색이 실행되지 않은 경우 -> 결과 없이 반환
        if ((reservationCode == null || reservationCode.isEmpty()) &&
                memberCode == null &&
                (reservationDate == null || reservationDate.isEmpty()) &&
                (startDatetime == null || startDatetime.isEmpty()) &&
                (endDatetime == null || endDatetime.isEmpty())) {
            model.addAttribute("searchExecuted", false);
            return "admin/reservation/search";
        }

        // 예약번호 입력값 검증 (숫자가 아닌 경우 예외 처리)
        Integer reservationCodeInt = null;
        if (reservationCode != null && !reservationCode.isEmpty()) {
            try {
                reservationCodeInt = Integer.parseInt(reservationCode);
            } catch (NumberFormatException e) {
                model.addAttribute("noResultsMessage", "예약번호는 숫자로 입력해주세요.");
                model.addAttribute("searchExecuted", true);
                return "admin/reservation/search";
            }
        }

        // 검색 수행
        List<ReservationDTO> searchReservation = reservationService.searchAdminReservation(
                reservationCodeInt, memberCode, reservationDate, startDatetime, endDatetime);

        // 검색이 실행되었음을 표시
        model.addAttribute("searchExecuted", true);

        // 검색 결과가 없을 경우 메시지 추가
        if (searchReservation.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        model.addAttribute("selectedSearchType", selectedSearchType);
        model.addAttribute("searchReservation", searchReservation);
        return "admin/reservation/search";
    }

    @GetMapping("/reservation/search/all")
    public String getAllReservations(Model model) {
        List<ReservationDTO> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("searchExecuted", true);
        if (reservationList.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        return "admin/reservation/search";
    }

    @PostMapping("/reservation/cancel")
    public String cancelMultipleReservations(@RequestParam("reservationCodes") List<Integer> reservationCodes) {
        if (reservationCodes != null && !reservationCodes.isEmpty()) {
            for (int reservationCode : reservationCodes) {
                reservationService.cancelReservation(reservationCode);
            }
        }
        return "redirect:/admin/reservation/search";
    }

    @PostMapping("/reservation/delete-old")
    public String deleteOldReservations() {
        int deletedCount = reservationService.deleteOldCanceledReservations();
        return "redirect:/admin/reservation/search";
    }

        @PostMapping("/reservation/delete")
    public String deleteReservations(@RequestParam("reservationCodes") List<Integer> reservationCodes) {
        if (reservationCodes != null && !reservationCodes.isEmpty()) {
            reservationService.deleteReservations(reservationCodes);
        }
        return "redirect:/admin/reservation/search";
    }
} 