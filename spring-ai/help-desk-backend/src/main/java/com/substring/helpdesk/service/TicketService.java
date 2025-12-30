package com.substring.helpdesk.service;

import org.springframework.stereotype.Service;

import com.substring.helpdesk.entity.Ticket;
import com.substring.helpdesk.repo.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
	private final TicketRepository ticketRepository;
	
	public TicketService(TicketRepository ticketRepository) {
		super();
		this.ticketRepository = ticketRepository;
	}
	//create ticket
	@Transactional
	public Ticket createTicket(Ticket ticket)
	{
		ticket.setId(null);
		return ticketRepository.save(ticket);
	}
	//update ticket
	public Ticket updateTicket(Ticket ticket)
	{
		return ticketRepository.save(ticket);
	}
	//get ticket logic
	public Ticket getTicket(Long ticketId)
	{
		/*return ticketRepository.findById(ticketId)
				.orElseThrow(()->new IllegalArgumentException("Ticket not found"));*/
		return ticketRepository.findById(ticketId).orElse(null);
	}
	//get ticket by username
	public Ticket getTicketByEmailId(String username)
	{
		return ticketRepository.findByEmail(username).orElse(null);
	}
}
