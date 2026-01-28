package dev.ewald.todo_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ewald.todo_app.model.Todo;
import dev.ewald.todo_app.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void createTodo(String name) {
        if (name == null || name.length() > 140) {
            log.warn("Rejected todo: too long ({} characters)", name != null ? name.length() : 0);
            return;
        }
        
        if (name != null && !name.isBlank()) {
            Todo todo = new Todo();
            todo.setName(name);
            
            todoRepository.save(todo);
            log.info("Successfully saved todo: {}", todo);
        }
    }
}
