package implement;

import java.util.ArrayList;

public class User {

    private final long id;
    private ArrayList<Integer> coordinate;
    private int points;

    public User(long id, ArrayList<Integer> coordinate, int points) {
        this.id = id;
        this.coordinate = coordinate;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Integer> getCoordinate() {
        return coordinate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int newPoints) {
        this.points = newPoints;
    }
}
