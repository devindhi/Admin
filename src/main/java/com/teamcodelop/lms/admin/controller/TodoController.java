package com.teamcodelop.lms.admin.controller;

import com.teamcodelop.lms.dao.dto.TodoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TodoController {
    @PostMapping("/save")
    TodoDTO createTodo(@RequestBody TodoDTO todoDTO);

    @GetMapping("/get")
    List<TodoDTO> getAllTodos();

    @GetMapping("/get/{id}")
    TodoDTO getTodoById(@PathVariable Long id);

    @PutMapping("/update")
    TodoDTO updateTodo(@RequestBody TodoDTO todoDTO);

    @DeleteMapping("/delete/{id}")
    String deleteTodo(@PathVariable Long id);

    @PutMapping("/complete/{id}")
    String completeTodo(@PathVariable Long id);
}

