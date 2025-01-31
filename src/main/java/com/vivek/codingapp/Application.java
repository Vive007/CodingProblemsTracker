package com.vivek.codingapp;

import com.vivek.codingapp.models.ProblemLink;
import com.vivek.codingapp.repository.ProblemRepository;
import com.vivek.codingapp.selectors.RandomProblemSelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;

public class Application {
    private static int sessionSolvedCount = 0;
    private static Instant startTime;
    private static boolean isTimerRunning = false;
    private static String currentProblemUrl = "";

    public static void main(String[] args) {
        ProblemRepository repository = new ProblemRepository();
        try {
            repository.loadProblemsFromFile("exportedLeetcode_problems.txt");
        } catch (IOException e) {
            System.err.println("Failed to load problems from file.");
        }

        RandomProblemSelector selector = new RandomProblemSelector(repository);

        JFrame frame = new JFrame("Coding Problem Selector");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Click 'Random' to select a problem");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton randomButton = new JButton("Random");
        JButton solvedButton = new JButton("Mark as Solved");
        JButton exportButton = new JButton("Export");

        randomButton.addActionListener(e -> {
            try {
                ProblemLink problem = selector.selectRandomProblem();
                currentProblemUrl = problem.getUrl(); // Store the current problem URL
                label.setText("Opening: " + currentProblemUrl);
                Desktop.getDesktop().browse(URI.create(currentProblemUrl));
                // Reset and start the timer when a new problem is selected
                startTime = Instant.now();
                isTimerRunning = true;
                label.setText("Problem selected: " + currentProblemUrl + ". Timer started!");
            } catch (Exception ex) {
                label.setText("Failed to open a problem.");
            }
        });

        solvedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isTimerRunning) {
                    label.setText("Timer is not running. Select a new problem first.");
                    return;
                }

                if (currentProblemUrl.isEmpty()) {
                    label.setText("No problem selected. Click 'Random' first.");
                    return;
                }

                for (ProblemLink problem : repository.getProblems()) {
                    if (problem.getUrl().equals(currentProblemUrl)) {
                        Instant endTime = Instant.now();
                        Duration duration = Duration.between(startTime, endTime);
                        long minutes = duration.toMinutes();
                        long seconds = duration.getSeconds() % 60;
                        String timeTaken = String.format("%d:%02d", minutes, seconds);
                        problem.setStatus("Solved");
                        problem.setTimeTaken(timeTaken);
                        label.setText("Marked as Solved! Time taken: " + timeTaken+" Minutes:Seconds");
                        sessionSolvedCount++;
                        isTimerRunning = false;
                        currentProblemUrl = "";
                        break;
                    }
                }
            }
        });

        exportButton.addActionListener(e -> {
            try {
                repository.exportProblemsToFile("exportedLeetcode_problems.txt");
                label.setText("Problems exported successfully! You solved " + sessionSolvedCount + " problems this session.");
            } catch (IOException ex) {
                // Failed to export problems
                label.setText("Congrats you solved all problems.");
            }
        });

        JPanel panel = new JPanel();
        panel.add(randomButton);
        panel.add(solvedButton);
        panel.add(exportButton);

        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}