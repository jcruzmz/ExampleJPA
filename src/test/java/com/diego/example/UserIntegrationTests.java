package com.diego.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.diego.example.controller.UserRestController;
import com.diego.example.entity.RoleEntity;
import com.diego.example.entity.UserEntity;
import com.diego.example.vo.ResponseVO;

@SpringBootTest
class UserIntegrationTests {

	@Autowired
	private UserRestController userController;

	private UserEntity userMock = new UserEntity(2, "Juan Diego", "Cruz", new RoleEntity(1, "Administrador"));

	private RoleEntity roleMock = new RoleEntity(1, null);
	
	@Test
	@Transactional
	void testFindAll() {
		System.out.println("Get all user test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	@Transactional
	void testFindBydId() {
		System.out.println("Get user by id test");
		ResponseEntity<ResponseVO<UserEntity>> response = userController.findById(5);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}
	
	@Test
	@Transactional
	void testFindByName() {
		System.out.println("Get user by name test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByName("Juan Diego");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().size() > 0);
	}
	
	@Test
	@Transactional
	void testFindByLastName() {
		System.out.println("Get user by lastname test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByLastname("Cruz");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().size() > 0);
	}
	
	@Test
	@Transactional
	void testFindByRole() {
		System.out.println("Get user by role test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByRole(this.roleMock);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().size() > 0);
	}
	
	@Test
	@Transactional
	void testCreateUser() {
		System.out.println("Create user test");
		ResponseEntity<ResponseVO<UserEntity>> response = userController.save(this.userMock);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}
}
