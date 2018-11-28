package strategies;

import problem.Problem;
import problem.State;
import problem.Strategy;
import problem.Transition;

import java.util.*;

public class DfsStrategy implements Strategy {
    @Override
    public Transition proposeTransition(Problem problem) {
        State startState = problem.getStartState();
        State endState = problem.getEndState();

        Map<State, Integer> stateToCost = new LinkedHashMap<>();
        Map<State, Transition> stateToTransition = new LinkedHashMap<>();
        Map<Transition, State> transitionToState = new LinkedHashMap<>();
        Set<State> pastStates = new HashSet<>();
        Stack<State> stateStack = new Stack<>();

        stateStack.push(problem.getCurrentState());
        while (!problem.isSolved()) {
            State currentState = stateStack.pop();
            problem.setCurrentState(currentState);
            pastStates.add(problem.getCurrentState());
            List<Transition> candidateTransitions = problem.getValidTransitions();
            candidateTransitions.forEach(
                    transition -> {
                        State fState = transition.getEndState();
                        if (!pastStates.contains(fState)) {
                            stateToTransition.put(fState, transition);
                            stateStack.push(fState);

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

    @Override
    public String getName() {
        return "DFS";
    }
}
