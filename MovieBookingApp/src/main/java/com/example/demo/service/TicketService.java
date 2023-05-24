package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.MovieIdNotPresentException;
import com.example.demo.exceptions.NoMoviePresentException;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;

public interface TicketService {
	
	public List<Ticket> getAllTickets(int movieId) throws MovieIdNotPresentException;
	public boolean bookTicket(Ticket ticket);
	public boolean deleteTicket(int movieId) throws MovieIdNotPresentException;
	
	public List<Ticket> getTickets();

}
