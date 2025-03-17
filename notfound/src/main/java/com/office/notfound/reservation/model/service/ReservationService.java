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
    public ReservationService(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDTO> findAllReservation() {
        return reservationMapper.findAllReservation();
    }

    public List<ReservationDTO> searchReservation(Integer reservationCodeInt, String reservationDate, String startDatetime, String endDatetime) {
        return reservationMapper.searchReservation(reservationCodeInt, reservationDate, startDatetime, endDatetime);


    }

    public void cancelReservation(int reservationCode) {
        String status = "예약취소";
        reservationMapper.updateReservationStatus(status, reservationCode);
    }

    public int deleteOldCanceledReservations() {
        return reservationMapper.deleteOldCanceledReservations();
    }

    public List<ReservationDTO> searchAdminReservation(Integer reservationCodeInt, Integer memberCodeInt, String reservationDate, String startDatetime, String endDatetime) {
        return reservationMapper.searchAdminReservation(reservationCodeInt, memberCodeInt, reservationDate, startDatetime, endDatetime);
    }

    public void deleteReservations(List<Integer> reservationCodes) {
        if (reservationCodes != null && !reservationCodes.isEmpty()) {
            reservationMapper.deleteReservations(reservationCodes);
        }
    }

    // 로그인한 사용자의 예약 조회
    public List<ReservationDTO> findAllReservationByMember(Integer memberCode) {
        return reservationMapper.findAllReservationByMember(memberCode);
    }

    // 로그인한 사용자의 예약 검색
    public List<ReservationDTO> searchReservationByMember(Integer memberCode, Integer reservationCodeInt, String reservationDate, String startDatetime, String endDatetime) {
        return reservationMapper.searchReservationByMember(memberCode, reservationCodeInt, reservationDate, startDatetime, endDatetime);
    }

    // 로그인한 사용자의 예약 취소
    public void cancelMemberReservations(Integer memberCode, List<Integer> reservationCodes) {
        reservationMapper.cancelReservationsByMember(memberCode, reservationCodes);

    }
}