package com.finance.financecontrol.mappers;

import com.finance.financecontrol.dtos.requests.CategoryDtoRequest;
import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.requests.UserDtoRequest;
import com.finance.financecontrol.dtos.responses.CategoryDtoResponse;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.dtos.responses.UserDtoResponse;
import com.finance.financecontrol.models.Transaction;
import com.finance.financecontrol.models.Category;

import com.finance.financecontrol.models.User;
import com.finance.financecontrol.repositories.CategoryRepository;
import com.finance.financecontrol.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransactionMapper {

    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    public TransactionMapper(CategoryRepository categoryRepository, UserRepository userRepository) {
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
    }

    public Transaction toEntity(TransactionDtoRequest dto) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());

        User user = mapDtoToUser(dto.getUserId());
        transaction.setUserId(user);

        transaction.setTransactionDescription(dto.getTransactionDescription());

        Category category = mapDtoToCategory(dto.getCategory());
        transaction.setCategoryId(category);

        transaction.setExpenseType(dto.getExpenseType());
        transaction.setDueDate(LocalDateTime.now());
        transaction.setAmount(dto.getAmount());
        transaction.setComments(dto.getComments());
        transaction.setCreatedAt(LocalDateTime.now());
        return transaction;
    }

    public TransactionDtoResponse toResponseDTO(Transaction transaction) {
        return new TransactionDtoResponse(
                transaction.getId(),
                mapUserIdToDto(transaction.getUserId()),
                transaction.getTransactionDescription(),
                mapCategoryIdToDto(transaction.getCategoryId()),
                transaction.getExpenseType(),
                transaction.getDueDate(),
                transaction.getAmount(),
                transaction.getComments(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
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

    private User mapDtoToUser(UserDtoRequest userDto) {
        if (userDto != null && userDto.getId() != null) {
            User user = userRepository.findById(userDto.getId()).orElse(null);
            return user;
        }
        return null;
    }

    private UserDtoResponse mapUserIdToDto(User user) {
        return (user != null) ?
                new UserDtoResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword())
                : null;
    }
}
