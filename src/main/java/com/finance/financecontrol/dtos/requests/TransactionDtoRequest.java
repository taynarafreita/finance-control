package com.finance.financecontrol.dtos.requests;

import com.finance.financecontrol.enums.ExpenseType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionDtoRequest {

    private String userId;

    @NotNull(message = "is required")
    private String transactionDescription;

    @NotNull(message = "is required")
    private String category;

    @NotNull(message = "is required")
    private ExpenseType expenseType;

    private LocalDateTime dueDate;

    @NotNull(message = "is required")
    private BigDecimal amount;

    private String comments;

    public TransactionDtoRequest() {}

    public TransactionDtoRequest(String userId, String transactionDescription, String category, ExpenseType expenseType, LocalDateTime dueDate, BigDecimal amount, String comments) {
        this.userId = userId;
        this.transactionDescription = transactionDescription;
        this.category = category;
        this.expenseType = expenseType;
        this.dueDate = dueDate;
        this.amount = amount;
        this.comments = comments;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
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