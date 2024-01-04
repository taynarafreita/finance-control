package com.finance.financecontrol.enums;

public enum ExpenseType {

    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String value;

    ExpenseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
