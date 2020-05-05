package com.nagarro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.model.Admin;
import com.nagarro.model.AdminTicket;
import com.nagarro.model.AuthRequest;
import com.nagarro.model.Ticket;
import com.nagarro.repository.AdminRepository;
import com.nagarro.repository.AdminTicketRepository;
import com.nagarro.repository.TicketRepository;

@Service
public class AdminServices {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	AdminTicketRepository adminTicketRepository;


	public String updoadTicketDoc(Long adminId,Long employeeId,
	Long ticketId, AdminTicket ticketInfo) {

		ticketInfo.setAdminId(adminId);
		ticketInfo.setEmployeeId(employeeId);
		ticketInfo.setTicketId(ticketId);
		adminTicketRepository.save(ticketInfo);
		return "Ok";

		

	}

	public List<Admin> getAdmin() {
		return adminRepository.findAll();
	}

	// public Ticket getTicketById() {
	// 	ticketRepository.findByTicketId(ticketId)
	// }


	public String authentiacate(AuthRequest auth) {
		String userName = auth.getUserName();

		Admin admin = adminRepository.findByuserName(userName);

		if(admin==null) {
			return "Invalid User";
		}
		else if(admin.getPassword().equals(auth.getPassword())) {
			return "succesfull";
		}
		else{
			return "Incorrect Password";
		}
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
