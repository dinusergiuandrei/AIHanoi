package strategies;

import problem.Problem;
import problem.Strategy;
import problem.Transition;

import java.util.*;

public class RandomHanoiStrategy implements Strategy {

    @Override
    public Transition proposeTransition(Problem problem) {
        List<Transition> candidateTransitions = problem.getValidTransitions();
        Random random = new Random();
        return candidateTransitions.get(random.nextInt(candidateTransitions.size()));
    }

}
