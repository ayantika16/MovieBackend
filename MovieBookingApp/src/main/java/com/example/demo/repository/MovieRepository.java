package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movie;


@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie,Integer>{

}
