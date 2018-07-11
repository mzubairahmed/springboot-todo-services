package com.demo.spring.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.todo.entity.ToDo;
import com.demo.spring.todo.service.TodoService;

@RestController
public class TodoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todo/{id}")
	public ResponseEntity<ToDo> getTodo(@PathVariable("id") Long id) throws Exception {
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.info("TODO Service: getById: {}", id);
		ToDo todo = todoService.getTodo(id);
		watch.stop();
		LOGGER.info("TODO Service: responded in {} ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}
	
	@GetMapping("/todo/all")
	public ResponseEntity<List<ToDo>> getAllTodo() {
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.info("TODO Service: get todo list");
		List<ToDo> todos = todoService.getTodoList();
		watch.stop();
		LOGGER.info("TODO Service: responded in {} ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}
	
	
	@PostMapping("/todo")
	public ResponseEntity<ToDo> getTodo(@RequestBody ToDo todo) {
		StopWatch watch = new StopWatch();
		watch.start();
		LOGGER.info("TODO Service: save todo {}", todo.getName());
		todoService.saveTodo(todo);
		watch.stop();
		LOGGER.info("TODO Service: responded in {} ms", watch.getLastTaskTimeMillis());
		return new ResponseEntity<>(todo, HttpStatus.OK);
	}


}
