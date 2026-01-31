package dev.ewald.todo_app.service;

import io.nats.client.Connection;
import io.nats.client.Nats;

import java.nio.charset.StandardCharsets;
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
            notifyNats("A todo was created: " + name);
            log.info("Successfully saved todo: {}", todo);
        }
    }

    public void toggleDone(Long id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setDone(!todo.isDone());

        todoRepository.save(todo);
        notifyNats("A todo was updated" + todo.getName());
        log.info("Todo {} status changed to done: {}", id, todo.isDone());
    }

    public void notifyNats(String message) {
        try (Connection nc = Nats.connect("nats://nats-svc:4222")) {
            nc.publish("todo_events", message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("NATS error", e);
        }
    }
}
