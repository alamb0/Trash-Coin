package implement;

import java.util.ArrayList;

public class User {

    private final long id;
    private double x, y;
    private int points;
    private String username;
    private String password;

    public User(
        String username,
        String password,
        double x, double y) {

        this.username = username;
        this.password = password;
        this.x = x;
        this.y = y;
        this.points = 0;
        this.id = generateID();
    }

    public long getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getX(){
        return x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getY(){
        return y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int newPoints) {
        this.points = newPoints;
    }

     public static long generateID(){
        return (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
    }
}
