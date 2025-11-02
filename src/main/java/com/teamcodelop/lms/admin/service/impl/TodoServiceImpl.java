package com.teamcodelop.lms.admin.service.impl;

import com.teamcodelop.lms.admin.exception.TodoNotFoundException;
import com.teamcodelop.lms.admin.service.TodoService;
import com.teamcodelop.lms.dao.dto.TodoDTO;
import com.teamcodelop.lms.dao.entity.Todo;
import com.teamcodelop.lms.dao.mapper.TodoMapper;
import com.teamcodelop.lms.dao.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoMapper todoMapper;

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo = todoMapper.toEntity(todoDTO);
        todoRepository.save(todo);
        return todoMapper.toDTO(todo);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todoMapper.toDTOList(todos);
    }

    @Override
    public TodoDTO getTodoById(Long id) {
        Todo todoById = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found"));
        return todoMapper.toDTO(todoById);
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO) {
        Long id = todoDTO.getId();
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found"));
        todoMapper.updateEntityFromDTO(todoDTO, existingTodo);
        Todo updatedTodo = todoRepository.save(existingTodo);
        return todoMapper.toDTO(updatedTodo);
    }

    @Override
    public String deleteTodo(Long id) {
        if(!todoRepository.existsById(id)){
            throw new TodoNotFoundException("Todo with id " + id + " not found");
        }
        todoRepository.deleteById(id);
        return "Todo Deleted Successfully!";
    }

    @Override
    public String completeTodo(Long id) {
        Todo todoToComplete = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo with id " + id + " not found"));
        todoToComplete.setIsCompleted(!todoToComplete.getIsCompleted());
        todoRepository.save(todoToComplete);
        return "Todo Status Changed Successfully!";
    }
}
