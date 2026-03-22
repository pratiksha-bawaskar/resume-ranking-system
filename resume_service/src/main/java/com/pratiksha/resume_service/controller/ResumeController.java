package com.pratiksha.resume_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ResumeController {

    // ✅ UPLOAD RESUME (FIXED)
    @PostMapping("/upload")
    public Map<String, Object> uploadResume(@RequestParam("file") MultipartFile file) {

        System.out.println("Received file: " + file.getOriginalFilename());

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