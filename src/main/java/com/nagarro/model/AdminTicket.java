package com.nagarro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminticket")
public class AdminTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "admin_id")
	private Long adminId;

	@Column(name = "ticket_id")
	private Long ticketId;

	@Column(name = "documents")
	private byte[] file;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "comments")
	private String comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public AdminTicket() {

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AdminTicket(Long id, Long adminId, Long ticketId, byte[] file, String fileName, String comments) {
		super();
		this.id = id;
		this.adminId = adminId;
		this.ticketId = ticketId;
		this.file = file;
		this.fileName = fileName;
		this.comments = comments;
	}

}