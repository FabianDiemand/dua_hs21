package ch.ffhs.dua.pva01.visualAccumulator;

import edu.princeton.cs.algs4.StdDraw;

public class VisualAccumulator {

  private double total;
  private int n;

  public VisualAccumulator(int trials, double max) {
    StdDraw.setXscale(0, trials);
    StdDraw.setYscale(0, max);
    StdDraw.setPenRadius(.005);
  }

  public void addDataValue(double val) {
    n++;
    total += val;
    StdDraw.setPenColor(StdDraw.DARK_GRAY);
    StdDraw.point(n, val);

    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.point(n, total / n);
  }
}
