package com.sss.virtual.tech.ticketapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sss.virtual.tech.ticketapi.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByUserEmail(String email);
}