package com.diego.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.example.dao.UserDao;
import com.diego.example.entity.RoleEntity;
import com.diego.example.entity.UserEntity;
import com.diego.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public List<UserEntity> findAll() {
		return (List<UserEntity>) userDao.findAll();
	}

	public Optional<UserEntity> findById(int id) {
		return userDao.findById(id);
	}

	public List<UserEntity> findByName(String name) {
		return userDao.findByName(name);
	}

	public List<UserEntity> findByLastname(String lastname) {
		return userDao.findByLastName(lastname);
	}
	
	public List<UserEntity> findByRole(RoleEntity role){
		return userDao.findByRole(role);
	}

	public UserEntity save(UserEntity user) {
		return userDao.save(user);
	}
}
