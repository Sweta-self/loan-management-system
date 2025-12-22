package com.sweta.loanmanagement.service;

import com.sweta.loanmanagement.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
}
