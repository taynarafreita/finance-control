package com.finance.financecontrol.repositories;

import com.finance.financecontrol.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
