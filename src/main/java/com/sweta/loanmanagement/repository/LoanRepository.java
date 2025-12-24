package com.sweta.loanmanagement.repository;

import com.sweta.loanmanagement.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long>, JpaSpecificationExecutor<Loan> {
    List<Loan> findByCustomerId(Long customerId);
}
