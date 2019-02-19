package com.todo.rest.user.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.todo.rest.user.userservice.jpa.entity.Category;
import com.todo.rest.user.userservice.service.CategoryService;

@RestController("/v1")
public class CategoryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable("id") Long id) throws Exception {
		
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.debug("Getting the category by id: {}", id);
		Category category = categoryService.getCategoryById(id);
		LOGGER.debug("Category found - {}", category.getName());
		watch.stop();
		LOGGER.info("Category find service took {} Ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<Category>(category, HttpStatus.OK);
		
	}

}
