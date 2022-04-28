package ru.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointCalculationTest {

    @Test
    public void testDistance1() {
        Point p1 = new Point(4, 6);
        Point p2 = new Point(-4, 0);
        Assert.assertEquals(p1.distance(p2), 10.0);

    }
    @Test
    public void testDistance2() {
        Point p1 = new Point(4, 6);
        Point p2 = new Point(4, -6);
        Assert.assertEquals(p1.distance(p2), 12.0);
    }

    @Test
    public void testDistance3() {
        Point p1 = new Point(-4, -6);
        Point p2 = new Point(3, 0);
        Assert.assertEquals(p1.distance(p2), 9.219544457292887);
    }
}
