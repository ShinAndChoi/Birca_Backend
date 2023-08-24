package com.app.birca.exception.businesslicense;

import com.app.birca.exception.BircaException;

public class BusinessRegistrationError extends BircaException {

    private static final String MESSAGE = "국세청에 등록되지 않은 사업자등록번호입니다.";

    public BusinessRegistrationError() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
