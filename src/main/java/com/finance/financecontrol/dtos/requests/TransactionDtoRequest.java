package com.finance.financecontrol.dtos.requests;

import com.finance.financecontrol.annotations.ValidExpenseTypeEnum;
import com.finance.financecontrol.enums.ExpenseTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDtoRequest {

    private UserDtoRequest user;

    @NotNull(message = "is required")
    @Size(max = 250, message = "must have a maximum of 250 characters")
    private String transactionDescription;

    @NotNull(message = "is required")
    private CategoryDtoRequest category;

    @NotNull(message = "is required")
    @ValidExpenseTypeEnum(message = "must be a ExpenseTypeEnum")
    private ExpenseTypeEnum expenseType;

    private LocalDateTime dueDate;

    @NotNull(message = "is required")
    private BigDecimal amount;

    private String comments;

    public TransactionDtoRequest() {}

    public TransactionDtoRequest(UserDtoRequest user, String transactionDescription, CategoryDtoRequest category, ExpenseTypeEnum expenseType, LocalDateTime dueDate, BigDecimal amount, String comments) {
        this.user = user;
        this.transactionDescription = transactionDescription;
        this.category = category;
        this.expenseType = expenseType;
        this.dueDate = dueDate;
        this.amount = amount;
        this.comments = comments;
    }

    public UserDtoRequest getUser() {
        return user;
    }

    public void setUserId(UserDtoRequest user) {
        this.user = user;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public CategoryDtoRequest getCategory() {
        return category;
    }

    public void setCategory(CategoryDtoRequest category) {
        this.category = category;
    }

    public ExpenseTypeEnum getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseTypeEnum expenseType) {
        this.expenseType = expenseType;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
