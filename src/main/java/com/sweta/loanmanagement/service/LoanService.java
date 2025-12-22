package com.sweta.loanmanagement.service;

import com.sweta.loanmanagement.entity.Loan;

import java.util.List;

public interface LoanService {
    Loan createLoan(Loan loan);
    List<Loan> getAllLoans();
}
