package com.app.birca.exception.businesslicense;

import com.app.birca.exception.BircaException;

public class DuplicationRegistrationNumber extends BircaException {

    private static final String MESSAGE = "이미 등록된 사업자등록번호입니다.";

    public DuplicationRegistrationNumber() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }

}
