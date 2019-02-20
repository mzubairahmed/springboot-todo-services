package com.todo.rest.user.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todo.rest.user.userservice.exception.UserAlreadyExistsException;
import com.todo.rest.user.userservice.exception.UserNotExistException;
import com.todo.rest.user.userservice.jpa.UserRepository;
import com.todo.rest.user.userservice.jpa.entity.User;

@Component
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByUserName(String userName) throws Exception {
		LOGGER.info("Getting user by userName: {}", userName);
		return userRepository.findUserByUserName(userName);
	}
	
	public User getUserById(Long userId) throws Exception {
		LOGGER.info("Getting user by id: {}", userId);
		return userRepository.findById(userId).get();
	}
	
	public User saveUser(User user) throws Exception {
		User savedUser;
		if(!userExists(user.getUserName())) {
			savedUser = userRepository.save(user);
			LOGGER.info("User: {} successfully saved", savedUser.getUserName());
			return savedUser;
		} else {
			LOGGER.error("user {} already exists", user.getUserName());
			throw new UserAlreadyExistsException(String.format("Username: {} already exists", user.getUserName()));
		}
	}
	
	public boolean deleteUser(Long userId) throws Exception {
		User deleteUser = userRepository.findById(userId).get();
		if(deleteUser != null) {
			LOGGER.error("Cannot delete user id: {}, user doesn't exist", userId);
			throw new UserNotExistException();
		} else {
			userRepository.delete(deleteUser);
			LOGGER.info("User deleted successfully.");
			return Boolean.TRUE;
		}
	}
	
	public boolean userExists(String userName) throws Exception {
		User user = userRepository.findUserByUserName(userName);
		if(user != null && user.getId() != null && user.getId() > 0) {
			return true;
		} else {
			return false;
		}
	}

}
