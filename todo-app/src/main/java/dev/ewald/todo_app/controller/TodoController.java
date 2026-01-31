package dev.ewald.todo_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.ewald.todo_app.model.Todo;
import dev.ewald.todo_app.service.TodoService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todos")
@Slf4j
public class TodoController {
    @Autowired
    TodoService todoService;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTodo(@RequestBody String todo) {
        log.info("Received todo request: {}", todo);
        todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void toggleTodo(@PathVariable Long id) {
        todoService.toggleDone(id);
    }
}
