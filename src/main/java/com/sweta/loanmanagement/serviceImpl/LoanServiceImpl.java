package com.sweta.loanmanagement.serviceImpl;

import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.repository.CustomerRepository;
import com.sweta.loanmanagement.repository.LoanRepository;
import com.sweta.loanmanagement.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    public LoanServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Loan createLoan(Loan loan) {
        Long customerId=loan.getCustomer().getId();
        Customer customer=
                customerRepository.findById(customerId)
                        .orElseThrow(()->new RuntimeException("Customer not found"));
        loan.setCustomer(customer);
        return loanRepository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
