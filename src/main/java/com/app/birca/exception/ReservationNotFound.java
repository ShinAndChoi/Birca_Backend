package com.app.birca.exception;

public class ReservationNotFound extends BircaException {

    private static final String MESSAGE = "예약이 존재하지 않습니다.";

    public ReservationNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
