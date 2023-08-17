package com.app.birca.exception.jwt;

import com.app.birca.exception.BircaException;

public class Unauthorized extends BircaException {

    private static final String MESSAGE = "인증이 필요합니다.";

    public Unauthorized() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }

}
