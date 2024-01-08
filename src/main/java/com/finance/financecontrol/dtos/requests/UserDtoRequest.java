package com.finance.financecontrol.dtos.requests;

import com.finance.financecontrol.enums.ExpenseType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class UserDtoRequest {

    private UUID id;

    @NotNull(message = "is required")
    private String name;

    @NotNull(message = "is required")
    private String email;

    @NotNull(message = "is required")
    private String username;

    @NotNull(message = "is required")
    private String password;

    public UserDtoRequest() {}

    public UserDtoRequest(UUID id, String name, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
