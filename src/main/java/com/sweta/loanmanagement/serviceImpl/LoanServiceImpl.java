package com.sweta.loanmanagement.serviceImpl;

import com.sweta.loanmanagement.dto.LoanRequestDTO;
import com.sweta.loanmanagement.dto.LoanResponseDTO;
import com.sweta.loanmanagement.dto.LoanUpdateRequestDTO;
import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.entity.Loan;
import com.sweta.loanmanagement.enums.LoanStatus;
import com.sweta.loanmanagement.exception.CustomerNotFoundException;
import com.sweta.loanmanagement.exception.LoanNotFoundException;
import com.sweta.loanmanagement.repository.CustomerRepository;
import com.sweta.loanmanagement.repository.LoanRepository;
import com.sweta.loanmanagement.service.AuthHelperService;
import com.sweta.loanmanagement.service.LoanService;
import com.sweta.loanmanagement.specification.LoanSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final AuthHelperService authHelperService;

    public LoanServiceImpl(LoanRepository loanRepository, CustomerRepository customerRepository, AuthHelperService authHelperService) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
        this.authHelperService = authHelperService;
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
        loan.setStatus(LoanStatus.CREATED);

        Loan savedLoan= loanRepository.save(loan);
        LoanResponseDTO response=new LoanResponseDTO();
        response.setLoanId(savedLoan.getId());
        response.setAmount(savedLoan.getAmount());
        response.setTenureMonths(savedLoan.getTenureMonths());
        response.setInterestRate(savedLoan.getInterestRate());
        response.setCustomerId(customer.getId());
        response.setCustomerEmail(customer.getEmail());
        response.setCustomerName(customer.getFullName());
        response.setStatus(LoanStatus.CREATED);
        return response;
    }

    @Override
    public Page<LoanResponseDTO> getAllLoans(int page, int size, String sortBy, String direction) {
Sort sort=direction.equalsIgnoreCase("desc")
        ? Sort.by(sortBy).descending()
        :Sort.by(sortBy).ascending();
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<Loan>loanPage=loanRepository.findAll(pageable);
        return loanPage.map(this::mapToLoanResponse);
    }

private LoanResponseDTO mapToLoanResponse(Loan loan) {
    LoanResponseDTO dto=new LoanResponseDTO();
    dto.setLoanId(loan.getId());
    dto.setAmount(loan.getAmount());
    dto.setTenureMonths(loan.getTenureMonths());
    dto.setStatus(loan.getStatus());
    dto.setCustomerId(loan.getCustomer().getId());
    dto.setCustomerEmail(loan.getCustomer().getEmail());
    dto.setCustomerName(loan.getCustomer().getFullName());
    return dto;
}
    @Override
    public LoanResponseDTO updateLoan(Long loanId, LoanUpdateRequestDTO request) {
        Loan loan=loanRepository.findById(loanId)
                .orElseThrow(()->new LoanNotFoundException("Loan not found with id"+loanId));
        Customer loggedInUser=authHelperService.getLoggedInCustomer();
        if(!loan.getCustomer().getId().equals(loggedInUser.getId())){
            throw new RuntimeException("You are not allowed to update this loan");
        }
        if(request.getLoanAmount()!=null){
            loan.setAmount(request.getLoanAmount());
        }
        if(request.getTenure()!=null){
            loan.setTenureMonths(request.getTenure());
        }
        //moving status updation restricted to admin only
//        if(request.getStatus()!=null){
//            loan.setStatus(request.getStatus());
//        }
        Loan updatedLoan=loanRepository.save(loan);
        return mapToLoanResponse(updatedLoan);
    }

    @Override
    public LoanResponseDTO updateLoanStatus(Long loanId, LoanStatus newStatus) {
       Loan loan=loanRepository.findById(loanId)
               .orElseThrow(()->new LoanNotFoundException("Loan not found"));
       LoanStatus currentStatus = loan.getStatus();

       //already finalized
        if(currentStatus ==LoanStatus.REJECTED ||currentStatus==LoanStatus.CLOSED){
            throw new IllegalStateException("Loan status cannot be changed");
        }
        //invalid transition
        if(currentStatus==LoanStatus.CREATED && newStatus== LoanStatus.CLOSED){
            throw new IllegalStateException("Loan must be approved before closing");
        }
        //approved loan cannot go back
        if(currentStatus==LoanStatus.APPROVED && newStatus==LoanStatus.CREATED){
            throw  new IllegalStateException("Invalid state transition");
        }
        loan.setStatus(newStatus);
        loanRepository.save(loan);
        return mapToLoanResponse(loan);
    }

    @Override
    public LoanResponseDTO getLoanById(Long loanId) {
        Loan loan=loanRepository.findById(loanId)
                .orElseThrow(()-> new LoanNotFoundException("Loan not found with id"+loanId));
        return mapToLoanResponse(loan);
    }

    @Override
    public List<LoanResponseDTO> getLoansByCustomerId(Long customerId) {
        //step1 validate customer exists
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found" +
                        "with id"+customerId));
        //step 2 fetch loans
        List<Loan>loans=loanRepository.findByCustomerId(customerId);
        //step 3.map to responseDto
        return loans.stream().map(this::mapToLoanResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<LoanResponseDTO> searchLoans(LoanStatus status, Double amount, Integer tenureMonths,String fullName, LocalDateTime createdAt,LocalDateTime updatedAt, int page, int size, String sortBy, String sortDir) {
        Sort sort=sortBy.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<Loan>loanPage=loanRepository.findAll(LoanSpecification.search(
                status,amount,tenureMonths,fullName,createdAt,updatedAt
        ),
                pageable);
        return loanPage.map(this::mapToLoanResponse);
    }
    public List<LoanResponseDTO> getMyLoans(){
        Customer customer=
                authHelperService.getLoggedInCustomer();
        List<Loan>loans=loanRepository.findByCustomerId(customer.getId());
        return loans.stream().map(this::mapToLoanResponse)
                .collect(Collectors.toList());
    }
}
