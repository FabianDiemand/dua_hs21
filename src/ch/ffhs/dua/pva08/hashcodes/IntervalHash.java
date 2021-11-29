package ch.ffhs.dua.pva08.hashcodes;

public class IntervalHash {
  private final double min;
  private final double max;

  public IntervalHash(double min, double max){
    this.min = min;
    this.max = max;
  }

  public double getMin(){
    return min;
  }

  public double getMax(){
    return max;
  }

  public int hashCode(){
    int minHash = ((Double) min).hashCode();
    int maxHash = ((Double) max).hashCode();

    return 37 * minHash + maxHash;
  }

  public boolean equals(Object interval){
    if(this == interval) return true;
    if(interval == null) return false;
    if(this.getClass() != interval.getClass()) return false;

    IntervalHash that = (IntervalHash) interval;
    return this.min == that.getMin() && this.max == that.getMax();
  }
}
