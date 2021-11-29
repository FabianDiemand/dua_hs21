package ch.ffhs.dua.pva08.hashcodes;

public class Point2DHash{
  private double x;
  private double y;

  public Point2DHash(double x, double y){
    this.x = x;
    this.y = y;
  }

  public Point2DHash(){
    this.x = 0;
    this.y = 0;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public void setPosition(double x, double y){
    this.x = x;
    this.y = y;
  }

  public int hashCode(){
    int xHash = ((Double) x).hashCode();
    int yHash = ((Double) y).hashCode();

    return 37 * xHash + yHash;
  }

  public boolean equals(Object point){
    if(this == point) return true;
    if(point == null) return false;
    if(this.getClass() != point.getClass()) return false;

    Point2DHash that = (Point2DHash) point;
    return this.x == that.getX() && this.y == that.getY();
  }
}
