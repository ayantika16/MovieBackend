package com.example.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.entity.Role;

@Repository
@Transactional
public interface RoleDao extends JpaRepository<Role, Integer> {

}
