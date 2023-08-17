package com.app.birca.exception.jwt;

import com.app.birca.exception.BircaException;

public class TokenNotFoundException extends BircaException {

    private static final String MESSAGE = "Token이 없습니다.";

    public TokenNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
