package dev.ewald.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ewald.todo_app.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
}
