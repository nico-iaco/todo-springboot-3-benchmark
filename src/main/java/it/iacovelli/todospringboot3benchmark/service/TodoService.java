package it.iacovelli.todospringboot3benchmark.service;

import it.iacovelli.todospringboot3benchmark.exception.TodoNotFoundException;
import it.iacovelli.todospringboot3benchmark.model.dto.TodoDto;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    TodoDto createTodo(TodoDto todoDto);

    TodoDto editTodo(TodoDto todoDto) throws TodoNotFoundException;

    void deleteTodo(UUID todoId) throws TodoNotFoundException;

    List<TodoDto> findAllTodos();

}
