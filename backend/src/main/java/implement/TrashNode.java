package implement;

import java.util.ArrayList;

public class TrashNode {

    private final long id;
    private final ArrayList<Double> coordinate;
    private int weight;

    public TrashNode(long id, ArrayList<Double> coordinate, int weight) {
        this.id = id;
        this.coordinate = coordinate;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Double> getCoordinate() {
        return coordinate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }
}
