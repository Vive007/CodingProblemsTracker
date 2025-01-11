package com.vivek.codingapp.selectors;
import com.vivek.codingapp.models.ProblemLink;
import com.vivek.codingapp.repository.ProblemRepository;
import java.util.List;
import java.util.Random;

public class RandomProblemSelector {
    private ProblemRepository repository;

    public RandomProblemSelector(ProblemRepository repository) {
        this.repository = repository;
    }

    public ProblemLink selectRandomProblem() {
        List<ProblemLink> unsolvedProblems = repository.getUnsolvedProblems();
        if (unsolvedProblems.isEmpty()) {
            throw new IllegalStateException("No unsolved problems available!");
        }
        Random random = new Random();
        int index = random.nextInt(unsolvedProblems.size());
        return unsolvedProblems.get(index);
    }
}