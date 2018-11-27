package hanoi;

import problem.State;
import problem.Transition;

public class HanoiTransition implements Transition {
    private Integer startTower;
    private Integer endTower;
    private HanoiState startState;

    public HanoiTransition(Integer startTower, Integer endTower, HanoiState startState) {
        this.startTower = startTower;
        this.endTower = endTower;
        this.startState = startState;
    }

    public void undo(){
        startState.getTowers().get(startTower).push(startState.getTowers().get(endTower).pop());
    }

    @Override
    public State getStartState() {
        return startState;
    }

    @Override
    public State getEndState() {
        startState.getTowers().get(endTower).push(startState.getTowers().get(startTower).pop());
        return startState;
    }

    @Override
    public Boolean isValidTransition() {
        if(!isValidIndex(startTower) || !isValidIndex(endTower))
            return false;
        if(startState.getTowers().get(startTower).empty())
            return false;
        if(startState.getTowers().get(endTower).empty())
            return true;
        return startState.getTowers().get(startTower).peek() < startState.getTowers().get(endTower).peek();
    }

    private boolean isValidIndex(Integer index){
        return index >=0 && index <= startState.getTowers().size();
    }
}
