package com.sweta.loanmanagement.service;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.dto.LoanUpdateRequestDTO;
import com.sweta.loanmanagement.entity.Loan;

import java.util.List;

public interface LoanService {
    LoanResponseDTO createLoan(LoanRequestDTO request);
    List<Loan> getAllLoans();
    LoanResponseDTO updateLoan(Long loadId, LoanUpdateRequestDTO request);
}
