package strategies;

import hanoi.HanoiState;
import org.jetbrains.annotations.NotNull;
import problem.Problem;
import problem.State;
import problem.Strategy;
import problem.Transition;

import java.util.*;

public class AStarStrategy implements Strategy {
    @Override
    public Transition proposeTransition(Problem problem) {
        HanoiState startState = (HanoiState) problem.getStartState();
        HanoiState endState = (HanoiState) problem.getEndState();

        Map<State, Integer> stateToCost = new LinkedHashMap<>();
        Map<State, Transition> stateToTransition = new LinkedHashMap<>();
        Map<Transition, State> transitionToState = new LinkedHashMap<>();
        Queue<State> stateQueue = new ArrayDeque<>();
        Set<State> pastStates = new HashSet<>();


        stateQueue.add(problem.getCurrentState());
        while (!problem.isSolved()) {
            HanoiState currentState = (HanoiState) stateQueue.poll();
            problem.setCurrentState(currentState);
            Double currentScore = evaluateState(currentState, startState, endState);
            List<Transition> candidateTransitions = problem.getValidTransitions();
            candidateTransitions.forEach(
                    transition -> {
                        HanoiState fState = (HanoiState) transition.getEndState();
                        Double score = evaluateState(fState, startState, endState);
                        if (!pastStates.contains(fState) && score > currentScore) {
                            stateToTransition.put(fState, transition);
                            stateQueue.add(fState);

                            problem.setCurrentState(fState);
                            problem.updateIsSolved();
                            transition.undo();
                            problem.setCurrentState(transition.getStartState());
                        }
                    }
            );
        }

        return null;
    }

    private Double evaluateState(HanoiState currentState, HanoiState startState, HanoiState endState) {
        return evaluateStateScore(currentState, startState, endState);
    }

    @NotNull
    static Double evaluateStateScore(HanoiState currentState, HanoiState startState, HanoiState endState) {
        List<Integer> startData = new ArrayList<>();
        List<Integer> endData = new ArrayList<>();
        List<Integer> currentData = new ArrayList<>();

        currentState.getTowers().forEach(tower -> currentData.add(tower.size()));
        startState.getTowers().forEach(tower -> startData.add(tower.size()));
        endState.getTowers().forEach(tower -> endData.add(tower.size()));

        Integer s = 0;
        double dStart = 0.0;
        for (int i = 0; i < startData.size(); i++) {
            Double d = (double) (startData.get(i) - currentData.get(i));
            dStart += d * d;
        }
        dStart = Math.sqrt(dStart);

        double dEnd = 0.0;
        for (int i = 0; i < endData.size(); i++) {
            Double d = (double) (endData.get(i) - currentData.get(i));
            dEnd += d * d;
        }
        dEnd = Math.sqrt(dEnd);

        Double result = dStart - dEnd;
        return result;
    }

    @Override
    public String getName() {
        return "A*";
    }
}
