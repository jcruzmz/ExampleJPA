package com.diego.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.diego.example.controller.RoleRestController;
import com.diego.example.entity.RoleEntity;
import com.diego.example.vo.ResponseVO;

@SpringBootTest
class RoleIntegrationTests {

	@Autowired
	private RoleRestController roleController;

	private RoleEntity role = new RoleEntity(1, "Test");

	@Test
	@Transactional
	void testFindAll() {
		System.out.println("Get all role test");
		ResponseEntity<ResponseVO<List<RoleEntity>>> response = roleController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@Transactional
	void testFindBydId() {
		System.out.println("Get role by id test");
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.findById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}

	@Test
	@Transactional
	void testCreateRole() {
		System.out.println("Create role test");
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.save(this.role);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}
	
	@Test
	@Transactional
	void testUpdateRole() {
		System.out.println("Update role test");
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.update(this.role, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}
}
