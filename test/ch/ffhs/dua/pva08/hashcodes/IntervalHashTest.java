package ch.ffhs.dua.pva08.hashcodes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntervalHashTest {
  final double min = 3;
  final double max = 10;
  final int HASH_CONSTANT = 37;
  IntervalHash interval = new IntervalHash(min, max);

  @Test
  void getMin() {
    assertEquals(min, interval.getMin());
  }

  @Test
  void getMax() {
    assertEquals(max, interval.getMax());
  }

  @Test
  void testHashCode() {
    int expectedHash = HASH_CONSTANT * ((Double) min).hashCode() + ((Double) max).hashCode();

    assertEquals(expectedHash, interval.hashCode());
  }

  @Test
  void testEquals_positive() {
    IntervalHash identicalInterval = new IntervalHash(min, max);

    assertEquals(interval, identicalInterval);
  }

  @Test
  void testEquals_negative() {
    IntervalHash differentInterval = new IntervalHash(min + 2, max);

    assertNotEquals(interval, differentInterval);
  }
}