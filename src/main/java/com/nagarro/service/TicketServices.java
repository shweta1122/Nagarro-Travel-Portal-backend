package com.nagarro.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.exception.ResourceNotFoundException;
import com.nagarro.model.Ticket;
import com.nagarro.repository.EmployeeRepository;
import com.nagarro.repository.TicketRepository;

@Service
public class TicketServices {
	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Ticket> getTicketByEmployee(Long employeeId) {
		List<Ticket> tickets = ticketRepository.findByEmployeeId(employeeId);
		tickets.forEach(ticket -> ticket.setEmployee(null));
		return tickets;
	}
	public Ticket createTicket(Long employeeId, @Valid Ticket ticket) {
		return employeeRepository.findById(employeeId).map(employee -> {
			ticket.setEmployee(employee);
			ticket.setStatus("submitted");
			Ticket tickets = ticketRepository.save(ticket);
			tickets.setEmployee(null);
			return tickets;
		}).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
	}
	
	
	public Ticket updateTicket(Long employeeId, Long ticketId, Ticket ticketRequest) {
		System.out.println("-----------------------------------------------------------");
		System.out.println(ticketRequest);
		return ticketRepository.findById(ticketId).map(ticket -> {

			LocalDate localDate = LocalDate.now();
		ticket.setCreatedAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		ticket.setUpdatedAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			ticket.setProjectName(ticketRequest.getProjectName());
			ticket.setRequestType(ticketRequest.getRequestType());
			ticket.setPriority(ticketRequest.getPriority());

			ticket.setTravelCity(ticketRequest.getTravelCity());
			ticket.setFromLocation(ticketRequest.getFromLocation());
			ticket.setTravelDate(ticketRequest.getTravelDate());
			ticket.setEndDate(ticketRequest.getEndDate());
			ticket.setPassportNumber(ticketRequest.getPassportNumber());
			ticket.setProjectName(ticketRequest.getProjectName());
			ticket.setExpenseBornBy(ticketRequest.getExpenseBornBy());
			ticket.setTravelApprover(ticketRequest.getTravelApprover());
			ticket.setExpectedDuration(ticketRequest.getExpectedDuration());
			ticket.setUpperBound(ticketRequest.getUpperBound());
			ticket.setAdditionalDetail(ticket.getAdditionalDetail());
			ticket.setStatus("re-submitted");
			Ticket tickets = ticketRepository.save(ticket);
			tickets.setEmployee(null);
			return tickets;
		}).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
	}
}
