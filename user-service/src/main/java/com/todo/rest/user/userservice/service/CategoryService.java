package com.todo.rest.user.userservice.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.todo.rest.user.userservice.jpa.CategoryRepository;
import com.todo.rest.user.userservice.jpa.entity.Category;

@Component
public class CategoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category getCategoryById(Long id) {
		Category category = categoryRepository.findById(id).get();
		return category;
	}
	
	public Category saveCategory(Category category) {
		Category savedCategory = categoryRepository.save(category);
		return savedCategory;
	}
	
	public List<Category> getAllCategories() throws Exception {
		return Lists.newArrayList(categoryRepository.findAll());
	}
	
	public void deleteCategory (Long id) throws Exception {
		Category deleteCategory = categoryRepository.findById(id).get();
		if(deleteCategory != null) {
			categoryRepository.delete(deleteCategory);
			LOGGER.info("Category: {} has been deleted successfully.", deleteCategory.getName());
		} else {
			LOGGER.error("Cannot find the a category on id: {}", id);
			throw new SQLException("Cannot delete category id: " + id + ", it doesn't exist in db");
		}
	}

}
