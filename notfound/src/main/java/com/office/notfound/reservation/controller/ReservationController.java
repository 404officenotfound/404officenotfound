package com.office.notfound.reservation.controller;

import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.service.ReservationService;
import com.office.notfound.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * 🔹 로그인한 사용자의 예약 조회 (전체 조회)
     */
    @GetMapping("/search/all")
    public String search(Model model) {
        Integer memberCode = getLoggedInMemberCode();
        if (memberCode == null) {
            model.addAttribute("noResultsMessage", "로그인이 필요합니다.");
            return "reservation/search";
        }

        List<ReservationDTO> reservationList = reservationService.findAllReservationByMember(memberCode);
        model.addAttribute("reservationList", reservationList);
        return "reservation/search";
    }

    /**
     * 🔹 로그인한 사용자의 예약 검색 (조건 검색)
     */
    @GetMapping("/search")
    public String searchReservation(
            @RequestParam(required = false) String reservationCode,
            @RequestParam(required = false) String reservationDate,
            @RequestParam(required = false) String startDatetime,
            @RequestParam(required = false) String endDatetime,
            Model model) {

        Integer memberCode = getLoggedInMemberCode();
        if (memberCode == null) {
            model.addAttribute("noResultsMessage", "로그인이 필요합니다.");
            return "reservation/search";
        }

        Integer reservationCodeInt = null;
        if (reservationCode != null && !reservationCode.isEmpty()) {
            try {
                reservationCodeInt = Integer.parseInt(reservationCode);
            } catch (NumberFormatException e) {
                model.addAttribute("noResultsMessage", "예약번호는 숫자로 입력해주세요.");
                return "reservation/search";
            }
        }

        List<ReservationDTO> searchReservation = reservationService.searchReservationByMember(
                memberCode, reservationCodeInt, reservationDate, startDatetime, endDatetime);

        model.addAttribute("searchExecuted", true);
        if (searchReservation.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        model.addAttribute("searchReservation", searchReservation);
        return "reservation/search";
    }

    /**
     * 🔹 로그인한 사용자의 예약 취소
     */
    @PostMapping("/cancel-multiple")
    public String cancelMultipleReservations(@RequestParam("reservationCodes") List<Integer> reservationCodes) {
        Integer memberCode = getLoggedInMemberCode();
        if (memberCode != null) {
            reservationService.cancelMemberReservations(memberCode, reservationCodes);
        }
        return "redirect:/reservation/search";
    }

    /**
     * 🔹 현재 로그인한 사용자의 memberCode 가져오기
     */
    private Integer getLoggedInMemberCode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) authentication.getPrincipal()).getMemberCode();
        }
        return null;
    }
}
