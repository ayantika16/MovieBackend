//package com.example.demo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.example.demo.exceptions.NoMoviePresentException;
//import com.example.demo.model.Movie;
//
//import com.example.demo.repository.MovieRepository;
//import com.example.demo.service.MovieServiceImpl;
//
//@AutoConfigureMockMvc
//@SpringBootTest
//public class MovieServiceImplTest {
//	
//	@Mock
//	private MovieRepository movieRepo;
//	
//	@InjectMocks
//	private MovieServiceImpl movieService;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@BeforeEach
//	public void init()
//	{
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(movieService).build();
//	}
//	
//	List<Movie> movieList=new ArrayList<Movie>();
//	
//	@Test
//	public void getAllMoviesSuccess() throws Exception{
//		
//		Movie mov=new Movie();
//		mov.setMovieId(101);
//		mov.setMovieName("RRR");
//		mov.setTheatreName("Inox");
//		mov.setTotalSeats(100);
//		mov.setSeatsAvailable(100);
//		
//		movieList.add(mov);
//		
//		when(movieRepo.findAll()).thenReturn(movieList);
//		
//		List<Movie> mList = movieService.getAllMovies();
//		assertEquals(movieList, mList);
//		
//		
//	}
//	
//	@Test
//	public void getAllMoviesFailure() throws Exception
//	{
//		when(movieRepo.findAll()).thenReturn(null);
//		//when(movieService.getAllMovies()).thenThrow(new NoMoviePresentException());
//		
//		assertThrows(NoMoviePresentException.class, () -> {
//	        movieService.getAllMovies();
//	    });
//		
//	}
//	
//	@Test
//	public void addMovieSuccess() throws Exception
//	{
//		Movie mov=new Movie();
//		mov.setMovieId(101);
//		mov.setMovieName("RRR");
//		mov.setTheatreName("Inox");
//		mov.setTotalSeats(100);
//		mov.setSeatsAvailable(100);
//		
//		movieList.add(mov);
//		when(movieRepo.saveAndFlush(any())).thenReturn(mov);
//		
//		Movie mv = movieService.addMovie(mov);
//		assertEquals(mov, mv);
//		
//	}
//	
//	
//
//}
