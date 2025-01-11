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

    public static void main(String[] args) {
        ProblemRepository repository = new ProblemRepository();
        try {
            repository.loadProblemsFromFile("exported_problems.txt");
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
                label.setText("Opening: " + problem.getUrl());
                Desktop.getDesktop().browse(URI.create(problem.getUrl()));
            } catch (Exception ex) {
                label.setText("Failed to open a problem.");
            }
        });

        solvedButton.addActionListener(new ActionListener() {
            private Instant startTime = Instant.now();

            @Override
            public void actionPerformed(ActionEvent e) {
                String currentProblem = label.getText().replace("Opening: ", "");
                for (ProblemLink problem : repository.getProblems()) {
                    if (problem.getUrl().equals(currentProblem)) {
                        Instant endTime = Instant.now();
                        long timeTaken = Duration.between(startTime, endTime).toMinutes();
                        problem.setStatus("Solved");
                        problem.setTimeTaken(timeTaken);
                        label.setText("Marked as Solved! Time taken: " + timeTaken + " minutes");
                        sessionSolvedCount++;
                        break;
                    }
                }
            }
        });

        exportButton.addActionListener(e -> {
            try {
                repository.exportProblemsToFile("exported_problems.txt");
                label.setText("Problems exported successfully! You solved " + sessionSolvedCount + " problems this session.");
            } catch (IOException ex) {
                // Failed to export problems
                label.setText("Congrats you solved all  problems.");
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
