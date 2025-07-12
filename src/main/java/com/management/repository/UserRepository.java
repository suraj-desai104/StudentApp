package com.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.role=?1")
	public List<User> Byrole(String role);
	
}
