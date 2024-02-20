package com.finance.financecontrol.exceptions.http;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super("Internal server error");
    }
}
