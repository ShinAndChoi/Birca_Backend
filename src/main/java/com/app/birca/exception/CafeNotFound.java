package com.app.birca.exception;

public class CafeNotFound extends BircaException {

    private static final String MESSAGE = "존재하지 않는 카페 입니다.";

    public CafeNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
