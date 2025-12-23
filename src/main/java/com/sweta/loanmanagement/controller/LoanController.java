package com.sweta.loanmanagement.controller;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.dto.LoanUpdateRequestDTO;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanResponseDTO createLoan(@RequestBody @Valid LoanRequestDTO request){
        return loanService.createLoan(request);
    }
    @GetMapping
    public List<Loan>getAllLoans(){
        return loanService.getAllLoans();
    }
    @PutMapping("/{loanId}")
    public ResponseEntity<LoanResponseDTO>updateLoan(
            @PathVariable Long loanId,
            @RequestBody LoanUpdateRequestDTO request){
        return ResponseEntity.ok(loanService.updateLoan(loanId, request));
    }
}
