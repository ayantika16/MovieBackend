package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.MovieAlreadyPresentException;
import com.example.demo.exceptions.MovieIdNotPresentException;
import com.example.demo.exceptions.NoMoviePresentException;
import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovies() throws NoMoviePresentException {
		List<Movie> movieList=movieRepository.findAll();
		
		if(movieList==null || movieList.size()==0) {
			throw new NoMoviePresentException();
		}
		
		return movieList;
	}

	@Override
	public Movie addMovie(Movie movie) throws MovieAlreadyPresentException {
		
		Optional<Movie> movObj=movieRepository.findById(movie.getMovieId());
		
		if(movObj.isPresent()) {
			throw new MovieAlreadyPresentException();
		}
		
		return movieRepository.saveAndFlush(movie);
		
	}

	@Override
	public boolean deleteMovie(int mid) throws MovieIdNotPresentException {
		Optional<Movie> movObj=movieRepository.findById(mid);
		
		if(!movObj.isPresent()) {
			throw new MovieIdNotPresentException();
		}
		
		movieRepository.deleteById(mid);
		return true;
	}

	@Override
	public boolean updateMovie(Movie movie) throws MovieIdNotPresentException {
		Optional<Movie> movObj=movieRepository.findById(movie.getMovieId());
		
		if(!movObj.isPresent()) {
			throw new MovieIdNotPresentException();
		}
		
		movieRepository.saveAndFlush(movie);
		return true;
	}

	@Override
	public Movie searchMovieById(int mid) throws MovieIdNotPresentException {
		Optional<Movie> movObj=movieRepository.findById(mid);
		
		if(!movObj.isPresent()) {
			throw new MovieIdNotPresentException();
		}
		
		return movObj.get();
	}

}
