package ch.ffhs.dua.pva08.hashcodes;

import static org.junit.jupiter.api.Assertions.*;

import edu.princeton.cs.algs4.In;
import java.awt.Point;
import org.junit.jupiter.api.Test;

class Interval2DHashTest {
  final int HASH_CONSTANT = 37;
  final Point2DHash minPoint = new Point2DHash(-1, 2);
  final Point2DHash maxPoint = new Point2DHash(20, 50);
  final Point2DHash differentMinPoint = new Point2DHash(1, -2);
  final Point2DHash differentMaxPoint = new Point2DHash(25, 45);
  final Interval2DHash interval2D = new Interval2DHash(minPoint, maxPoint);

  @Test
  void getMinPoint() {
    assertEquals(minPoint, interval2D.getMinPoint());
  }

  @Test
  void getMaxPoint() {
    assertEquals(maxPoint, interval2D.getMaxPoint());
  }

  @Test
  void testHashCode() {
    int expectedHash = HASH_CONSTANT * minPoint.hashCode() + maxPoint.hashCode();

    assertEquals(expectedHash, interval2D.hashCode());
  }

  @Test
  void testEquals_positive() {
    Interval2DHash identicalInterval2D = new Interval2DHash(minPoint, maxPoint);

    assertEquals(interval2D, identicalInterval2D);
  }

  @Test
  void testEquals_negative() {
    Interval2DHash differentInterval2D = new Interval2DHash(differentMinPoint, differentMaxPoint);

    assertNotEquals(interval2D, differentInterval2D);
  }
}