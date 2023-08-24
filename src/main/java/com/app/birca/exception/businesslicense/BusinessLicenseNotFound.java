package com.app.birca.exception.businesslicense;

import com.app.birca.exception.BircaException;

public class BusinessLicenseNotFound extends BircaException {

    private static final String MESSAGE = "사업자등록증이 존재하지 않습니다.";

    public BusinessLicenseNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
