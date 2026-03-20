package com.pratiksha.resume_service.service;

import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    // 🔥 MAIN LOGIC METHOD
    public int analyzeResume(String resumeText, String jobDescription) {

        // ✅ lowercase for matching
        resumeText = resumeText.toLowerCase();
        jobDescription = jobDescription.toLowerCase();

        // ✅ split words
        String[] keywords = jobDescription.split(" ");

        int matchCount = 0;

        for (String word : keywords) {

            if (resumeText.contains(word)) {
                matchCount++;
            }
        }

        // ✅ score calculation
        int score = (matchCount * 100) / keywords.length;

        return score;
    }
}