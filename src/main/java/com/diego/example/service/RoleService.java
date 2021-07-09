package com.diego.example.service;

import java.util.List;
import java.util.Optional;

import com.diego.example.entity.RoleEntity;

public interface RoleService {
	public List<RoleEntity> findAll();

	public Optional<RoleEntity> findById(int id);
	
	public RoleEntity save(RoleEntity role);

}
