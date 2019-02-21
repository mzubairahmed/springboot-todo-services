package com.todo.rest.user.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.rest.user.userservice.jpa.entity.User;
import com.todo.rest.user.userservice.service.UserService;

@RestController
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/byname/{userName}")
	public ResponseEntity<User> getUserInfo(@PathVariable("userName") String userName) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		User user = userService.getUserByUserName(userName);
		watch.stop();
		LOGGER.info("User: {}, {} found by username in {} ms", user.getLastName(), user.getFirstName(), watch.getTotalTimeMillis());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		User user = userService.getUserById(id);
		watch.stop();
		LOGGER.info("User: {}, {} found by id in {} ms", user.getLastName(), user.getFirstName(), watch.getTotalTimeMillis());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/exists/{userName}")
	public ResponseEntity<Boolean> userAlreadyExists(@PathVariable("userName") String userName) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		Boolean userExists = userService.userExists(userName);
		if(userExists) {
			LOGGER.info("Username: {} already exits", userName);
		} else {
			LOGGER.info("Username: {} doesn't exit", userName);
		}
		watch.stop();
		LOGGER.info("Username validation service took {} ms", watch.getTotalTimeMillis());
		return new ResponseEntity<>(userExists, HttpStatus.OK);
	}
	
	
	public ResponseEntity<List<User>> getAllUsers() throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		List<User> users = userService.listAllUsers();
		watch.stop();
		LOGGER.info("Total {} users found in {} ms", users.size(), watch.getTotalTimeMillis());
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/user/")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		User savedUser = userService.saveUser(user);
		watch.stop();
		LOGGER.info("User successfully saved - DB ID: {} in {} ms", savedUser.getId(), watch.getTotalTimeMillis());
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		Boolean deleteStatus = userService.deleteUser(id);
		watch.stop();
		LOGGER.info("User deleted successfully in {} ms", watch.getTotalTimeMillis());
		return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
	}

}
