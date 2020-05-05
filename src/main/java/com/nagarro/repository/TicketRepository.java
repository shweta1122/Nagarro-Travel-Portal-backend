package com.nagarro.repository;

import java.util.List;
import java.util.Optional;

import com.nagarro.model.Employee;
import com.nagarro.model.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEmployeeId(Long employeeId);
    //Ticket findByTicketId(long ticketId);

    Optional<Ticket> findByIdAndEmployeeId(Long id, Long employeeId);

    // Employee findEmployeeByTicketId(Long ticketId);

}