package com.finance.financecontrol.annotations;

import com.finance.financecontrol.validations.ExpenseTypeEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExpenseTypeEnumValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExpenseTypeEnum {
    String message() default "must be a valid ExpenseType enum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


