package it.iacovelli.todospringboot3benchmark.controller;

import it.iacovelli.todospringboot3benchmark.model.dto.TodoDto;
import it.iacovelli.todospringboot3benchmark.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController extends BaseController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto todo = todoService.createTodo(todoDto);
        return ResponseEntity.ok(todo);
    }

    @PatchMapping("/")
    public ResponseEntity<TodoDto> editTodo(@RequestBody TodoDto todoDto) {
        TodoDto editedTodo = todoService.editTodo(todoDto);
        return ResponseEntity.ok(editedTodo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable UUID todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<TodoDto>> getTodos() {
        List<TodoDto> todoList = todoService.findAllTodos();
        return ResponseEntity.ok(todoList);
    }

}
