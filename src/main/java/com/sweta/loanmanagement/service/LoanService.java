package com.sweta.loanmanagement.service;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.dto.LoanUpdateRequestDTO;
import com.sweta.loanmanagement.entity.Loan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoanService {
    LoanResponseDTO createLoan(LoanRequestDTO request);
    Page<LoanResponseDTO> getAllLoans(int page,int size,String sortBy,String direction);
    LoanResponseDTO updateLoan(Long loadId, LoanUpdateRequestDTO request);
    LoanResponseDTO getLoanById(Long loanId);
    List<LoanResponseDTO>getLoansByCustomerId(Long customerId);
}
