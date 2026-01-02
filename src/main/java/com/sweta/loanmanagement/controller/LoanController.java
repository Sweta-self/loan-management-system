package com.sweta.loanmanagement.controller;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.dto.LoanUpdateRequestDTO;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.enums.LoanStatus;
import com.sweta.loanmanagement.service.LoanService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@SecurityRequirement(name="bearerAuth")
@RestController
@RequestMapping("/api/loans")
@Tag(name="Loan APIs",description="Loan Management APIs")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanResponseDTO createLoan(@RequestBody @Valid LoanRequestDTO request){
        return loanService.createLoan(request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<LoanResponseDTO>>getAllLoans(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "asc")String direction){
        return ResponseEntity.ok(loanService.getAllLoans(page,size,sortBy,direction));
    }
@PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{loanId}")
    public ResponseEntity<LoanResponseDTO>updateLoan(
            @PathVariable Long loanId,
            @RequestBody LoanUpdateRequestDTO request){
        return ResponseEntity.ok(loanService.updateLoan(loanId, request));
    }
    @GetMapping("/getLoanById/{loanId}")
    public ResponseEntity<LoanResponseDTO>getLoanById(@PathVariable Long loanId){
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<LoanResponseDTO>>getLoansByCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(loanService.getLoansByCustomerId(customerId));
    }
    @GetMapping("/search")
    public ResponseEntity<Page<LoanResponseDTO>>searchLoans(
            @RequestParam(required=false)LoanStatus status,
            @RequestParam(required=false)Double amount,
            @RequestParam(required=false)Integer tenureMonths,
            @RequestParam(required=false)String fullName,

            @RequestParam(required=false)
            @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
            LocalDateTime createdAt,

            @RequestParam(required=false)
            @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
            LocalDateTime updatedAt,

            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "desc")String sortDir

            )
    {
        return ResponseEntity.ok(loanService.searchLoans(
                status,amount,tenureMonths,fullName,createdAt,updatedAt,page,size,sortBy,sortDir
        ));
    }
}
