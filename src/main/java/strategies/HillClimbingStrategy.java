package strategies;

import hanoi.HanoiState;
import problem.Problem;
import problem.State;
import problem.Strategy;
import problem.Transition;

import java.io.IOException;
import java.util.*;

import static strategies.AStarStrategy.evaluateStateScore;

public class HillClimbingStrategy implements Strategy {
    @Override
    public Transition proposeTransition(Problem problem) {
        List<Transition> candidateTransitions = problem.getValidTransitions();
        List<State> candidateStates = new ArrayList<>();
        Map<State, Transition> stateToTransition = new LinkedHashMap<>();
        candidateTransitions.forEach(
                transition -> {
                    try {
                        State s = ((HanoiState) transition.getEndState()).getCopy();
                        candidateStates.add(s);
                        stateToTransition.put(s, transition);
                        transition.undo();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );

        HanoiState bestEndState = null;
        Double bestScore = -Double.MAX_VALUE;

        HanoiState startState = (HanoiState) problem.getStartState();
        HanoiState endState = (HanoiState) problem.getEndState();

        for (State candidateState : candidateStates) {
            HanoiState currentState = (HanoiState) candidateState;
            Double score = evaluateState(currentState, startState, endState);
            if (score > bestScore) {
                bestEndState = currentState;
                bestScore = score;
            }
        }

        return stateToTransition.get(bestEndState);
    }

    private Double evaluateState(HanoiState currentState, HanoiState startState, HanoiState endState) {
        return evaluateStateScore(currentState, startState, endState);
    }

    @Override
    public String getName() {
        return "Hill Climbing";
    }
}
