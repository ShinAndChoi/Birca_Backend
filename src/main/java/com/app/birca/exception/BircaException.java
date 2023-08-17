package com.app.birca.exception;

public abstract class BircaException extends RuntimeException {

    public BircaException(String message) {
        super(message);
    }

    public abstract int getStatusCode();

}
