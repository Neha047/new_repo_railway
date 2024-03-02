package com.telescop.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Ticket {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String originStation;
	    private String destinationStation;
	    private LocalDateTime bookingTime;
	   // private int entryCount;

	

	   
	    private BigDecimal price;

	    
	    public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public Ticket() {
	        // Default constructor
	    }

	    public Ticket(String originStation, String destinationStation, LocalDateTime bookingTime,int count,Long id) {
	        this.originStation = originStation;
	        this.destinationStation = destinationStation;
	        this.bookingTime = bookingTime;
	        count=0;
	        this.id=id;
	     
	    }

	    public boolean isExpired() {
	        // Assume ticket expires after 18 hours of booking time
	        return bookingTime.plusHours(18).isBefore(LocalDateTime.now());
	    }

	    // Getters and setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getOriginStation() {
	        return originStation;
	    }

	    public void setOriginStation(String originStation) {
	        this.originStation = originStation;
	    }

	    public String getDestinationStation() {
	        return destinationStation;
	    }

	    public void setDestinationStation(String destinationStation) {
	        this.destinationStation = destinationStation;
	    }

	    public LocalDateTime getBookingTime() {
	        return bookingTime;
	    }

	    public void setBookingTime(LocalDateTime bookingTime) {
	        this.bookingTime = bookingTime;
	    }




    
    
}
