package it.iacovelli.todospringboot3benchmark.controller;

import it.iacovelli.todospringboot3benchmark.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<String> objectNotFound(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

}
