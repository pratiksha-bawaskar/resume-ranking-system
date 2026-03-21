package com.pratiksha.user_service.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pratiksha.user_service.entity.User;
import com.pratiksha.user_service.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ CREATE USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        try {
            System.out.println("Incoming User: " + user.getName());
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("ERROR SAVING USER: " + e.getMessage());
            throw e;
        }
    }

    // ✅ GET ALL USERS
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}