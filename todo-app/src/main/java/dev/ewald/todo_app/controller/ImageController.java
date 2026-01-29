package dev.ewald.todo_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.ewald.todo_app.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;
    
    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String setImage() {
        imageService.setImage();

        return "image created";
    }
}
