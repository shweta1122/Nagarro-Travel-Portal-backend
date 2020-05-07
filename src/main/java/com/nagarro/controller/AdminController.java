package com.nagarro.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.model.Admin;
import com.nagarro.model.AdminTicket;
import com.nagarro.model.AuthRequest;

import com.nagarro.model.Ticket;
import com.nagarro.repository.AdminTicketRepository;

import com.nagarro.repository.TicketRepository;
import com.nagarro.service.AdminServices;

@RestController
public class AdminController {

	@Autowired
	AdminServices adminServices;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	AdminTicketRepository commentsService;

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

	@RequestMapping(value = "/admin/uploadDoc", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<?> addComment(@RequestParam("file") MultipartFile file,
			@RequestParam("comments") String comments, @RequestParam("ticketId") Long tid,
			@RequestParam("adminId") Long aid) {

		AdminTicket ob = new AdminTicket();

		ob.setComments(comments);
		ob.setTicketId(tid);
		ob.setFileName(file.getOriginalFilename());
		ob.setAdminId(aid);

		try {
			ob.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		commentsService.save(ob);

		if (file != null) {

		}

		return ResponseEntity.ok().body("Updated");
	}

	@PatchMapping("/admin/ticket/{ticketId}")
	public Ticket updateTicketStatus(@PathVariable(value = "ticketId") Long ticketId,
			@RequestBody Ticket ticketStatus) {
		return adminServices.updateTicketStatus(ticketId, ticketStatus);
	}

}