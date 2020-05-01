package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.jwt.util.JwtUtil;
import com.nagarro.model.Admin;
import com.nagarro.model.Employee;
import com.nagarro.model.Ticket;
import com.nagarro.repository.EmployeeRepository;
import com.nagarro.repository.TicketRepository;
import com.nagarro.service.AdminServices;

@RestController
public class AdminController {

	@Autowired
	AdminServices adminServices;

	@Autowired
	TicketRepository ticketRepository;

	
	@GetMapping("/admin")
	public List<Admin> getAdmin() {
		return adminServices.getAdmin();
	}

	@GetMapping("/admin/tickets")
	public List<Ticket> getAllTickets() {
		var ticketsRaw = ticketRepository.findAll();
		ticketsRaw.forEach(x -> {
			var emp = x.getEmployee();
			emp.setTickets(null);
		});
		return ticketsRaw;

	}

	@PatchMapping("/admin/tickets/{ticketId}")
	public Ticket updateTicketStatus(@PathVariable(value = "ticketId") Long ticketId,
			@RequestBody Ticket ticketStatus) {
		return adminServices.updateTicketStatus(ticketId, ticketStatus);
	}

}