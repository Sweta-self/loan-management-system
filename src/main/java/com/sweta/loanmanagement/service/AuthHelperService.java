package com.sweta.loanmanagement.service;

import com.sweta.loanmanagement.auth.entity.User;
import com.sweta.loanmanagement.auth.repository.UserRepository;
import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service

public class AuthHelperService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    public AuthHelperService(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    public Customer getLoggedInCustomer(){
        String uname= SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        User user=userRepository.findByEmail(uname)
                .orElseThrow(()->new RuntimeException("User not found"));
        return customerRepository.findByUser(user)
                .orElseThrow(()->new RuntimeException("customer not found"));

    }
}
