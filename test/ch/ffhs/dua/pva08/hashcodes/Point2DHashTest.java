package ch.ffhs.dua.pva08.hashcodes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Point2DHashTest {
  final int HASH_CONSTANT = 37;
  static Point2DHash pointSet;
  static Point2DHash pointConstr;
  static double xCoord;
  static double yCoord;

  @BeforeAll
  static void setup(){
    xCoord = -2.1;
    yCoord = 7.2;

    pointSet = new Point2DHash();
    pointConstr = new Point2DHash(xCoord, yCoord);
  }

  @Test
  void testGetter() {
    assertEquals(xCoord, pointConstr.getX());
    assertEquals(yCoord, pointConstr.getY());

    pointSet.setPosition(3, 2);

    assertEquals(3, pointSet.getX());
    assertEquals(2, pointSet.getY());

  }

  @Test
  void testHashCode() {
    int expectedHash = HASH_CONSTANT * ((Double) xCoord).hashCode() + ((Double) yCoord).hashCode();

    assertEquals(expectedHash, pointConstr.hashCode());
  }

  @Test
  void testEquals_positive() {
    Point2DHash identicalPointConstr = new Point2DHash(xCoord, yCoord);

    assertEquals(pointConstr, identicalPointConstr);
  }

  @Test
  void testEquals_negative() {
    Point2DHash differentPointConstr = new Point2DHash(xCoord, -yCoord);

    assertNotEquals(pointConstr, differentPointConstr);
  }
}