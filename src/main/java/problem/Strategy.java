package problem;

public interface Strategy {
    Transition proposeTransition(Problem problem);

    String getName();
}
