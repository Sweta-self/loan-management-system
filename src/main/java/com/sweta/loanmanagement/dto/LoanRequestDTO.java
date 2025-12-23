package com.sweta.loanmanagement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;



public class LoanRequestDTO {
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private Integer tenureMonths;
    @NotNull
    private Double interestRate;
    @NotNull
    private Long customerId;

    public LoanRequestDTO() {
    }

    public LoanRequestDTO(Double amount, Integer tenureMonths, Double interestRate, Long customerId) {
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getTenureMonths() {
        return tenureMonths;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTenureMonths(Integer tenureMonths) {
        this.tenureMonths = tenureMonths;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
