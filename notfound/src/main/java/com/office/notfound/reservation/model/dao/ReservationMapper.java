package com.office.notfound.reservation.model.dao;

import com.office.notfound.reservation.model.dto.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

 @Mapper
 public interface ReservationMapper {

  // 🔹 로그인한 사용자의 모든 예약 조회
  List<ReservationDTO> findAllReservationByMember(@Param("memberCode") Integer memberCode);

  // 🔹 로그인한 사용자의 특정 예약 검색
  List<ReservationDTO> searchReservationByMember(@Param("memberCode") Integer memberCode,
                                                 @Param("reservationCode") Integer reservationCode,
                                                 @Param("reservationDate") String reservationDate,
                                                 @Param("startDatetime") String startDatetime,
                                                 @Param("endDatetime") String endDatetime);

  // 🔹 로그인한 사용자의 특정 예약 취소
  void cancelReservationsByMember(@Param("memberCode") Integer memberCode,
                                  @Param("reservationCodes") List<Integer> reservationCodes);

  // 🔹 관리자: 모든 예약 조회
  List<ReservationDTO> adminFindAllReservation();

  // 🔹 관리자: 특정 조건으로 예약 검색
  List<ReservationDTO> searchAdminReservation(@Param("reservationCode") Integer reservationCode,
                                              @Param("memberCode") Integer memberCode,
                                              @Param("reservationDate") String reservationDate,
                                              @Param("startDatetime") String startDatetime,
                                              @Param("endDatetime") String endDatetime);

  // 🔹 관리자: '예약취소' 상태인 예약을 삭제
  void deleteReservations(@Param("reservationCodes") List<Integer> reservationCodes);

  // 🔹 일정 기간 지난 '예약취소' 상태의 예약 자동 삭제
  int deleteOldCanceledReservations();
 }
