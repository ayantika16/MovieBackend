package com.example.demo.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movie;
import com.example.demo.model.Ticket;

@Repository
@Transactional
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
	
	@Query(value="select t from Ticket t where t.movie_id_fk= :movieId")
	public List<Ticket> getTicketList(int movieId);
	
	@Modifying
	@Query(value="delete from Ticket where movie_id_fk= :movieId")
	public void deleteTicketData(int movieId);

}
