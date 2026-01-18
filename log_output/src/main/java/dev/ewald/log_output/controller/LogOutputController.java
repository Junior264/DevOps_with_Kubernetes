package dev.ewald.log_output.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getTimeStampedRandomString() {
        return logOutputService.getTimeStampedRandomString();
    }
    
}
