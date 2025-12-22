package com.sweta.loanmanagement.controller;

import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.service.LoanService;
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
    public Loan createLoan(@RequestBody Loan loan){
        return loanService.createLoan(loan);
    }
    @GetMapping
    public List<Loan>getAllLoans(){
        return loanService.getAllLoans();
    }
}
