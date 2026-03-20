package com.pratiksha.resume_service.controller;

import com.pratiksha.resume_service.entity.Resume;
import com.pratiksha.resume_service.repository.ResumeRepository;
import com.pratiksha.resume_service.service.ResumeService; // ✅ NEW

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/resumes")
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ResumeService resumeService; // ✅ NEW

    // ✅ UPLOAD API
    @PostMapping("/upload")
    public String uploadResume(
        @RequestParam("file") MultipartFile file,
        @RequestParam("userId") Long userId,
        @RequestParam("name") String name,
        @RequestParam("jobDescription") String jobDescription
    ) {
        try {

            System.out.println("🔥 Upload API Hit");

            if (name == null || name.trim().isEmpty()) {
                name = "User " + userId;
            }

            String uploadDir = "uploads/";
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            Files.write(Paths.get(filePath), file.getBytes());

            // ✅ TEXT EXTRACTION
            String text = "";

            try {
                PDDocument document = PDDocument.load(file.getInputStream());
                PDFTextStripper stripper = new PDFTextStripper();
                text = stripper.getText(document);
                document.close();
            } catch (Exception e) {
                text = new String(file.getBytes());
            }

            // 🔥 JD BASED AI SCORING
            int finalScore = resumeService.analyzeResume(text, jobDescription);

            System.out.println("🔥 AI Score: " + finalScore);

            // 🔍 SKILLS (optional enhancement)
            List<String> keywords = List.of(
                    "Java", "Spring Boot", "React", "Python",
                    "AWS", "Docker", "MySQL", "REST API", "Microservices"
            );

            List<String> detectedSkills = new ArrayList<>();
            for (String skill : keywords) {
                if (text.toLowerCase().contains(skill.toLowerCase())) {
                    detectedSkills.add(skill);
                }
            }

            // ❗ Missing skills based on JD
            List<String> missingSkills = new ArrayList<>();
            String[] jdWords = jobDescription.toLowerCase().split(" ");

            for (String word : jdWords) {
                if (!text.toLowerCase().contains(word)) {
                    missingSkills.add(word);
                }
            }

            // 💡 Suggestion
            String suggestion = missingSkills.size() > 0
                    ? "Improve by adding: " + missingSkills
                    : "Great match with job description!";

            // ✅ SAVE
            Resume resume = new Resume();

            resume.setName(name);
            resume.setUserId(userId);
            resume.setSkills(detectedSkills.toString());
            resume.setExperience(1); // basic for now
            resume.setScore(finalScore);
            resume.setFilePath(filePath);
            resume.setMissingSkills(missingSkills.toString());
            resume.setSuggestion(suggestion);

            resumeRepository.save(resume);

            return "Resume Uploaded & Analyzed Successfully 🚀";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error Uploading Resume";
        }
    }

    // ✅ GET SCORE
    @GetMapping("/score")
    public Map<String, Integer> getScore() {
        List<Resume> resumes = resumeRepository.findAll();

        if (resumes.isEmpty()) return Map.of("score", 0);

        Resume latest = resumes.get(resumes.size() - 1);
        return Map.of("score", latest.getScore());
    }

    // ✅ GET SUGGESTION
    @GetMapping("/improve")
    public Map<String, String> improveResume() {
        List<Resume> resumes = resumeRepository.findAll();

        if (resumes.isEmpty()) {
            return Map.of("suggestion", "Upload resume first");
        }

        Resume latest = resumes.get(resumes.size() - 1);
        return Map.of("suggestion", latest.getSuggestion());
    }

    // ✅ RANKING
    @GetMapping("/ranking")
    public List<Resume> getRanking() {
        return resumeRepository.findAllByOrderByScoreDesc();
    }

    // ✅ DELETE
    @DeleteMapping("/deleteAll")
    public String deleteAllResumes() {
        resumeRepository.deleteAll();
        return "All resumes deleted successfully";
    }
    
    @GetMapping("/download-report")
    public void downloadReport(HttpServletResponse response) {
        try {
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=report.csv");

            List<Resume> resumes = resumeRepository.findAll();

            PrintWriter writer = response.getWriter();

            // CSV Header
            writer.println("Name,UserId,Score,Skills,Suggestion");

            // Data
            for (Resume r : resumes) {
                writer.println(
                    r.getName() + "," +
                    r.getUserId() + "," +
                    r.getScore() + "," +
                    r.getSkills() + "," +
                    r.getSuggestion()
                );
            }

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
