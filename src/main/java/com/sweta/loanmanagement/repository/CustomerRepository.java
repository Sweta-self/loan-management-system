package com.sweta.loanmanagement.repository;

import com.sweta.loanmanagement.auth.entity.User;
import com.sweta.loanmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByUser(User user);
}
