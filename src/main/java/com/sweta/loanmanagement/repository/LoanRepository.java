package com.sweta.loanmanagement.repository;

import com.sweta.loanmanagement.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    List<Loan> findByCustomerId(Long customerId);
}
