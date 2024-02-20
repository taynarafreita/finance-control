package com.finance.financecontrol.enums;

public enum ExpenseTypeEnum {

    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String value;

    ExpenseTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
