package com.office.notfound.reservation.model.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

  private  int reservationCode;
  private  int memberCode;
  private  int officeCode;
  private String storeName;    // 매핑 대상
  private  LocalDateTime reservationDate;
  private  LocalDateTime startDatetime;
  private  LocalDateTime endDatetime;
  private  ReservationStatus reservationStatus;
  private  int totalPrice;

  public ReservationDTO() {
  }

  public ReservationDTO(int reservationCode, int memberCode, int officeCode, String storeName, LocalDateTime reservationDate, LocalDateTime startDatetime, LocalDateTime endDatetime, ReservationStatus reservationStatus, int totalPrice) {
    this.reservationCode = reservationCode;
    this.memberCode = memberCode;
    this.officeCode = officeCode;
    this.storeName = storeName;
    this.reservationDate = reservationDate;
    this.startDatetime = startDatetime;
    this.endDatetime = endDatetime;
    this.reservationStatus = reservationStatus;
    this.totalPrice = totalPrice;
  }

  public int getReservationCode() {
    return reservationCode;
  }

  public void setReservationCode(int reservationCode) {
    this.reservationCode = reservationCode;
  }

  public int getMemberCode() {
    return memberCode;
  }

  public void setMemberCode(int memberCode) {
    this.memberCode = memberCode;
  }

  public int getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(int officeCode) {
    this.officeCode = officeCode;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public LocalDateTime getReservationDate() {
    return reservationDate;
  }

  public void setReservationDate(LocalDateTime reservationDate) {
    this.reservationDate = reservationDate;
  }

  public LocalDateTime getStartDatetime() {
    return startDatetime;
  }

  public void setStartDatetime(LocalDateTime startDatetime) {
    this.startDatetime = startDatetime;
  }

  public LocalDateTime getEndDatetime() {
    return endDatetime;
  }

  public void setEndDatetime(LocalDateTime endDatetime) {
    this.endDatetime = endDatetime;
  }

  public ReservationStatus getReservationStatus() {
    return reservationStatus;
  }

  public void setReservationStatus(ReservationStatus reservationStatus) {
    this.reservationStatus = reservationStatus;
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public String toString() {
    return "ReservationDTO{" +
            "reservationCode=" + reservationCode +
            ", memberCode=" + memberCode +
            ", officeCode=" + officeCode +
            ", storeName='" + storeName + '\'' +
            ", reservationDate=" + reservationDate +
            ", startDatetime=" + startDatetime +
            ", endDatetime=" + endDatetime +
            ", reservationStatus=" + reservationStatus +
            ", totalPrice=" + totalPrice +
            '}';
  }
}
