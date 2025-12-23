package com.sweta.loanmanagement.dto;

import com.sweta.loanmanagement.enums.LoanStatus;

public class LoanUpdateRequestDTO {
    private Double loanAmount;
    private Integer tenure;
    private LoanStatus status;

    public LoanUpdateRequestDTO(Double loanAmount, Integer tenure, LoanStatus status) {
        this.loanAmount = loanAmount;
        this.tenure = tenure;
        this.status = status;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public Integer getTenure() {
        return tenure;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
