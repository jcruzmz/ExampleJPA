package com.diego.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.example.dao.RoleDao;
import com.diego.example.entity.RoleEntity;
import com.diego.example.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<RoleEntity> findAll() {
		return (List<RoleEntity>) roleDao.findAll();
	}

	public Optional<RoleEntity> findById(int id) {
		return roleDao.findById(id);
	}

	public RoleEntity save(RoleEntity role) {
		return roleDao.save(role);
	}
}
