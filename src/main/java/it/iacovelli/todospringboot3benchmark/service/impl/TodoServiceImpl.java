package it.iacovelli.todospringboot3benchmark.service.impl;

import it.iacovelli.todospringboot3benchmark.exception.TodoNotFoundException;
import it.iacovelli.todospringboot3benchmark.mapper.TodoMapper;
import it.iacovelli.todospringboot3benchmark.model.Todo;
import it.iacovelli.todospringboot3benchmark.model.dto.TodoDto;
import it.iacovelli.todospringboot3benchmark.repository.TodoRepository;
import it.iacovelli.todospringboot3benchmark.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todoEntity = todoMapper.fromDtoToEntity(todoDto);
        Todo savedTodoEntity = todoRepository.save(todoEntity);
        return todoMapper.fromEntityToDto(savedTodoEntity);
    }

    @Override
    public TodoDto editTodo(TodoDto todoDto) throws TodoNotFoundException {
        Todo todoEntity = todoRepository.findById(todoDto.id())
                .orElseThrow(() -> new TodoNotFoundException("Todo with id " + todoDto.id() + " was not found!!"));
        Todo editedTodo = todoMapper.updateEntityFromDto(todoDto, todoEntity);
        Todo savedTodo = todoRepository.save(editedTodo);
        return todoMapper.fromEntityToDto(savedTodo);
    }

    @Override
    public void deleteTodo(UUID todoId) throws TodoNotFoundException {
        Todo todoEntity = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo with id " + todoId + " was not found!!"));
        todoRepository.delete(todoEntity);
    }

    @Override
    public List<TodoDto> findAllTodos() {
        return todoRepository.findAllByOrderByPublishedDateAsc()
                .stream()
                .map(todoMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

}
