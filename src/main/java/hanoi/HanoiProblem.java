package hanoi;

import problem.Problem;
import problem.State;
import problem.Transition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class HanoiProblem implements Problem {
    private HanoiState startState;
    private HanoiState endState;

    private HanoiState currentState;

    private Boolean isSolved;

    private Integer resetCounter = Integer.MAX_VALUE;

    public List<HanoiState> getPastStates() {
        return pastStates;
    }

    private List<HanoiState> pastStates = new ArrayList<>();

    private Integer moveCount = 0;

    private Integer maxMoveCount = 15000;

    public HanoiProblem(HanoiState startState, HanoiState endState) {
        this.startState = startState;
        this.endState = endState;
        initialize();
    }

    public HanoiProblem getDeepCopy() throws IOException, ClassNotFoundException {
        HanoiState newStartState = this.startState.getCopy();
        HanoiState newEndState = this.endState.getCopy();
        return new HanoiProblem(newStartState, newEndState);
    }

    public Integer getMoveCount() {
        return moveCount;
    }

    public void setResetCounter(Integer value) {
        this.resetCounter = value;
    }

    @Override
    public void initialize() {
        setCurrentState(getStartState());
        this.isSolved = false;
        try {
            pastStates.add(this.currentState.getCopy());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public State getStartState() {
        return this.startState;
    }

    @Override
    public State getEndState() {
        return this.endState;
    }

    @Override
    public State getCurrentState() {
        return currentState;
    }

    @Override
    public void setCurrentState(State state) {
        if (this.getPastStates().size() % this.resetCounter == 0) {
            this.resetPastStates();
        }
        try {
            this.currentState = (HanoiState) state;
            if (this.currentState != null) {
                try {
                    pastStates.add(this.currentState.getCopy());
                } catch (Exception e) {
                    System.out.println("I could not make a state copy.");
                }
            }
        } catch (ClassCastException e) {
            System.out.println("Can not cast State to HanoiState");
        }
    }

    @Override
    public Boolean isSolved() {
        return isSolved;
    }

    @Override
    public void setIsSolved(Boolean isSolved) {
        this.isSolved = isSolved;
    }

    @Override
    public boolean updateIsSolved() {
        if (Objects.equals(((HanoiState) this.getCurrentState()).getTowers(), ((HanoiState) getEndState()).getTowers())) {
            this.setIsSolved(true);
        }

        int moveCount = this.getPastStates().size() + this.getMoveCount();
        return moveCount >= maxMoveCount;
    }

    public void resetPastStates() {
        this.moveCount += pastStates.size();
        pastStates = new ArrayList<>();
    }

    @Override
    public List<Transition> getValidTransitions() {
        try {
            List<Transition> transitions = new ArrayList<>();
            List<Stack<Integer>> towers = ((HanoiState) this.getCurrentState()).getTowers();
            for (int i = 0; i < towers.size(); i++) {
                for (int j = 0; j < towers.size(); j++) {
                    if (i != j) {
                        if (!towers.get(i).empty()
                                && (
                                towers.get(j).empty()
                                        || towers.get(i).peek() < towers.get(j).peek()
                        )
                        )
                            transitions.add(new HanoiTransition(i, j, (HanoiState) this.getCurrentState()));

                    }
                }
            }
            return transitions;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }
}
