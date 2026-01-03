package com.sweta.loanmanagement.auth.service;

import com.sweta.loanmanagement.auth.entity.User;
import com.sweta.loanmanagement.auth.enums.Role;
import com.sweta.loanmanagement.auth.repository.UserRepository;
import com.sweta.loanmanagement.entity.Customer;
import com.sweta.loanmanagement.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }
    //Register
    public void register(String username,String password){
        User user= new User();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));

        //default role
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        //Create customer and connect
        Customer customer=new Customer();
        customer.setFullName(username);
        customer.setUser(user);
        customerRepository.save(customer);
    }
    //Admin promotes user
    public void changeRole(Long userId,Role role){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
        user.setRole(role);
        userRepository.save(user);
    }
}
