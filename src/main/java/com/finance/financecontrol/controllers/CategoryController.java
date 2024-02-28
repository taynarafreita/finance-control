package com.finance.financecontrol.controllers;

import com.finance.financecontrol.dtos.responses.CategoryDtoResponse;
import com.finance.financecontrol.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDtoResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
