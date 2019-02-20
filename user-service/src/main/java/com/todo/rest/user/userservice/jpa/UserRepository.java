package com.todo.rest.user.userservice.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.rest.user.userservice.jpa.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	User findUserByUserName(String userName);

}
