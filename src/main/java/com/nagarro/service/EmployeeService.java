package com.nagarro.service;

import java.time.LocalDate;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exception.ResourceNotFoundException;

import com.nagarro.model.Employee;
import com.nagarro.model.Ticket;

import com.nagarro.repository.AdminTicketRepository;
import com.nagarro.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	AdminTicketRepository adminTicketRepository;
	@Autowired
	PasswordAuth passwordAuth;
	@Autowired
	MailService mailService;

	public List<Employee> getEmployee() {
		List<Employee> employees = employeeRepository.findAll();
		employees.forEach(employee -> {
			List<Ticket> tickets = employee.getTickets();
			tickets.forEach(ticket -> ticket.setEmployee(null));
			employee.setTickets(tickets);
		});
		return employees;
	}

	public Employee createEmployee(Employee employee) {

		LocalDate localDate = LocalDate.now();
		employee.setCreatedAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		employee.setUpdatedAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		employee.setPassword(passwordAuth.passwordGen());

		mailService.sendEmail(employee);
		return employeeRepository.save(employee);
	}

	public Employee getEmployeeById(Long Id) {
		return employeeRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", Id));
	}

	public void updatePassword(@Valid Employee employee) {
		employee.setPassword(passwordAuth.passwordGen());
		employeeRepository.updatePassword(employee.getUserName(), employee.getPassword());
		mailService.sendEmail(employee);
	}

}
