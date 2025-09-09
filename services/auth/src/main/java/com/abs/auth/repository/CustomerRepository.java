package com.abs.auth.repository;

import com.abs.auth.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    boolean existsByEmailIgnoreCase(String email);

    Optional<Customer> findByEmailIgnoreCase(String s);
}
