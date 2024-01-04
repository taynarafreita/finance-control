package com.finance.financecontrol.repositories;

import com.finance.financecontrol.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
