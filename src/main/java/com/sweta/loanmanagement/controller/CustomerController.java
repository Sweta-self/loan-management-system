package com.sweta.loanmanagement.controller;

import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
    @GetMapping
    public List<Customer>getAllCustomers(){
        return customerService.getAllCustomers();
    }
}
