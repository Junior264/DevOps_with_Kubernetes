package dev.ewald.log_output.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ewald.log_output.service.LogOutputService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/")
public class LogOutputController {
    @Autowired
    private LogOutputService logOutputService;

    @GetMapping("/one")
    @ResponseStatus(HttpStatus.OK)
    public String getTimeStampedRandomString() {
        return logOutputService.getTimeStampedRandomString();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getTimeStampedRandomStrings() throws IOException {
        return logOutputService.getTimeStampedRandomStrings();
    }

    @GetMapping(value = "/pong", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String getTimeStampedRandomStringWithPongCount() throws IOException {
        return logOutputService.getTimeStampedRandomStringWithPongCount();
    }
    
}
