package dev.ewald.todo_app.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final List<String> todos = new CopyOnWriteArrayList<>(List.of());

    public List<String> getTodos() {
        return todos;
    }

    public void createTodo(String todo) {
        if (todo != null && !todo.isBlank()) {
            todos.add(todo);
        }
    }
}
