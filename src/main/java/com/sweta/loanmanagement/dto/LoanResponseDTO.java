package com.sweta.loanmanagement.dto;

import com.sweta.loanmanagement.enums.LoanStatus;

public class LoanResponseDTO {
    private Long loanId;
    private Double amount;
    private Integer tenureMonths;
    private Double interestRate;
    private Long customerId;
    private String customerName;
    private String customerEmail;
  private LoanStatus status;
    public LoanResponseDTO() {
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LoanResponseDTO(Long loanId, Double amount, Integer tenureMonths, Double interestRate, Long customerId, String customerName, String customerEmail,LoanStatus status) {
        this.loanId = loanId;
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.status = status;
    }

    public Long getLoanId() {
        return loanId;
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

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
