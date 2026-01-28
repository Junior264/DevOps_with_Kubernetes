package dev.ewald.todo_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.ewald.todo_app.model.Todo;
import dev.ewald.todo_app.service.TodoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @todo CORS open to every domain for learning purpose. FIX LATER...
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/todos")
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
        todoService.createTodo(todo);
    }
}
