package com.nagarro.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import com.nagarro.model.Employee;

@Service
public class MailService {
	private JavaMailSender javaMailSender;
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	public void sendEmail(Employee employee) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(employee.getUserName());
		mail.setSubject("This is from nagarro travel portal");
		mail.setText("Hi\n\nUsername="+employee.getUserName()+"\nPassword="+employee.getPassword()+"\n\n\nregards\nTravel team nagarro");
		javaMailSender.send(mail);
		System.out.println("sent mail");
	}
}
