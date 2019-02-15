package com.demo.spring.todo.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.spring.todo.entity.ToDo;
import com.demo.spring.todo.jpa.TodoRepository;

@Component
public class TodoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
	
	@Autowired
	private TodoRepository repository;
	
	public ToDo getTodo(Long id) throws Exception {
		LOGGER.debug("Getting todo from id: {}", id);
		ToDo todo = repository.findById(id).orElse(null);
		if (todo != null) {
			return todo;
		} else {
			throw new Exception("No Todo Found for id: " + id);
		}
	}
	
	public List<ToDo> getTodoList() {
		LOGGER.debug("Getting all todos");
		return (List<ToDo>) repository.findAll();
	}
	
	public boolean saveTodo(ToDo todo) {
		boolean result = false;
		LOGGER.debug("Adding todo: {},  due by: {}", todo.getName(), todo.getDueDate());
		repository.save(todo);
		result = true;
		return result;
	}
	
	public boolean completeTodo(Long id) {
		boolean completeStatus = false;
		LOGGER.debug("Marking todo id: {} as completed..", id);
		ToDo completeTodo = repository.findById(id).orElse(null);
		if(completeTodo != null) {
			completeTodo.setCompleted(true);
			completeTodo.setCompleteDate(new Date());
			repository.save(completeTodo);
			LOGGER.info("Todo {} completed successfully", completeTodo.getName());
			completeStatus = true;
		} else {
			LOGGER.debug("No active todo found for id: {}", id);
		}
		return completeStatus;
	}
	
	public boolean delete(Long id) throws Exception {
		boolean result = false;
		LOGGER.debug("Deleting todo: {}", id);
		repository.deleteById(id);
		result = true;
		return result;
	}

}
