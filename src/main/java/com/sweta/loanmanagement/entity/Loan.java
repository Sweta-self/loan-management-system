package com.sweta.loanmanagement.entity;

import com.sweta.loanmanagement.enums.LoanStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@SQLDelete(sql="UPDATE loans SET deleted=true WHERE id=?")
@SQLRestriction("deleted = false")
@Table(name="loans")
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    private Double amount;
    private Integer tenureMonths;
    private Double interestRate;

   // private LocalDate startDate;

//    @PrePersist
//    public void onCreate(){
//        this.startDate=LocalDate.now();
//    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    //Many loans belong to one customer
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
private boolean deleted=false;
    public Loan() {
    }

    public Loan(Long id, Double amount, Integer tenureMonths, Double interestRate,LoanStatus status) {
        this.id = id;
        this.amount = amount;
        this.tenureMonths = tenureMonths;
        this.interestRate = interestRate;

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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
