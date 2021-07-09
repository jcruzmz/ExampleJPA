package com.diego.example.service;

import java.util.List;
import java.util.Optional;

import com.diego.example.entity.RoleEntity;
import com.diego.example.entity.UserEntity;

public interface UserService {

	public List<UserEntity> findAll();

	public Optional<UserEntity> findById(int id);
	
	public List<UserEntity> findByName(String name);
	
	public List<UserEntity> findByLastname(String lastname);
	
	public List<UserEntity> findByRole(RoleEntity role);
	
	public UserEntity save(UserEntity user); 
}
