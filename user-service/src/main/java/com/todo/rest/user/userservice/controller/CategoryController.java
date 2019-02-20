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

	@PostMapping("/category/")
	public ResponseEntity<Category> saveCategory(@RequestBody Category saveCategory) throws Exception {
		
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.debug("Create new category: {}", saveCategory.getName());
		Category category = categoryService.saveCategory(saveCategory);
		watch.stop();
		LOGGER.info("Category saved, took {} Ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<Category>(category, HttpStatus.OK);
		
	}
	
	@GetMapping("/category/all")
	public ResponseEntity<List<Category>> getAllCategories() throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.debug("Getting all categories");
		List<Category> categories = categoryService.getAllCategories();
		LOGGER.debug("Total {} categories found", categories.size());
		watch.stop();
		LOGGER.info("Category find service took {} Ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.debug("deleting category by id: {}", id);
		categoryService.deleteCategory(id);
		watch.stop();
		LOGGER.info("Category deleted successfully in {} Ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<Category>(HttpStatus.OK);
	}
	
}
