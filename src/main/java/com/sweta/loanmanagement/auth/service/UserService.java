package com.sweta.loanmanagement.auth.service;

import com.sweta.loanmanagement.auth.entity.User;
import com.sweta.loanmanagement.auth.enums.Role;
import com.sweta.loanmanagement.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //Register
    public void register(String username,String password){
        User user= new User();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));

        //default role
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
    //Admin promotes user
    public void changeRole(Long userId,Role role){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
        user.setRole(role);
        userRepository.save(user);
    }
}
