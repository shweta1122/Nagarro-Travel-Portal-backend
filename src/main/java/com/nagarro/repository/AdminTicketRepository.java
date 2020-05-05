package com.nagarro.repository;

import com.nagarro.model.AdminTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminTicketRepository extends JpaRepository<AdminTicket, Long> {

    AdminTicket findByTicketId(Long Id);
}