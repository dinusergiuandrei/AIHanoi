import charts.Entry;
import charts.bar.BarChart;
import charts.bar.BarChartEntry;
import charts.bar.BarChartParams;
import hanoi.*;
import problem.Problem;
import problem.Strategy;
import strategies.*;
import utils.NumbersExpert;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static Params params = new Params(6, 5, 200);
    private static String resultPath = "results";

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        computeAndSaveResults();
        loadResult();
    }

    public static void loadResult()throws IOException, ClassNotFoundException {
        Result result = Result.load(resultPath);
        System.out.println("\nAverage moves needed\n");
        result.strategyToAvgMoves.forEach((s, c) -> System.out.println(s + " : " + c));
        System.out.println("\nAverage time needed (milliseconds)\n");
        result.strategyToAvgTime.forEach((s, c) -> System.out.println(s + " : " + c));

        List<Entry> avgMoveCountSource = new ArrayList<>();
        result.strategyToAvgMoves.forEach(
                (s, c) -> avgMoveCountSource.add(new BarChartEntry(c,"average move count", s))
        );

        List<Entry> avgTimeSource = new ArrayList<>();
        result.strategyToAvgTime.forEach(
                (s, c) -> avgTimeSource.add(new BarChartEntry(c,"average time", s))
        );

        List<Entry> avgResult = new ArrayList<>();
        result.strategyToAvgResult.forEach(
                (s, c) -> avgResult.add(new BarChartEntry(c,"average result", s))
        );

        BarChart moveChart = new BarChart();
        moveChart.createChart(avgMoveCountSource, new BarChartParams("Avg moves", "alg", "moves"));
        moveChart.display();

        BarChart timeChart = new BarChart();
        timeChart.createChart(avgTimeSource, new BarChartParams("Avg time", "strategy", "millis"));
        timeChart.display();

//        BarChart solutionFoundChart = new BarChart();
//        solutionFoundChart.createChart(avgResult, new BarChartParams("Solution found", "strategy", "%"));
//        solutionFoundChart.display();
    }

    public static void computeAndSaveResults() throws IOException {
        int instanceCount = 30;
        List<Problem> instances = createInstances(instanceCount);
        List<Strategy> strategies = computeStrategies();

        NumbersExpert<Integer> intExpert = new NumbersExpert<>();
        NumbersExpert<Long> longExpert = new NumbersExpert<>();

        Map<String, Double> strategyToAvgMoves = new LinkedHashMap<>();
        Map<String, Double> strategyToAvgTime = new LinkedHashMap<>();
        Map<String, Double> strategyToAvgResult = new LinkedHashMap<>();

        strategies.forEach(
                strategy -> {
                    String name = strategy.getName();
                    List<Integer> moveCountList = new ArrayList<>();
                    List<Long> timeList = new ArrayList<>();
                    List<Integer> solutionFound = new ArrayList<>();
                    instances.forEach(
                            problem -> {
                                try {
                                    HanoiProblem problemCopy = ((HanoiProblem) problem).getDeepCopy();
                                    long start = System.currentTimeMillis();
                                    problemCopy.solve(new RandomHanoiStrategy());

                                    int moveCount = problemCopy.getPastStates().size() + problemCopy.getMoveCount();
                                    long time = System.currentTimeMillis() - start;

                                    moveCountList.add(moveCount);
                                    timeList.add(time);

                                    if(problemCopy.isSolved())
                                        solutionFound.add(1);
                                    else solutionFound.add(0);
                                } catch (IOException | ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            });
                    Double avgMoves = intExpert.getAverage(moveCountList);
                    Double avgTime = longExpert.getAverage(timeList);
                    Double avgResult = intExpert.getAverage(solutionFound);

                    strategyToAvgMoves.put(name, avgMoves);
                    strategyToAvgTime.put(name, avgTime);
                    strategyToAvgResult.put(name, avgResult);
                });
        Result result = new Result(strategyToAvgMoves, strategyToAvgTime, strategyToAvgResult);
        result.save(resultPath);
    }

    static class Result implements Serializable {
        public Map<String, Double> strategyToAvgMoves;
        public Map<String, Double> strategyToAvgTime;
        public Map<String, Double> strategyToAvgResult;

        public Result(Map<String, Double> strategyToAvgMoves, Map<String, Double> strategyToAvgTime, Map<String, Double> strategyToAvgResult) {
            this.strategyToAvgMoves = strategyToAvgMoves;
            this.strategyToAvgTime = strategyToAvgTime;
            this.strategyToAvgResult = strategyToAvgResult;
        }

        public void save(String path) throws IOException {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        }

        public static Result load(String path) throws IOException, ClassNotFoundException {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis);
            Result result = (Result) in.readObject();
            fis.close();
            return result;
        }
    }

    private static List<Problem> createInstances(int instanceCount) {
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

//    private static HanoiProblem createProblem(Params params){
//        HanoiStateBuilder stateBuilder = new HanoiStateBuilder();
//        HanoiState startState = stateBuilder.createRandomHanoiState(params.getMaxTowerCount(), params.getMaxTileCount());
//        HanoiState endState = stateBuilder.createRandomHanoiState(params.getMaxTowerCount(), params.getMaxTileCount());
//        HanoiProblem problem = new HanoiProblem(startState, endState);
//        problem.setResetCounter(params.getResetCounter());
//        return problem;
//    }

    private static List<Strategy> computeStrategies() {
        List<Strategy> strategies = new ArrayList<>();
        strategies.add(new RandomHanoiStrategy());
        strategies.add(new OptimizedRandomHanoiStrategy());
        strategies.add(new BfsStrategy());
        strategies.add(new DfsStrategy());
        strategies.add(new HillClimbingStrategy());
        strategies.add(new AStarStrategy());
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

        Params(int maxTowerCount, int maxTileCount, int maxResetCounter) {
            this.maxTowerCount = maxTowerCount;
            this.maxTileCount = maxTileCount;
            this.resetCounter = maxResetCounter;
        }

    }
}
