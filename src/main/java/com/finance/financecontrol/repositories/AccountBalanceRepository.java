package com.finance.financecontrol.repositories;

import com.finance.financecontrol.models.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, UUID> {
}
