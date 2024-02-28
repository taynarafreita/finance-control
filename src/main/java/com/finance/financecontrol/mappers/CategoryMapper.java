package com.finance.financecontrol.mappers;

import com.finance.financecontrol.dtos.requests.CategoryDtoRequest;
import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.requests.UserDtoRequest;
import com.finance.financecontrol.dtos.responses.CategoryDtoResponse;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.dtos.responses.UserDtoResponse;
import com.finance.financecontrol.models.Category;
import com.finance.financecontrol.models.Transaction;
import com.finance.financecontrol.models.User;
import com.finance.financecontrol.repositories.CategoryRepository;
import com.finance.financecontrol.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CategoryMapper {

    private CategoryRepository categoryRepository;

    public CategoryMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category toEntity(CategoryDtoRequest dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setDescription(dto.getDescription());

        return category;
    }

    public CategoryDtoResponse toResponseDTO(Category category) {
        return new CategoryDtoResponse(
                category.getId(),
                category.getDescription()
        );
    }

    private Category mapDtoToCategory(CategoryDtoRequest categoryDto) {
        if (categoryDto != null) {
            Long categoryId = categoryDto.getId();
            Category category = categoryRepository.findById(Math.toIntExact(categoryId)).orElse(null);

            return category;
        }
        return null;
    }

    private CategoryDtoResponse mapCategoryIdToDto(Category category) {
        return (category != null) ? new CategoryDtoResponse(category.getId(), category.getDescription()) : null;
    }

}
