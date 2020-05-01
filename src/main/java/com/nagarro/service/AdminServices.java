package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.model.Admin;
import com.nagarro.model.Ticket;
import com.nagarro.repository.AdminRepository;
import com.nagarro.repository.TicketRepository;

@Service
public class AdminServices {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	TicketRepository ticketRepository;

	public List<Admin> getAdmin() {
		return adminRepository.findAll();
	}

	public Ticket updateTicketStatus(Long ticketId, Ticket ticketStatus) {
		return ticketRepository.findById(ticketId).map(ticket -> {
			ticket.setStatus(ticketStatus.getStatus());
			Ticket ticketDb = ticketRepository.save(ticket);
			ticketDb.setEmployee(null);
			return ticketDb;
		}).orElseThrow(() -> new ResourceNotFoundException("Ticket", "id", ticketId));

	}

}
