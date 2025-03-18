package com.office.notfound.reservation.model.scheduler;

import com.office.notfound.reservation.model.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private final ReservationService reservationService;

    @Autowired
    public Scheduler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteOldCanceledReservations() {
        int deletedRows = reservationService.deleteOldCanceledReservations();
        System.out.println("삭제된 예약 건수: " + deletedRows);
    }

}
