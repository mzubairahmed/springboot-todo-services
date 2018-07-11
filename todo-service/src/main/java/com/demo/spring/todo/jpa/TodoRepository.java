package com.demo.spring.todo.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.todo.entity.ToDo;

@Repository
public interface TodoRepository extends CrudRepository<ToDo, Long> {

}
