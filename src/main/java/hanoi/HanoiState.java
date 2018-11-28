package hanoi;

import problem.State;

import java.io.*;
import java.util.List;
import java.util.Stack;

public class HanoiState implements State, Serializable {

    private List<Stack<Integer>> towers;

    public HanoiState(List<Stack<Integer>> towers) {
        this.towers = towers;
    }

    public List<Stack<Integer>> getTowers() {
        return towers;
    }

    public HanoiState getCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        bos.close();
        byte[] byteData = bos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
        return (HanoiState) new ObjectInputStream(bais).readObject();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("( ");
        for (Stack tile : this.towers) {
            builder.append(tile).append("; ");
        }
        builder.append(")");
        return builder.toString();

    }
}
