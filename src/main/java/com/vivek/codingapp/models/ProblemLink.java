package com.vivek.codingapp.models;
public class ProblemLink {
    private String url;
    private String status; // Solved, Attempted, Unsolved
    private String timeTaken; // Time taken to solve in minutes

    public ProblemLink(String url, String status,String timeTaken) {
        this.url = url;
        this.status = status;
        this.timeTaken = timeTaken;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }
}
