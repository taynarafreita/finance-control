package com.finance.financecontrol.repositories;

import com.finance.financecontrol.models.Transaction;
import com.finance.financecontrol.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUserId(UUID user);
}
