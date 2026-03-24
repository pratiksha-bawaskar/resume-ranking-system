package com.pratiksha.user_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pratiksha.user_service.entity.User;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return List.of();
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public void handleOptions() {
    }

    @GetMapping("/test")
    public String test() {
        return "User Service Working 🚀";
    }
}