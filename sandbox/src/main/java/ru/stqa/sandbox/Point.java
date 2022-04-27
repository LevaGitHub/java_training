package ru.stqa.sandbox;

public class Point {

    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point otherPoint) {
        return Math.sqrt( Math.pow((this.x - otherPoint.x), 2) + Math.pow((this.y - otherPoint.y), 2));
    }
}
