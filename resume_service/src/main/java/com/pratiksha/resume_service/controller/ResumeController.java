package com.pratiksha.resume_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "*")
public class ResumeController {

    // ✅ UPLOAD RESUME (TEMPORARY)
    @PostMapping("/upload")
    public Map<String, Object> uploadResume() {

        Map<String, Object> response = new HashMap<>();
        response.put("score", 85);
        response.put("suggestion", List.of("Improve formatting", "Add more skills"));

        return response;
    }

    // ✅ TEST API
    @GetMapping("/test")
    public String test() {
        return "Resume Service Working 🚀";
    }
}