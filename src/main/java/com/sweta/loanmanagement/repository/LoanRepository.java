package com.sweta.loanmanagement.repository;

import com.sweta.loanmanagement.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
