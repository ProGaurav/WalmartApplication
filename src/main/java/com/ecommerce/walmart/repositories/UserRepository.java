package com.ecommerce.walmart.repositories;

import com.ecommerce.walmart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailAddress(String emailAddress);
}
