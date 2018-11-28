package problem;

import java.io.IOException;

public interface Transition {
    State getStartState();
    State getEndState();
    Boolean isValidTransition();
    void undo();
}
