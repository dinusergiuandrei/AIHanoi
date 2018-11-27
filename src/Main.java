import hanoi.*;
import problem.Problem;
import problem.Strategy;
import strategies.OptimizedRandomHanoiStrategy;
import strategies.RandomHanoiStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Params params = new Params(5, 5, 200);

    public static void main(String args[]) {
        int instanceCount = 1;
        List<Problem> instances = createInstances(instanceCount);
        List<Strategy> strategies = computeStrategies();

        strategies.forEach(
                strategy ->
                        instances.forEach(
                                problem -> problem.solve(new RandomHanoiStrategy())));
    }

    private static List<Problem> createInstances(int instanceCount){
        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < instanceCount; i++) {
            problems.add(createRandomProblem(params));
        }
        return problems;
    }

    private static HanoiProblem createRandomProblem(Params params) {
        HanoiStateBuilder stateBuilder = new HanoiStateBuilder();
        HanoiState startState = stateBuilder.createRandomHanoiState(params.getMaxTowerCount(), params.getMaxTileCount());
        HanoiState endState = stateBuilder.createRandomHanoiState(params.getMaxTowerCount(), params.getMaxTileCount());
        HanoiProblem problem = new HanoiProblem(startState, endState);
        problem.setResetCounter(params.getResetCounter());
        return problem;
    }

    private static HanoiProblem createProblem(Params params){
        HanoiStateBuilder stateBuilder = new HanoiStateBuilder();
        HanoiState startState = stateBuilder.createRandomHanoiState(params.getMaxTowerCount(), params.getMaxTileCount());
        HanoiState endState = stateBuilder.createRandomHanoiState(params.getMaxTowerCount(), params.getMaxTileCount());
        HanoiProblem problem = new HanoiProblem(startState, endState);
        problem.setResetCounter(params.getResetCounter());
        return problem;
    }

    private static List<Strategy> computeStrategies(){
        List<Strategy> strategies = new ArrayList<>();
        strategies.add(new RandomHanoiStrategy());
        strategies.add(new OptimizedRandomHanoiStrategy());
        return strategies;
    }

    static class Params {
        private int maxTowerCount;
        private int maxTileCount;
        private int resetCounter;

        int getMaxTowerCount() {
            return maxTowerCount;
        }

        int getMaxTileCount() {
            return maxTileCount;
        }

        int getResetCounter() {
            return resetCounter;
        }

        Params(int towerCount, int tileCount, int resetCounter) {
            this.maxTowerCount = towerCount;
            this.maxTileCount = tileCount;
            this.resetCounter = resetCounter;
        }

    }
}
