package com.teamcodelop.lms.admin.service;

import com.teamcodelop.lms.dao.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    TodoDTO createTodo(TodoDTO todoDTO);
    List<TodoDTO> getAllTodos();
    TodoDTO getTodoById(Long id);
    TodoDTO updateTodo(TodoDTO todoDTO);
    String deleteTodo(Long id);
    String completeTodo(Long id);
}
