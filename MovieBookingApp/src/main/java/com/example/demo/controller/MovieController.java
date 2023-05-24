package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.MovieAlreadyPresentException;
import com.example.demo.exceptions.MovieIdNotPresentException;
import com.example.demo.exceptions.NoMoviePresentException;
import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;
import com.example.demo.service.AuthorizationService;
//import com.example.demo.service.DataPublisherServiceImpl;
import com.example.demo.service.MovieService;
import com.example.demo.service.TicketService;

@RestController
@RequestMapping("api/v1/moviebooking")
@CrossOrigin
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private AuthorizationService authorizationService;
	
//	@Autowired
//	DataPublisherServiceImpl dp;
	
	@PostMapping("/addMovie")
	//@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> addMovie(@RequestHeader("Authorization") String jwt, @RequestBody Movie movie) throws MovieAlreadyPresentException{
		
		if(jwt.length()>0 && authorizationService.validateJwt(jwt)) {
		if(movieService.addMovie(movie)!= null) {
			
			//dp.setTemp(movie.getMovieName()+" Added Successfully");
			return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
			
		}
		
		//dp.setTemp(movie.getMovieName()+"not added");
		
		return new ResponseEntity<String>("Movie is null", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>("JWT Validation Failed", HttpStatus.BAD_REQUEST);
		
	}
	@GetMapping("/getAllMovies")
	//@PreAuthorize("hasRole(2)")
	public ResponseEntity<?> getAllMovies(@RequestHeader("Authorization") String jwt) throws NoMoviePresentException, MovieIdNotPresentException{
		
		
		if(jwt.length()>0 && authorizationService.validateJwt(jwt)) {
			
		List<Movie> movieList=movieService.getAllMovies();
		
		if(movieList!=null) {
			
			for(Movie m: movieList) {
				List<Ticket> ticketList=ticketService.getAllTickets(m.getMovieId());
				m.setTicketList(ticketList);
			}
			//dp.setTemp("Fetched All Movies");
			return new ResponseEntity<List<Movie>>(movieList, HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("MovieList is empty", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<String>("JWT Validation Failed", HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/deleteMovie/{mid}")
	//("hasRole('Admin')")
	public ResponseEntity<?> deleteMovie(@RequestHeader("Authorization") String jwt, @PathVariable int mid) throws MovieIdNotPresentException{
		
		if(jwt.length()>0 && authorizationService.validateJwt(jwt)) {
		if( movieService.deleteMovie(mid) & ticketService.deleteTicket(mid)) {
			//dp.setTemp(mid+" Movie & Tickets deleted");
			return new ResponseEntity<String>(mid+" Movie & Tickets Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>(mid+" Movie is not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("JWT Validation Failed", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/updateMovie")
	//@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<?> updateMovie(@RequestHeader("Authorization") String jwt, @RequestBody Movie movie) throws MovieIdNotPresentException{
		
		if(jwt.length()>0 && authorizationService.validateJwt(jwt)) {
		if(movieService.updateMovie(movie)) {
			//dp.setTemp(movie.getMovieName()+" Updated Successfully");
			return new ResponseEntity<String>(movie.getMovieName()+" Movie Updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>(movie.getMovieName()+" Movie is not updated", HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
		return new ResponseEntity<String>("JWT Validation Failed", HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/searchByMovieId/{mid}")
	//("hasRole('User')")
	public ResponseEntity<?> searchByMovieId(@RequestHeader("Authorization") String jwt, @PathVariable int mid) throws MovieIdNotPresentException{
		if(jwt.length()>0 && authorizationService.validateJwt(jwt)) {
		if(movieService.searchMovieById(mid)!= null) {
			List<Ticket> ticketList=ticketService.getAllTickets(mid);
			movieService.searchMovieById(mid).setTicketList(ticketList);
			return new ResponseEntity<Movie>(movieService.searchMovieById(mid), HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("Movie not present", HttpStatus.NO_CONTENT);
		
		}
		
		return new ResponseEntity<String>("JWT Validation Failed", HttpStatus.BAD_REQUEST);
	}
	
	
	
}
