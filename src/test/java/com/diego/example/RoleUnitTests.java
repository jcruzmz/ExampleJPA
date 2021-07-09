package com.diego.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diego.example.controller.RoleRestController;
import com.diego.example.entity.RoleEntity;
import com.diego.example.serviceimpl.RoleServiceImpl;
import com.diego.example.vo.ResponseVO;

@SpringBootTest
class RoleUnitTests {
	
	@Autowired
	private RoleRestController roleController;
	
	@SpyBean
	private RoleServiceImpl roleService;
	
	private RoleEntity role = new RoleEntity(1, "Test");
	
	
	@Test
	void testFindAll() {
		System.out.println("Get all role test");
		ResponseEntity<ResponseVO<List<RoleEntity>>> response = roleController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void testFindAllError() {
		System.out.println("Get all role error test");
		when(this.roleService.findAll()).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<List<RoleEntity>>> response = roleController.findAll();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
	
	@Test
	void testFindBydId() {
		System.out.println("Get role by id test");
		when(this.roleService.findById(1)).thenReturn(Optional.of(this.role));
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.findById(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}

	@Test
	void testFindBydIdNotFound() {
		System.out.println("Get role by id not found test");
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.findById(0);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody().getData());
	}
	
	@Test
	void testFindBydIdError() {
		System.out.println("Get role by id not found test");
		when(this.roleService.findById(1)).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.findById(1);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}
	
	@Test
	void testCreateRole() {
		System.out.println("Create role test");
		when(this.roleService.save(this.role)).thenReturn(this.role);
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.save(this.role);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}
	
	@Test
	void testCreateRoleError() {
		System.out.println("Create role test error");
		when(this.roleService.save(this.role)).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.save(this.role);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testUpdateRole() {
		System.out.println("Update role test");
		when(this.roleService.findById(1)).thenReturn(Optional.of(this.role));
		when(this.roleService.save(this.role)).thenReturn(this.role);
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.update(this.role, 1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}
	
	@Test
	void testUpdateRoleNotFound() {
		System.out.println("Update role test");
		RoleEntity role = new RoleEntity(1, "Test");
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.update(role, 0);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody().getData());
	}
	
	@Test
	void testUpdateRoleError() {
		System.out.println("Update role test error");
		when(this.roleService.findById(1)).thenThrow(new RuntimeException());
		RoleEntity role = new RoleEntity(1, "Test");
		ResponseEntity<ResponseVO<RoleEntity>> response = roleController.update(role, 1);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

}
