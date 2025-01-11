package com.vivek.codingapp.repository;
import com.vivek.codingapp.models.ProblemLink;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemRepository {
    private List<ProblemLink> problems;

    public ProblemRepository() {
        problems = new ArrayList<>();
    }

    public void loadProblemsFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String url = parts[0];
            String status = parts.length > 1 ? parts[1] : "Unsolved";
            long timeTaken = parts.length > 2 ? Long.parseLong(parts[2]) : 0;
            problems.add(new ProblemLink(url, status));
        }
        reader.close();
    }

    public List<ProblemLink> getProblems() {
        return problems;
    }

    public List<ProblemLink> getUnsolvedProblems() {
        List<ProblemLink> unsolvedProblems = new ArrayList<>();
        for (ProblemLink problem : problems) {
            if (!problem.getStatus().equalsIgnoreCase("Solved")) {
                unsolvedProblems.add(problem);
            }
        }
        return unsolvedProblems;
    }

    public void exportProblemsToFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (ProblemLink problem : problems) {
            writer.write(problem.getUrl() + "," + problem.getStatus() + "," + problem.getTimeTaken());
            writer.newLine();
        }
        writer.close();
    }
}