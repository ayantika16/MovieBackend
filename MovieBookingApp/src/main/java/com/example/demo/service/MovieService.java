package com.example.demo.service;

import java.util.List;

import com.example.demo.exceptions.MovieAlreadyPresentException;
import com.example.demo.exceptions.MovieIdNotPresentException;
import com.example.demo.exceptions.NoMoviePresentException;
import com.example.demo.model.Movie;

public interface MovieService {

	public List<Movie> getAllMovies() throws NoMoviePresentException;
	public Movie addMovie(Movie movie) throws MovieAlreadyPresentException;
	public boolean deleteMovie(int mid) throws MovieIdNotPresentException;
	public boolean updateMovie(Movie movie) throws MovieIdNotPresentException;
	public Movie searchMovieById(int mid) throws MovieIdNotPresentException;
	
	
}
