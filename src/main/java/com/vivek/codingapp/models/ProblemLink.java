package com.vivek.codingapp.models;
public class ProblemLink {
    private String url;
    private String status; // Solved, Attempted, Unsolved
    private long timeTaken; // Time taken to solve in minutes

    public ProblemLink(String url, String status) {
        this.url = url;
        this.status = status;
        this.timeTaken = 0;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }
}
