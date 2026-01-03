package com.sweta.loanmanagement.service;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.dto.LoanUpdateRequestDTO;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.enums.LoanStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LoanService {
    LoanResponseDTO createLoan(LoanRequestDTO request);
    Page<LoanResponseDTO> getAllLoans(int page,int size,String sortBy,String direction);
    LoanResponseDTO updateLoan(Long loadId, LoanUpdateRequestDTO request);
    LoanResponseDTO updateLoanStatus(Long loanId,LoanStatus newStatus);
    LoanResponseDTO getLoanById(Long loanId);
    List<LoanResponseDTO>getLoansByCustomerId(Long customerId);
    Page<LoanResponseDTO>searchLoans(LoanStatus status, Double amount, Integer tenureMonths, String fullName, LocalDateTime createdAt,LocalDateTime updatedAt, int page, int size, String sortBy, String sortDir);
}
