package ch.ffhs.dua.pva08.hashcodes;

public class DateHash {
  private final int day;
  private final int month;
  private final int year;
  private final int hour;
  private final int minute;
  private final int second;

  public DateHash(int day, int month, int year, int hour, int minute, int second){
    this.day = day;
    this.month = month;
    this.year = year;
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }

  public int hashCode(){
    int dayHash = ((Integer) day).hashCode();
    int monthHash = ((Integer) month).hashCode();
    int yearHash = ((Integer) year).hashCode();
    int hourHash = ((Integer) hour).hashCode();
    int minuteHash = ((Integer) minute).hashCode();
    int secondHash = ((Integer) second).hashCode();

    return 31 * dayHash + 29 * monthHash + 23 * hourHash + 19 * minuteHash + 17 * secondHash + yearHash;
  }

  public boolean equals(Object date){
    if(this == date) return true;
    if(date == null) return false;
    if(this.getClass() != date.getClass()) return false;

    DateHash that = (DateHash) date;

    boolean dayMatch = this.day == that.getDay();
    boolean monthMatch = this.month == that.getMonth();
    boolean yearMatch = this.year == that.getYear();
    boolean hoursMatch = this.hour == that.getHour();
    boolean minutesMatch = this.minute == that.getMinute();
    boolean secondsMatch = this.second == that.getSecond();
    return dayMatch && monthMatch && yearMatch && hoursMatch && minutesMatch && secondsMatch;
  }
}
