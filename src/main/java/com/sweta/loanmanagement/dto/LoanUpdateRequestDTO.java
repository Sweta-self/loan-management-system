package com.sweta.loanmanagement.dto;

import com.sweta.loanmanagement.enums.LoanStatus;

public class LoanUpdateRequestDTO {
    private Double loanAmount;
    private Integer tenure;

    public LoanUpdateRequestDTO(Double loanAmount, Integer tenure) {
        this.loanAmount = loanAmount;
        this.tenure = tenure;

    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public Integer getTenure() {
        return tenure;
    }


    public void setTenure(Integer tenure) {
        this.tenure = tenure;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }


}
