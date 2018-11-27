package strategies;

import hanoi.HanoiProblem;
import hanoi.HanoiState;
import hanoi.HanoiTransition;
import problem.Problem;
import problem.Strategy;
import problem.Transition;

import java.util.*;
import java.util.stream.Collectors;

public class OptimizedRandomHanoiStrategy implements Strategy {

    @Override
    public Transition proposeTransition(Problem problem) {
        HanoiProblem hanoiProblem = (HanoiProblem) problem;
        List<Transition> candidateTransitions = problem.getValidTransitions();
        candidateTransitions
                = candidateTransitions
                .stream()
                .filter(transition -> !leadsToPastState(hanoiProblem, (HanoiTransition) transition))
                .collect(Collectors.toList());
        Random random = new Random();
        if(candidateTransitions.size() == 0){
            ((HanoiProblem) problem).resetPastStates();
            return this.proposeTransition(problem);
        }
        return candidateTransitions.get(random.nextInt(candidateTransitions.size()));
    }

    private Boolean leadsToPastState(HanoiProblem problem, HanoiTransition transition) {
        HanoiState state = (HanoiState) transition.getEndState();
        boolean result = false;
        for (HanoiState pastState : problem.getPastStates()) {
            if (Objects.equals(state.getTowers(), pastState.getTowers())) {
                result = true;
            }
        }
        transition.undo();
        return result;
    }
}
