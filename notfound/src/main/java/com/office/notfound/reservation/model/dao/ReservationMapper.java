package com.office.notfound.reservation.model.dao;

import com.office.notfound.payment.model.dto.PaymentDTO;
import com.office.notfound.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ReservationMapper {

    List<ReservationDTO> findAllReservationByMember(@Param("memberCode") Integer memberCode);

    List<ReservationDTO> searchReservationByMember(@Param("memberCode") Integer memberCode,
                                                   @Param("reservationCodeInt") Integer reservationCodeInt,
                                                   @Param("reservationDate") String reservationDate,
                                                   @Param("startDatetime") String startDatetime,
                                                   @Param("endDatetime") String endDatetime);

    void cancelReservationsByMember(@Param("memberCode") Integer memberCode,
                                    @Param("reservationCodes") List<Integer> reservationCodes);

    List<ReservationDTO> findAllReservation();

    List<ReservationDTO> searchReservation(Integer reservationCodeInt, String reservationDate, String startDatetime, String endDatetime);
    void updateReservationStatus(String status, int reservationCode);

    int deleteOldCanceledReservations();


    List<ReservationDTO> searchAdminReservation(Integer reservationCodeInt, Integer memberCodeInt, String reservationDate, String startDatetime, String endDatetime);

    void deleteReservations(@Param("reservationCodes") List<Integer> reservationCodes);
}

