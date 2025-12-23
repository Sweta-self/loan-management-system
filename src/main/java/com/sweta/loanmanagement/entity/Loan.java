package com.sweta.loanmanagement.entity;

import com.sweta.loanmanagement.enums.LoanStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="loans")
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    private Double amount;
    private Integer tenureMonths;
    private Double interestRate;

    private LocalDate startDate;

    @PrePersist
    public void onCreate(){
        this.startDate=LocalDate.now();
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    //Many loans belong to one customer
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Loan() {
    }

    public Loan(Long id, Double amount, Integer tenureMonths, Double interestRate, LocalDate startDate,LoanStatus status) {
        this.id = id;
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;
        this.startDate = startDate;
        this.status=status;
    }

    public Long getId() {
        return id;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
