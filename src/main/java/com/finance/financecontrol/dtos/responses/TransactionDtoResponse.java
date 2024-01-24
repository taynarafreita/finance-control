package com.finance.financecontrol.dtos.responses;

import com.finance.financecontrol.enums.ExpenseType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class TransactionDtoResponse {

    private UUID id;

    private UserDtoResponse user;

    private String transactionDescription;

    private CategoryDtoResponse category;

    private ExpenseType expenseType;

    private LocalDateTime dueDate;

    private BigDecimal amount;

    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public TransactionDtoResponse(UUID id, UserDtoResponse user, String transactionDescription, CategoryDtoResponse category, ExpenseType expenseType, LocalDateTime dueDate, BigDecimal amount, String comments, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.transactionDescription = transactionDescription;
        this.category = category;
        this.expenseType = expenseType;
        this.dueDate = dueDate;
        this.amount = amount;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TransactionDtoResponse() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserDtoResponse getUser() {
        return user;
    }

    public void setUser(UserDtoResponse user) {
        this.user = user;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public CategoryDtoResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryDtoResponse category) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
