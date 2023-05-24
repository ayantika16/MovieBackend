package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.MovieIdNotPresentException;
import com.example.demo.exceptions.NoMoviePresentException;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService{
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Ticket> getTickets() {
		List<Ticket> tickets=ticketRepository.findAll();
		
		
		return tickets;
	}

	@Override
	public List<Ticket> getAllTickets(int movieId) throws MovieIdNotPresentException {
		
		Optional<Movie> movObj=movieRepository.findById(movieId);
		
		if(!movObj.isPresent()) {
			throw new MovieIdNotPresentException();
		}
		
		List<Ticket> ticketList=ticketRepository.getTicketList(movieId);
		return ticketList;
	}

	@Override
	public boolean bookTicket(Ticket ticket) {
		
		Ticket ticketObj= new Ticket();
		
		ticketObj.setBookedSeats(ticket.getBookedSeats());
		ticketObj.setBookingName(ticket.getBookingName());
		ticketObj.setMovie_id_fk(ticket.getMovie_id_fk());
		ticketObj.setSeatsAvailable(ticket.getSeatsAvailable());
		ticketRepository.saveAndFlush(ticketObj);
		return true;
	}

	@Override
	public boolean deleteTicket(int movieId) {
		
//		Optional<Movie> movObj=movieRepository.findById(movieId);
//		
//		if(!movObj.isPresent()) {
//			throw new MovieIdNotPresentException();
//		}
		
		ticketRepository.deleteTicketData(movieId);
		return true;
	}

	

}
