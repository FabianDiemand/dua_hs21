package ch.ffhs.dua.pva08.hashcodes;

public class Interval2DHash {
  private final Point2DHash minPoint;
  private final Point2DHash maxPoint;

  public Interval2DHash(Point2DHash minPoint, Point2DHash maxPoint){
    this.minPoint = minPoint;
    this.maxPoint = maxPoint;
  }

  public Point2DHash getMinPoint(){
    return minPoint;
  }

  public Point2DHash getMaxPoint(){
    return maxPoint;
  }

  public int hashCode(){
    int minPointHash = minPoint.hashCode();
    int maxPointHash = maxPoint.hashCode();

    return 37 * minPointHash + maxPointHash;
  }

  public boolean equals(Object interval2DHash){
    if(this == interval2DHash) return true;
    if(interval2DHash == null) return false;
    if(this.getClass() != interval2DHash.getClass()) return false;

    Interval2DHash that = (Interval2DHash) interval2DHash;
    return this.minPoint == that.getMinPoint() && this.maxPoint == that.getMaxPoint();
  }
}
