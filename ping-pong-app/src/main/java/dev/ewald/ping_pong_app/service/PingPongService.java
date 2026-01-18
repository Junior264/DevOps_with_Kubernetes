package dev.ewald.ping_pong_app.service;

import org.springframework.stereotype.Service;

@Service
public class PingPongService {
    private int counter;

    public String pongCounter() {
        counter++;
        return "pong " + String.valueOf(counter);
    }
}
