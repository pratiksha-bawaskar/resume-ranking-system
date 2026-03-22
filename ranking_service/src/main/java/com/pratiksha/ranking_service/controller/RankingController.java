package com.pratiksha.ranking_service.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/ranking")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RankingController {

    // ✅ GET RANKING (Dummy Data)
    @GetMapping
    public List<Map<String, Object>> getRanking() {

        return List.of(
                Map.of(
                        "userId", "1",
                        "score", 90,
                        "suggestion", "Improve formatting"
                ),
                Map.of(
                        "userId", "2",
                        "score", 85,
                        "suggestion", "Add more projects"
                ),
                Map.of(
                        "userId", "3",
                        "score", 78,
                        "suggestion", "Improve skills section"
                )
        );
    }

    // ✅ DOWNLOAD CSV (Dummy Data)
    @GetMapping("/download-report")
    public void downloadReport(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=report.csv");

        List<Map<String, Object>> rankingList = getRanking();

        PrintWriter writer = response.getWriter();
        writer.println("UserId,Score,Suggestion");

        for (Map<String, Object> r : rankingList) {

            writer.println(
                    r.get("userId") + "," +
                    r.get("score") + "," +
                    "\"" + r.get("suggestion") + "\""
            );
        }

        writer.flush();
        writer.close();
    }
}