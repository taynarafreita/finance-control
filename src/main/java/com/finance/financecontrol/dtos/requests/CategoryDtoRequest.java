package com.finance.financecontrol.dtos.requests;

import jakarta.validation.constraints.NotNull;

public class CategoryDtoRequest {

    private Long id;

    @NotNull(message = "is required")
    private String description;

    public CategoryDtoRequest() {}

    public CategoryDtoRequest(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
