package com.app.birca.exception.businesslicense;

import com.app.birca.exception.BircaException;

public class BusinessRegistrationPendingException extends BircaException {

    private static final String MESSAGE = "사업자등록증 심사가 통과되지 않았습니다.";

    public BusinessRegistrationPendingException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 422;
    }

}
