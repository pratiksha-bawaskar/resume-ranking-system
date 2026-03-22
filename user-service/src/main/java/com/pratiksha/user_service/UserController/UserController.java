package com.pratiksha.user_service.UserController;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratiksha.user_service.entity.User;

@CrossOrigin(origins = "https://resume-ranking-app.netlify.app")
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println("Incoming User: " + user.getName());
        return user;
    }

    @GetMapping
    public List<User> getUsers() {
        return List.of();
    }
}