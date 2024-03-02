package com.telescop.demo.Service;

import java.util.Optional;

import java.time.LocalDateTime;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.telescop.demo.Model.Ticket;
import com.telescop.demo.Repo.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
	

	int count =0;
	
	LocalDateTime local=LocalDateTime.now();
	public TicketService() {
//		
	}


	@Autowired
    private TicketRepository ticketRepository;


	
	@Transactional
	public String generateTicketId(String originStation, String destinationStation) {
	    // Check if a ticket with the same origin and destination stations exists
	    List<Ticket> existingTickets = ticketRepository.findByOriginStationAndDestinationStation(originStation, destinationStation);
	    
	    if (!existingTickets.isEmpty()) {
	        // If a matching ticket is found, return its ID
	        return existingTickets.get(0).getId().toString();
	    }
	    
	    // If no matching ticket is found, create a new ticket
	    Ticket ticket = new Ticket();
	    ticket.setOriginStation(originStation);
	    ticket.setDestinationStation(destinationStation);
	    ticket.setBookingTime(LocalDateTime.now());
	    ticketRepository.save(ticket);
	    
	    // Generate and return the ticket ID
	    String ticketId = ticket.getId().toString(); // Assuming ticket ID is auto-generated
	    return ticketId;
	}




    public boolean validateTicketForEntry(String ticketId) {
    	Optional<Ticket> optionalTicket = ticketRepository.findById(Long.parseLong(ticketId));
    	count++;
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            return !ticket.isExpired() && count< 2 && count < 2;
        }
        return false;
    }

    public boolean validateTicketForExit(String ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(Long.parseLong(ticketId));
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            count++;
            return !ticket.isExpired() && count > 0 && count <=2;
        }
        return false;
    }
   
    public List<Ticket> getTicket() {
    	
    	return ticketRepository.findAll();
    	
    	
    	
    }
    

    public String printTicketById(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            StringBuilder ticketDetails = new StringBuilder();
            ticketDetails.append("Ticket ID: ").append(ticket.getId()).append("\n");
            ticketDetails.append("Origin Station: ").append(ticket.getOriginStation()).append("\n");
            ticketDetails.append("Destination Station: ").append(ticket.getDestinationStation()).append("\n");
            ticketDetails.append("Price: ").append(ticket.getPrice()).append("\n");
            ticketDetails.append("Booking Time: ").append(ticket.getBookingTime()).append("\n");
            return ticketDetails.toString();
        } else {
            return "Ticket not found";
        }
    }
    
   public Ticket addTicket(Ticket ticket) {
	   ticketRepository.save(ticket);
   	return ticket;
    	
   }
}
