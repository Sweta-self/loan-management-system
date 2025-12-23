package com.sweta.loanmanagement.serviceImpl;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.exception.CustomerNotFoundException;
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
    public LoanResponseDTO createLoan(LoanRequestDTO request) {

        Customer customer=
                customerRepository.findById(request.getCustomerId())
                        .orElseThrow(()->new CustomerNotFoundException("Customer not found with id:"+request.getCustomerId()));
        Loan loan=new Loan();
        loan.setAmount(request.getAmount());
        loan.setTenureMonths(request.getTenureMonths());
        loan.setInterestRate(request.getInterestRate());
        loan.setCustomer(customer);
        Loan savedLoan= loanRepository.save(loan);
        LoanResponseDTO response=new LoanResponseDTO();
        response.setLoanId(savedLoan.getId());
        response.setAmount(savedLoan.getAmount());
        response.setTenureMonths(savedLoan.getTenureMonths());
        response.setInterestRate(savedLoan.getInterestRate());
        response.setCustomerId(customer.getId());
        response.setCustomerEmail(customer.getEmail());
        response.setCustomerName(customer.getFullName());
        return response;
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
