package com.office.notfound.reservation.controller;

import com.office.notfound.member.model.dto.MemberDTO;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.service.ReservationService;
import com.office.notfound.samusil.model.dto.OfficeDTO;
import com.office.notfound.samusil.model.service.OfficeService;
import com.office.notfound.store.model.dto.StoreDTO;
import com.office.notfound.store.model.service.StoreService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    @GetMapping("/register")
    public String showRegisterForm(@RequestParam int storeCode,
                                 @RequestParam int officeCode,
                                 Model model,
                                 @AuthenticationPrincipal MemberDTO member) {
        if (member == null) {
            return "redirect:/auth/login";
        }

        // 매장 정보 조회
        StoreDTO store = storeService.findStoreByCode(storeCode);
        
        // 사무실 정보 조회
        OfficeDTO office = officeService.findOfficeDetail(officeCode);

        if (office == null) {
            System.out.println("❌ Office 객체가 null입니다. officeCode: " + officeCode);
            model.addAttribute("errorMessage", "해당 사무실 정보를 찾을 수 없습니다.");
            return "error-page";
        }

        model.addAttribute("store", store);
        model.addAttribute("office", office);
        
        return "reservation/register";
    }

    @GetMapping("/modify/{reservationCode}")
    public String showModifyForm(@PathVariable Integer reservationCode, Model model) {
        ReservationDTO reservation = reservationService.getReservation(reservationCode);
        model.addAttribute("reservation", reservation);
        return "reservation/modify";
    }

    @PostMapping("/modify/{reservationCode}")
    public String modifyReservation(@PathVariable Integer reservationCode,
                                    @AuthenticationPrincipal MemberDTO member,
                                    @ModelAttribute ReservationDTO modifiedReservation,
                                    RedirectAttributes redirectAttributes) {
        try {
            // 예약 수정 처리
            reservationService.modifyReservation(modifiedReservation);
            redirectAttributes.addFlashAttribute("message", "예약이 성공적으로 수정되었습니다.");

            return "redirect:/reservation/search";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/reservation/modify/" + reservationCode;
        }
    }
}
