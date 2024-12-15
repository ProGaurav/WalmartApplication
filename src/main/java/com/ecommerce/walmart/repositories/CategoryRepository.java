package com.ecommerce.walmart.repositories;

import com.ecommerce.walmart.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
