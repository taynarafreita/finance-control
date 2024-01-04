package com.finance.financecontrol.mappers;

import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
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

        User user = mapStringToUser(dto.getUserId());
        transaction.setUserId(user);

        transaction.setTransactionDescription(dto.getTransactionDescription());

        Category category = mapStringToCategory(dto.getCategory());
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
                mapUserIdToString(transaction.getUserId()),
                transaction.getTransactionDescription(),
                mapCategoryIdToString(transaction.getCategoryId()),
                transaction.getExpenseType(),
                transaction.getDueDate(),
                transaction.getAmount(),
                transaction.getComments(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

    private Category mapStringToCategory(String categoryId) {
        if (categoryId != null) {
            Long categoryIdLong = Long.parseLong(categoryId);
            Category category = categoryRepository.findById(Math.toIntExact(categoryIdLong)).orElse(null);
            return category;
        }
        return null;
    }

    private String mapCategoryIdToString(Category category) {
        return (category != null) ? category.getId().toString() : null;
    }

    private User mapStringToUser(String userId) {
        if (userId != null) {
            User user = userRepository.findById(UUID.fromString(userId)).orElse(null);
            return user;
        }
        return null;
    }

    private String mapUserIdToString(User user) {
        return (user != null) ? user.getId().toString() : null;
    }
}
