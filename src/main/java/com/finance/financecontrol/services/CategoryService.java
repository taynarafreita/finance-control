package com.finance.financecontrol.services;

import com.finance.financecontrol.dtos.responses.CategoryDtoResponse;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.mappers.CategoryMapper;
import com.finance.financecontrol.mappers.TransactionMapper;
import com.finance.financecontrol.models.Category;
import com.finance.financecontrol.models.Transaction;
import com.finance.financecontrol.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDtoResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
