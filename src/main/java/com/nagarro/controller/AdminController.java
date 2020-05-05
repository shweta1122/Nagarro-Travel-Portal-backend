package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.jwt.util.JwtUtil;
import com.nagarro.model.Admin;
import com.nagarro.model.AdminTicket;
import com.nagarro.model.AuthRequest;
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

	@PostMapping(value = "/admin/login")
	public String login(@RequestBody AuthRequest auth) {
		return adminServices.authentiacate(auth);

	}

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

	@PostMapping("/admin/{adminId}/{employeeId}/{ticketId}/uploadDoc")
	public String updloadTicketInfo(@PathVariable(value = "adminId") Long adminId,
			@PathVariable(value = "employeeId") Long employeeId, @PathVariable(value = "ticketId") Long ticketId,
			@RequestBody AdminTicket ticketInfo) {

		return adminServices.updoadTicketDoc(adminId, employeeId, ticketId, ticketInfo);
	}

	// public ResponseEntity<?>  updloadTicketInfo(
    //         @RequestParam("file") MultipartFile pdf,
    //         @RequestParam("comments") String comments,
    //         @RequestParam("ticketId") Long ticketId,
	// 		@RequestParam("adminId") Long adminId,
	// 		@RequestParam("employeeId") Long employeeId
    //         ) {

	// 			AdminTicket ticketInfo = new AdminTicket();

	// 			ticketInfo.setAdminId(adminId);
	// 			ticketInfo.setComments(comments);
	// 			//ticketInfo.setPdf(pdf.getOriginalFilename());
	// 			ticketInfo.setEmployeeId(employeeId);



	// 		}

	@PatchMapping("/admin/ticket/{ticketId}")
	public Ticket updateTicketStatus(@PathVariable(value = "ticketId") Long ticketId,
			@RequestBody Ticket ticketStatus) {
		return adminServices.updateTicketStatus(ticketId, ticketStatus);
	}

}