package com.sweta.loanmanagement.auth.controller;

import com.sweta.loanmanagement.auth.dto.LoginRequest;
import com.sweta.loanmanagement.auth.dto.LoginResponse;
import com.sweta.loanmanagement.auth.enums.Role;
import com.sweta.loanmanagement.auth.service.UserService;
import com.sweta.loanmanagement.auth.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private  final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
       Authentication authentication= authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String token=jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody LoginRequest request){
        userService.register(
                request.getUsername(),
                request.getPassword()
        );
        return ResponseEntity.ok("User registered with ROLE_USER");
    }
    @PutMapping("/changerole/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>changeRole(
            @PathVariable Long userId,
            @RequestParam Role role){
        userService.changeRole(userId,role);
        return ResponseEntity.ok("Role updated");
    }


}
