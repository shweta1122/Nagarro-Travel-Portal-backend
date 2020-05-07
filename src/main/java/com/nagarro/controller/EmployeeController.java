package com.nagarro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.model.AdminTicket;

import com.nagarro.model.Employee;
import com.nagarro.repository.AdminTicketRepository;
import com.nagarro.service.EmployeeService;
import com.nagarro.service.PasswordAuth;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	PasswordAuth passwordAuth;

	@Autowired
	AdminTicketRepository commentsService;

	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		return employeeService.getEmployee();
	}

	@PostMapping("/employee")
	public Employee registerEmployee(@Valid @RequestBody Employee employee) {

		return employeeService.createEmployee(employee);
	}

	@PatchMapping("/forgot")
	public void forgotPassword(@Valid @RequestBody Employee employee) {
		employeeService.updatePassword(employee);
	}

	@GetMapping("/employee/{ticketId}/ticketDoc")
	public List<AdminTicket> getAdminResponses(@PathVariable("ticketId") long ticketId) {
		return commentsService.findByTicketId(ticketId);
	}

}
