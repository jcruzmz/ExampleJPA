package com.diego.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diego.example.controller.UserRestController;
import com.diego.example.entity.RoleEntity;
import com.diego.example.entity.UserEntity;
import com.diego.example.serviceimpl.UserServiceImpl;
import com.diego.example.vo.ResponseVO;

@SpringBootTest
class UserUnitTests {

	@Autowired
	private UserRestController userController;

	@MockBean
	private UserServiceImpl userServiceMock;

	private UserEntity userMock = new UserEntity(2, "Juan Diego", "Cruz", new RoleEntity(1, "Administrador"));
	
	private RoleEntity roleMock = new RoleEntity(1, null);

	private List<UserEntity> listUserMock = new ArrayList<UserEntity>() {
		private static final long serialVersionUID = 1L;
		{
			add(userMock);
			add(userMock);
			add(userMock);
		}
		
	};

	@Test
	void testFindAll() {
		System.out.println("Get all user test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findAll();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testFindAllError() {
		System.out.println("Get all user error test");
		when(this.userServiceMock.findAll()).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findAll();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testFindBydId() {
		System.out.println("Get user by id test");
		when(this.userServiceMock.findById(5)).thenReturn(Optional.of(this.userMock));
		ResponseEntity<ResponseVO<UserEntity>> response = userController.findById(5);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}

	@Test
	void testFindBydIdNotFound() {
		System.out.println("Get user by id not found test");
		ResponseEntity<ResponseVO<UserEntity>> response = userController.findById(0);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testFindBydIdError() {
		System.out.println("Get user by id error test");
		when(this.userServiceMock.findById(5)).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<UserEntity>> response = userController.findById(5);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testFindByName() {
		System.out.println("Get user by name test");
		when(this.userServiceMock.findByName("Juan Diego")).thenReturn(this.listUserMock);
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByName("Juan Diego");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().size() > 0);
	}

	@Test
	void testFindByNameNotFound() {
		System.out.println("Get user by name not found test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByName("Juanito");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getData().size());
	}
	
	@Test
	void testFindByNameError() {
		System.out.println("Get user by name error test");
		when(this.userServiceMock.findByName("Juan")).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByName("Juan");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testFindByLastName() {
		System.out.println("Get user by lastname test");
		when(this.userServiceMock.findByLastname("Cruz")).thenReturn(listUserMock);
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByLastname("Cruz");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().size() > 0);
	}

	@Test
	void testFindByLastNameNotFound() {
		System.out.println("Get user by lastname not found test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByLastname("Test");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getData().size());
	}
	
	@Test
	void testFindByLastNameError() {
		System.out.println("Get user by lastname not found test");
		when(this.userServiceMock.findByLastname("Test")).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByLastname("Test");
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testFindByRole() {
		System.out.println("Get user by role test");
		when(this.userServiceMock.findByRole(this.roleMock)).thenReturn(this.listUserMock);
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByRole(this.roleMock);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().getData().size() > 0);
	}

	@Test
	void testFindByRoleNull() {
		System.out.println("Get user by role not found test");
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByRole(this.roleMock);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(0, response.getBody().getData().size());
	}

	@Test
	void testFindByRoleError() {
		System.out.println("Get user by role error test");
		when(this.userServiceMock.findByRole(this.roleMock)).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<List<UserEntity>>> response = userController.findByRole(this.roleMock);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}

	@Test
	void testCreateUser() {
		System.out.println("Create user test");
		when(this.userServiceMock.save(this.userMock)).thenReturn(this.userMock);
		ResponseEntity<ResponseVO<UserEntity>> response = userController.save(this.userMock);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}

	@Test
	void testCreateUserNull() {
		System.out.println("Create user null test");
		when(this.userServiceMock.save(this.userMock)).thenThrow(new RuntimeException());
		ResponseEntity<ResponseVO<UserEntity>> response = userController.save(this.userMock);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertNull(response.getBody().getData());
	}
}
