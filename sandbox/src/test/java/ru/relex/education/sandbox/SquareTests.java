package ru.relex.education.sandbox;

//import org.testng.annotations.Test;

//import org.junit.Assert;
import org.testng.Assert;
//import org.testng.annotations.Test;
import org.junit.Test;

public class SquareTests {

  @Test
  public void testArea(){
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 25.0);
  }
}
