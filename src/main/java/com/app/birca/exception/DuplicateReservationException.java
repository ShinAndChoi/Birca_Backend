package com.app.birca.exception;

public class DuplicateReservationException extends BircaException {

    private static final String MESSAGE = "이미 해당 날짜에 예약이 존재합니다.";

    public DuplicateReservationException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }

}
