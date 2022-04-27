package ru.stqa.sandbox;

public class PointCalculation {
    public static void main(String[] args) {
        Point p1 = new Point(1, 0);
        Point p2 = new Point(-4, 0);
        System.out.println("Расстояние между точками:" +
                "(" + p1.x + "," + p1.y + "), " +
                "(" + p2.x + "," + p2.y + ") " +
                "= " + p1.distance(p2));
        Point p3 = new Point(6, 4);
        System.out.println("Расстояние между точками:" +
                "(" + p1.x + "," + p1.y + "), " +
                "(" + p3.x + "," + p3.y + ") " +
                "= " + p1.distance(p3));
    }

}
