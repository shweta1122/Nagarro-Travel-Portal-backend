package com.nagarro.controller;

import com.nagarro.jwt.util.JwtUtil;
import com.nagarro.model.AuthRequest;
import com.nagarro.model.AuthResponse;
import com.nagarro.model.Employee;
import com.nagarro.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        String token = jwtUtil.generateToken(authRequest.getUserName());
        Employee employee = employeeRepository.findByUserName(authRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setId(employee.getId());
        return ResponseEntity.ok(authResponse);
    }

}