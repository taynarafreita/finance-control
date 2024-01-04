package com.finance.financecontrol.exceptions;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
