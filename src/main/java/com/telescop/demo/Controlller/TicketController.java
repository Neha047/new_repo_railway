package com.telescop.demo.Controlller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.telescop.demo.Model.Ticket;
import com.telescop.demo.Service.TicketService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    public List<Ticket> getTickets(){
        return ticketService.getTicket();
    }

    @PostMapping("/book")
    public ResponseEntity<Map<String, String>> bookTicket(@RequestBody Ticket bookingRequest) {
        try {
            // Extract origin, destination, and price from the booking request
            String originStation = bookingRequest.getOriginStation();
            String destinationStation = bookingRequest.getDestinationStation();
            BigDecimal price = bookingRequest.getPrice();
            
            System.out.println(originStation + " " + destinationStation + " " + price + " " + bookingRequest);
            
            // Generate ticket ID using the provided stations
            String ticketId = ticketService.generateTicketId(originStation, destinationStation);
            
            // Create a new Ticket object and set its properties
            Ticket ticket = new Ticket();
            ticket.setOriginStation(originStation);
            ticket.setDestinationStation(destinationStation);
            ticket.setBookingTime(LocalDateTime.now());
            ticket.setPrice(price);
            
         
            ticketService.addTicket(ticket);
            
            // If booking is successful, return a success message and ticket details
            Map<String, String> response = new HashMap<>();
            response.put("message", "Booking successful");
            response.put("ticketId", ticketId); // Optionally, include the generated ticket ID in the response
            
            // Parse ticketId to Long and retrieve ticket details
            Long ticketIdLong = Long.parseLong(ticketId);
            String ticketDetails = ticketService.printTicketById(ticketIdLong);
            
            response.put("ticketDetails", ticketDetails); // Add ticket details to the response
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            
            // Return an error response with status code 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Failed to book ticket"));
        }
    }


    @GetMapping("/validate/entry/{ticketId}")
    public boolean validateEntry(@PathVariable String ticketId) {
        return ticketService.validateTicketForEntry(ticketId);
    }

    @GetMapping("/validate/exit/{ticketId}")
    public boolean validateExit(@PathVariable String ticketId) {
        return ticketService.validateTicketForExit(ticketId);
    }
}
