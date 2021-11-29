package ch.ffhs.dua.pva08.hashcodes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DateHashTest {
  final int day = 5;
  final int month = 7;
  final int year = 2002;
  final int hour = 22;
  final int minute = 47;
  final int second = 26;

  final DateHash date = new DateHash(day, month, year, hour, minute, second);

  @Test
  void getDay() {
    assertEquals(day, date.getDay());
  }

  @Test
  void getMonth() {
    assertEquals(month, date.getMonth());
  }

  @Test
  void getYear() {
    assertEquals(year, date.getYear());
  }

  @Test
  void getHour() {
    assertEquals(hour, date.getHour());
  }

  @Test
  void getMinute() {
    assertEquals(minute, date.getMinute());
  }

  @Test
  void getSecond() {
    assertEquals(second, date.getSecond());
  }

  @Test
  void testHashCode() {
    int dayHashConstant = 31;
    int monthHashConstant = 29;
    int hourHashConstant = 23;
    int minuteHashConstant = 19;
    int secondHashConstant = 17;

    int dayHash = ((Integer) day).hashCode();
    int monthHash = ((Integer) month).hashCode();
    int yearHash = ((Integer) year).hashCode();
    int hourHash = ((Integer) hour).hashCode();
    int minuteHash = ((Integer) minute).hashCode();
    int secondHash = ((Integer) second).hashCode();

    int expectedHash = dayHash * dayHashConstant + monthHash * monthHashConstant + hourHash * hourHashConstant + minuteHash * minuteHashConstant + secondHash * secondHashConstant + yearHash;
    assertEquals(expectedHash, date.hashCode());
  }

  @Test
  void testEquals_positive() {
    DateHash identicalDate = new DateHash(day, month, year, hour, minute, second);

    assertEquals(identicalDate, date);
  }

  @Test
  void testEquals_negative() {
    DateHash differentDate = new DateHash(day - 1, month - 1, year - 1, hour - 1, minute - 1, second - 1);

    assertNotEquals(differentDate, date);
  }
}