package hanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class HanoiStateBuilder {
    public HanoiState createRandomHanoiState(Integer towerCount, Integer tileCount) {
        List<Stack<Integer>> towers = new ArrayList<>();
        for (Integer i = 0; i < towerCount; i++) {
            towers.add(new Stack<>());
        }
        Random random = new Random();
        while (tileCount > 0) {
            towers.get(random.nextInt(towerCount)).push(tileCount);
            --tileCount;
        }
        return new HanoiState(towers);
    }

    public HanoiState createDefaultStartState(Integer towerCount, Integer tileCount) {
        List<Stack<Integer>> towers = new ArrayList<>();
        for (Integer i = 0; i < towerCount; i++) {
            towers.add(new Stack<>());
        }
        while (tileCount > 0) {
            towers.get(0).push(tileCount);
            --tileCount;
        }
        return new HanoiState(towers);
    }

    public HanoiState createDefaultEndState(Integer towerCount, Integer tileCount) {
        List<Stack<Integer>> towers = new ArrayList<>();
        for (Integer i = 0; i < towerCount; i++) {
            towers.add(new Stack<>());
        }
        while (tileCount > 0) {
            towers.get(towers.size() - 1).push(tileCount);
            --tileCount;
        }
        return new HanoiState(towers);
    }
}
