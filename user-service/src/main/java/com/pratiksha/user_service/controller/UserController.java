package com.pratiksha.user_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratiksha.user_service.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @CrossOrigin(origins = "https://resume-ranking-app.netlify.app")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }

    @CrossOrigin(origins = "https://resume-ranking-app.netlify.app")
    @GetMapping
    public List<User> getUsers() {
        return List.of();
    }
}