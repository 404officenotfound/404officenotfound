package com.office.notfound.reservation.model.service;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.reservation.model.dao.ReservationMapper;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationMapper reservationMapper) {this.reservationMapper = reservationMapper;}

    public List<ReservationDTO> findAllReservation() { 
        return reservationMapper.findAllReservation();  // 메서드명 불일치
    }

    public List<PaymentDTO> searchReservation(Integer reservationCodeInt, String reservationDate, String startDatetime, String endDatetime) {
    return reservationMapper.searchReservation(reservationCodeInt,reservationDate,startDatetime,endDatetime);


    }
}
