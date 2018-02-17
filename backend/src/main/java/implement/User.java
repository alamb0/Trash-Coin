package src.main.implement;

import java.util.ArrayList;

public class User {

    private final long id;
    private ArrayList<Integer> coordinate;
    private int points;

    public User(long id, ArrayList<Integer> coordinate, int weight) {
        this.id = id;
        this.coordinate = coordinate;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Integer> getCoordiante() {
        return coordinate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
}
