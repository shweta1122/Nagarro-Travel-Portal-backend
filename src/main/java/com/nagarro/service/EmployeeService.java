package com.nagarro.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.model.AdminTicket;
import com.nagarro.model.Employee;
import com.nagarro.model.Ticket;
import com.nagarro.repository.AdminRepository;
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

	// show all employee list
	public List<Employee> getEmployee() {
		List<Employee> employees=employeeRepository.findAll();
		employees.forEach(employee -> {
			List<Ticket> tickets = employee.getTickets();
			tickets.forEach(ticket -> ticket.setEmployee(null));
			employee.setTickets(tickets);
		});
		return employees;
	}

	// Register new employee
	public Employee createEmployee(Employee employee) {
		System.out.println("---------------------------------------");
		System.out.println(employee);
		LocalDate localDate = LocalDate.now();
		employee.setCreatedAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		employee.setUpdatedAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		employee.setPassword(passwordAuth.passwordGen());
		System.out.println("-----------------------------------");
		System.out.println(employee);
		mailService.sendEmail(employee);
		return employeeRepository.save(employee);
	}

	// find employee by id
	public Employee getEmployeeById(Long Id) {
		return employeeRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", Id));
	}

	// update password and send mail
	public void updatePassword(@Valid Employee employee) {
        employee.setPassword(passwordAuth.passwordGen());
        employeeRepository.updatePassword(employee.getUserName(), employee.getPassword());
        mailService.sendEmail(employee);
	}

	public AdminTicket getTicketDoc(Long Id) {
		return adminTicketRepository.findByTicketId(Id);

	}
	

	


}
