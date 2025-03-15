package com.office.notfound.reservation.controller;

import com.office.notfound.reservation.model.dto.ReservationDTO;
import com.office.notfound.reservation.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping ("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/search/all")
    public String search(Model model) {
        List<ReservationDTO> reservationList = reservationService.findAllReservation();
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("searchExecuted", true);
        if (reservationList.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색 결과가 없습니다.");
        }
        return "reservation/search";
    }
}
