package com.finance.financecontrol.validations;

import com.finance.financecontrol.annotations.ValidExpenseTypeEnum;
import com.finance.financecontrol.enums.ExpenseTypeEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpenseTypeEnumValidator implements ConstraintValidator<ValidExpenseTypeEnum, ExpenseTypeEnum> {

    @Override
    public void initialize(ValidExpenseTypeEnum constraintAnnotation) {}

    @Override
    public boolean isValid(ExpenseTypeEnum value, ConstraintValidatorContext context) {
        for (ExpenseTypeEnum expenseTypeEnum : ExpenseTypeEnum.values()) {
            if (expenseTypeEnum == value) {
                return true;
            }
        }
        return false;
    }
}
