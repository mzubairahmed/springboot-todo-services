package com.todo.rest.user.userservice.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.todo.rest.user.userservice.jpa.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	

}
