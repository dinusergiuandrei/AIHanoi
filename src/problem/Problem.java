package problem;

import hanoi.HanoiProblem;

import java.util.List;

public interface Problem {
    State getStartState();

    State getEndState();

    void initialize();

    default Boolean solve(Strategy strategy){
        long start = System.currentTimeMillis();
        System.out.println(getCurrentState());
        while(!isSolved()){
            Transition transition = strategy.proposeTransition(this);
            if(transition.isValidTransition()){
                State transitionEndState = transition.getEndState();
                this.setCurrentState(transitionEndState);
                System.out.println(getCurrentState());
            }
            updateIsSolved();
        }
        System.out.println("Solved in " + ((HanoiProblem) this).getPastStates().size() + ((HanoiProblem) this).getMoveCount() + " moves (" + (System.currentTimeMillis()-start) + " milliseconds)");
        return isSolved();
    }

    State getCurrentState();

    void setCurrentState(State state);

    Boolean isSolved();

    void setIsSolved(Boolean isSolved);

    void updateIsSolved();

    List<Transition> getValidTransitions();
}
