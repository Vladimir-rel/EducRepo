package ru.relex.education.sandbox;

import org.testng.Assert;
import org.junit.Test;

public class PointTests {

    @Test
    public void testline(){

      Point a = new Point(0,0);
      Point b = new Point(3,4);

      Assert.assertEquals(Point.distance(a, b), 5.0);

      a = new Point(1,1);
      b = new Point(7,9);

      Assert.assertEquals(Point.distance(a, b), 10.0);
  }
}
