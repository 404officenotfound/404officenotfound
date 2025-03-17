package com.office.notfound.reservation.model.service;

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

    /**
     * 🔹 로그인한 사용자의 예약 전체 조회 (일반 사용자: 본인 예약만 / 관리자: 전체 조회)
     */
    public List<ReservationDTO> findAllReservations(Integer memberCode, boolean isAdmin) {
        if (isAdmin) {
            return reservationMapper.adminFindAllReservation();
        }
        return reservationMapper.findAllReservationByMember(memberCode);
    }

    /**
     * 🔹 로그인한 사용자의 특정 조건으로 예약 검색 (일반 사용자: 본인 예약만 / 관리자: 전체 조회)
     */
    public List<ReservationDTO> searchReservations(Integer memberCode, boolean isAdmin,
                                                   String reservationCode, String reservationDate,
                                                   String startDatetime, String endDatetime) {
        Integer reservationCodeInt = null;

        // 예약번호가 입력된 경우 Integer 변환
        if (reservationCode != null && !reservationCode.isEmpty()) {
            try {
                reservationCodeInt = Integer.parseInt(reservationCode);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("예약번호는 숫자로 입력해야 합니다.");
            }
        }

        if (isAdmin) {
            return reservationMapper.searchAdminReservation(reservationCodeInt, memberCode, reservationDate, startDatetime, endDatetime);
        }
        return reservationMapper.searchReservationByMember(memberCode, reservationCodeInt, reservationDate, startDatetime, endDatetime);
    }

    /**
     * 🔹 로그인한 사용자의 예약 취소 (본인 예약만 가능)
     */
    public void cancelMemberReservations(Integer memberCode, List<Integer> reservationCodes) {
        if (reservationCodes != null && !reservationCodes.isEmpty()) {
            reservationMapper.cancelReservationsByMember(memberCode, reservationCodes);
        }
    }

    /**
     * 🔹 관리자가 특정 예약을 삭제 (예약 취소된 건만 삭제 가능)
     */
    public void deleteReservations(List<Integer> reservationCodes) {
        if (reservationCodes != null && !reservationCodes.isEmpty()) {
            reservationMapper.deleteReservations(reservationCodes);
        }
    }

    /**
     * 🔹 일정 기간 지난 '예약취소' 상태의 예약 자동 삭제
     */
    public int deleteOldCanceledReservations() {
        return reservationMapper.deleteOldCanceledReservations();
    }
}
