package com.diego.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diego.example.entity.RoleEntity;
import com.diego.example.entity.UserEntity;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Integer> {
	
	List<UserEntity> findByName(String name);
	
	List<UserEntity> findByRole(RoleEntity role);
	
	@Query("select e from UserEntity e where e.lastname like %?1%")
	List<UserEntity> findByLastName(String lastName);
}
