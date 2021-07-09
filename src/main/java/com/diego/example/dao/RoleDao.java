package com.diego.example.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diego.example.entity.RoleEntity;

@Repository
@Qualifier("roleDao")
public interface RoleDao extends CrudRepository<RoleEntity, Integer>{

}
