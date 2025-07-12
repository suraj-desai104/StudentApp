package com.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.model.User;
import com.management.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	
	public List<User> GetAll()
	{
		return repository.findAll();
	}
	
	public User FindById(Integer id)
	{
		return repository.findById(id).orElseThrow();
	}
	
	
	public User Save(User cust)
	{
		return repository.save(cust);
	}
	
	public void Delete(Integer id)
	{
		repository.deleteById(id);
	}
	
	
	public List<User> ByUsingRole(String role)
	{
		return repository.Byrole(role);
	}
}

