package com.ecommerce.walmart.repositories;

import com.ecommerce.walmart.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
