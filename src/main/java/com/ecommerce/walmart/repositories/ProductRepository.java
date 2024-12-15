package com.ecommerce.walmart.repositories;

import com.ecommerce.walmart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
