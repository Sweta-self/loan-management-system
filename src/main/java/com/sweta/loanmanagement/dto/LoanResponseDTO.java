package com.sweta.loanmanagement.dto;

public class LoanResponseDTO {
    private Long loanId;
    private Double amount;
    private Integer tenureMonths;
    private Double InterestRate;
    private Long customerId;
    private String customerName;
    private String customerEmail;

    public LoanResponseDTO() {
    }

    public LoanResponseDTO(Long loanId, Double amount, Integer tenureMonths, Double interestRate, Long customerId, String customerName, String customerEmail) {
        this.loanId = loanId;
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        InterestRate = interestRate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
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
        return InterestRate;
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
        InterestRate = interestRate;
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
