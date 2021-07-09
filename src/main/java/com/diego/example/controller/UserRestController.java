package com.diego.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diego.example.entity.RoleEntity;
import com.diego.example.entity.UserEntity;
import com.diego.example.serviceimpl.UserServiceImpl;
import com.diego.example.vo.ResponseVO;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping()
	public ResponseEntity<ResponseVO<List<UserEntity>>> findAll(){
		List<UserEntity> result = null;
		ResponseVO<List<UserEntity>> response = new ResponseVO<>();
		try {
			result = userService.findAll();
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseVO<UserEntity>> findById(@RequestParam int id){
		Optional<UserEntity> result = Optional.empty();
		ResponseVO<UserEntity> response = new ResponseVO<>();
		try {
			result = userService.findById(id);
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
	
	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseVO<List<UserEntity>>> findByName(@RequestParam String name){
		List<UserEntity> result = null;
		ResponseVO<List<UserEntity>> response = new ResponseVO<>();
		try {
			result = userService.findByName(name);
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/lastname/{lastname}")
	public ResponseEntity<ResponseVO<List<UserEntity>>> findByLastname(@RequestParam String lastname){
		List<UserEntity> result = null;
		ResponseVO<List<UserEntity>> response = new ResponseVO<>();
		try {
			result = userService.findByLastname(lastname);
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/role/{id}")
	public ResponseEntity<ResponseVO<List<UserEntity>>> findByRole(@RequestParam RoleEntity role){
		List<UserEntity> result = null;
		ResponseVO<List<UserEntity>> response = new ResponseVO<>();
		try {
			result = userService.findByRole(role);
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<ResponseVO<UserEntity>> save(@RequestBody UserEntity user){
		UserEntity result = null;
		ResponseVO<UserEntity> response = new ResponseVO<>();
		try {
			result = userService.save(user);
			response.setData(result);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
