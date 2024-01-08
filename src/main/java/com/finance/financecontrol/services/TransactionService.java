package com.finance.financecontrol.services;

import com.finance.financecontrol.dtos.requests.CategoryDtoRequest;
import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.enums.ExpenseType;
import com.finance.financecontrol.exceptions.TransactionNotFoundException;
import com.finance.financecontrol.mappers.TransactionMapper;
import com.finance.financecontrol.models.Category;
import com.finance.financecontrol.models.Transaction;
import com.finance.financecontrol.repositories.CategoryRepository;
import com.finance.financecontrol.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private CategoryRepository categoryRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TransactionMapper transactionMapper,
                              CategoryRepository categoryRepository) {

        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<TransactionDtoResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TransactionDtoResponse createTransaction(TransactionDtoRequest transactionDto) {
        Transaction newTransaction = transactionMapper.toEntity(transactionDto);
        Transaction addedTransaction = transactionRepository.save(newTransaction);

        return transactionMapper.toResponseDTO(addedTransaction);
    }

    public TransactionDtoResponse updateTransaction(UUID transactionId,
                                                    TransactionDtoRequest updatedData) {
        Optional<Transaction> existingTransactionOptional = transactionRepository
                .findById(transactionId);

        if (existingTransactionOptional.isPresent()) {
            Transaction existingTransaction = existingTransactionOptional.get();

            if (updatedData.getTransactionDescription() != null) {
                existingTransaction.setTransactionDescription(updatedData.getTransactionDescription());
            }

            if (updatedData.getCategory() != null) {
                Category category = mapDtoToCategory(updatedData.getCategory());
                existingTransaction.setCategoryId(category);
            }

            if (updatedData.getExpenseType() != null) {
                existingTransaction.setExpenseType(updatedData.getExpenseType());
            }

            if (updatedData.getDueDate() != null) {
                existingTransaction.setDueDate(updatedData.getDueDate());
            }

            if (updatedData.getAmount() != null) {
                existingTransaction.setAmount(updatedData.getAmount());
            }

            if (updatedData.getComments() != null) {
                existingTransaction.setComments(updatedData.getComments());
            }

            Transaction updatedTransaction = transactionRepository.save(existingTransaction);

            return transactionMapper.toResponseDTO(updatedTransaction);
        } else {
            throw new TransactionNotFoundException("Transação não encontrada com o ID: " + transactionId);
        }
    }

    private Category mapDtoToCategory(CategoryDtoRequest categoryDto) {
        if (categoryDto != null) {
            Long categoryId = categoryDto.getId();

            return categoryRepository.findById(Math.toIntExact(categoryId)).orElse(null);
        }
        return null;
    }

    public void deleteTransaction(UUID transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
        } else {
            throw new TransactionNotFoundException("Transação não encontrada com o ID: " + transactionId);
        }
    }

}
