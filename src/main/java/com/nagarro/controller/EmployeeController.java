package com.nagarro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.jwt.util.JwtUtil;
import com.nagarro.model.AuthRequest;
import com.nagarro.model.Employee;
import com.nagarro.service.EmployeeService;
import com.nagarro.service.PasswordAuth;


@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	PasswordAuth passwordAuth;

	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		return employeeService.getEmployee();
	}

	@PostMapping("/employee")
	public Employee registerEmployee(@Valid @RequestBody Employee employee) {
		System.out.println("------------------------------------");
		System.out.println(employee);
		return employeeService.createEmployee(employee);
	}

	@PatchMapping("/forgot")
	public void forgotPassword(@Valid @RequestBody Employee employee) {
		employeeService.updatePassword(employee);
	}

	@GetMapping("/")
	public String welcome() {
		return "Welcome to javatechie !!";
	}

}
