package com.app.birca.exception.jwt;


import com.app.birca.exception.BircaException;

public class RefreshTokenExpiredException extends BircaException {

    private static final String MESSAGE = "RefreshToken이 만료되었습니다.";

    public RefreshTokenExpiredException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 498;
    }

}
