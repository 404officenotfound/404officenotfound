package com.office.notfound.payment.model.dto;

public class ReservationPayment {

    private int reservationCode;  // 예약 번호
    private int price;            // 예약 금액

    public ReservationPayment() {}

    public ReservationPayment(int reservationCode, int price) {
        this.reservationCode = reservationCode;
        this.price = price;
    }

    public int getReservationCode() { return reservationCode; }
    public void setReservationCode(int reservationCode) { this.reservationCode = reservationCode; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        return "ReservationPayment{" +
                "reservationCode=" + reservationCode +
                ", price=" + price +
                '}';
    }
}
