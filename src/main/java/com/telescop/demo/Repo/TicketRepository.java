package com.telescop.demo.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.telescop.demo.Model.Ticket;

public interface TicketRepository  extends JpaRepository<Ticket, Long>{
    List<Ticket> findByOriginStationAndDestinationStation(String originStation, String destinationStation);

	

}
