package com.example.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query(value="select u from User u where u.username= :username and u.password= :password")
	public User validateUser(@Param("username") String username, @Param("password") String password);//login
	
	
	@Query(value="select * from User where username= :name",nativeQuery = true)
	Optional<User> findByUserName(@Param("name") String name);
}
