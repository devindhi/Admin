package com.teamcodelop.lms.admin.controller.impl;

import com.teamcodelop.lms.admin.controller.TodoController;

import com.teamcodelop.lms.admin.exception.TodoNotFoundException;
import com.teamcodelop.lms.admin.service.TodoService;
//import com.teamcodelop.lms.dao
import com.teamcodelop.lms.dao.dto.TodoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/todo")
public class TodoControllerImpl implements TodoController {

    @Autowired
    private TodoService todoService;

    @Override
    public TodoDTO createTodo(TodoDTO todoDTO) {
        return todoService.createTodo(todoDTO);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        return todoService.getAllTodos();
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        return todoService.getTodoById(id);
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO) {
        return todoService.updateTodo(todoDTO);
    }

    @Override
    public String deleteTodo(Long id) {
        return todoService.deleteTodo(id);
    }

    @Override
    public String completeTodo(Long id) {
        return todoService.completeTodo(id);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<?> handleTodoNotFound(TodoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }
}
