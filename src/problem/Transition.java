package problem;

public interface Transition {
    State getStartState();
    State getEndState();
    Boolean isValidTransition();
}
