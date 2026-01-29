package dev.ewald.ping_pong_app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.ewald.ping_pong_app.service.PingPongService;

@RestController
@RequestMapping("/")
public class PingPongController {
    @Autowired
    PingPongService pingpongService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/pingpong")
    @ResponseStatus(HttpStatus.OK)
    public String getPongCounter() throws IOException {
        return pingpongService.pongCounter();
    }

    @GetMapping("/pings")
    @ResponseStatus(HttpStatus.OK)
    public Integer getPings() throws IOException {
        return pingpongService.getPings();
    }
}
