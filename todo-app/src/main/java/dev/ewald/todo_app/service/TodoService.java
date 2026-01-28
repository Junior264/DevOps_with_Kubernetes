package dev.ewald.todo_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ewald.todo_app.model.Todo;
import dev.ewald.todo_app.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void createTodo(String name) {
        if (name != null && !name.isBlank()) {
            Todo todo = new Todo();
            todo.setName(name);
            
            todoRepository.save(todo);
        }
    }
}
