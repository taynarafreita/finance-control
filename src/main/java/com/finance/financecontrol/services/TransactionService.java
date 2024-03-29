package com.finance.financecontrol.services;

import com.finance.financecontrol.dtos.requests.CategoryDtoRequest;
import com.finance.financecontrol.dtos.requests.TransactionDtoRequest;
import com.finance.financecontrol.dtos.responses.TransactionDtoResponse;
import com.finance.financecontrol.enums.ExpenseTypeEnum;
import com.finance.financecontrol.exceptions.http.TransactionNotFoundException;
import com.finance.financecontrol.mappers.TransactionMapper;
import com.finance.financecontrol.models.AccountBalance;
import com.finance.financecontrol.models.Category;
import com.finance.financecontrol.models.Transaction;
import com.finance.financecontrol.repositories.AccountBalanceRepository;
import com.finance.financecontrol.repositories.CategoryRepository;
import com.finance.financecontrol.repositories.TransactionRepository;
import com.finance.financecontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TransactionDtoResponse> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TransactionDtoResponse getTransactionById(UUID transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transação com id " + transactionId + " não encontrada."));

        return transactionMapper.toResponseDTO(transaction);
    }

    public TransactionDtoResponse createTransaction(TransactionDtoRequest transactionDto) {
        Transaction newTransaction = transactionMapper.toEntity(transactionDto);
        Transaction addedTransaction = transactionRepository.save(newTransaction);

        updateAccountBalance(newTransaction.getUser().getId());

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

            if (updatedData.getAmount() != null || updatedData.getExpenseType() != null) {
                updateAccountBalance(updatedTransaction.getUser().getId());
            }

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
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);

        if (transaction != null) {
            BigDecimal transactionAmount;
            transactionAmount = BigDecimal.ZERO;

            transaction.setAmount(transactionAmount);

            updateAccountBalance(transaction.getUser().getId());

            transactionRepository.deleteById(transactionId);
        } else {
            throw new TransactionNotFoundException("Transação não encontrada com o ID: " + transactionId);
        }
    }

    private void updateAccountBalance(UUID userId) {
        BigDecimal newBalance = calculateNewBalance(userId);

        AccountBalance accountBalance = new AccountBalance();
        accountBalance.setUser(userRepository.findById(userId).orElse(null));
        accountBalance.setCurrentBalance(newBalance);

        accountBalanceRepository.save(accountBalance);
    }

    private BigDecimal calculateNewBalance(UUID userId) {
        List<Transaction> userTransactions = transactionRepository.findByUserId(userId);

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Transaction transaction : userTransactions) {
            if (ExpenseTypeEnum.INCOME.equals(transaction.getExpenseType())) {
                totalIncome = totalIncome.add(transaction.getAmount());
            } else if (ExpenseTypeEnum.EXPENSE.equals(transaction.getExpenseType())) {
                totalExpense = totalExpense.add(transaction.getAmount());
            }
        }

        return totalIncome.subtract(totalExpense);
    }

}
