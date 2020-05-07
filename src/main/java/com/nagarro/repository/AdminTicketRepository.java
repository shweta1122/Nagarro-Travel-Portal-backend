package com.nagarro.repository;

import java.util.List;

import com.nagarro.model.AdminTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminTicketRepository extends JpaRepository<AdminTicket, Long> {

   

    List<AdminTicket> findByTicketId(Long ticketId);
}