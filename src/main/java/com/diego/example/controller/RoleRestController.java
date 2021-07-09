package com.diego.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.example.entity.RoleEntity;
import com.diego.example.serviceimpl.RoleServiceImpl;
import com.diego.example.vo.ResponseVO;

@RestController
@RequestMapping("/role")
public class RoleRestController {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@GetMapping()
	public ResponseEntity<ResponseVO<List<RoleEntity>>> findAll(){
		List<RoleEntity> result = null;
		ResponseVO<List<RoleEntity>> response = new ResponseVO<>();
		try {
			result = roleService.findAll();
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseVO<RoleEntity>> findById(@RequestParam int id){
		Optional<RoleEntity> result = Optional.empty();
		ResponseVO<RoleEntity> response = new ResponseVO<>();
		try {
			result = roleService.findById(id);
			if(result.isPresent()) {
				response.setData(result.get());
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<ResponseVO<RoleEntity>> save(@RequestBody RoleEntity role){
		RoleEntity result = null;
		ResponseVO<RoleEntity> response = new ResponseVO<>();
		try {
			result = roleService.save(role);
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseVO<RoleEntity>> update(@RequestBody RoleEntity role, @RequestParam int id){
		Optional<RoleEntity> result = Optional.empty();
		ResponseVO<RoleEntity> response = new ResponseVO<>();
		try {
			result = roleService.findById(id);
			if(result.isPresent()) {
				RoleEntity roleVO = result.get();
				roleVO.setDescription(role.getDescription());
				roleService.save(roleVO);
				response.setData(roleVO);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
