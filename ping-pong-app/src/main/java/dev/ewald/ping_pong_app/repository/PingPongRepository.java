package dev.ewald.ping_pong_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ewald.ping_pong_app.model.Counter;

@Repository
public interface PingPongRepository extends JpaRepository<Counter, Integer> {
    
}