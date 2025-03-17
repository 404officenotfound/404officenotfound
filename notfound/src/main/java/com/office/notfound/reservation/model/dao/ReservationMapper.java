package com.office.notfound.reservation.model.dao;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReservationMapper {
    List<ReservationDTO> findAllReservation();

    List<ReservationDTO> searchReservation(Integer reservationCodeInt, String reservationDate, String startDatetime, String endDatetime);

    int deleteOldCanceledReservations();

    void updateReservationStatus(String status, int reservationCode);

    List<ReservationDTO> searchAdminReservation(Integer reservationCodeInt, Integer memberCodeInt, String reservationDate, String startDatetime, String endDatetime);

    void deleteReservations(@Param("reservationCodes") List<Integer> reservationCodes);
}

