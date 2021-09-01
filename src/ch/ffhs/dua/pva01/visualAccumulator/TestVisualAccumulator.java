package ch.ffhs.dua.pva01.visualAccumulator;

import edu.princeton.cs.algs4.*;

public class TestVisualAccumulator {
  public static void main(String[] args) {
    int pointCount = Integer.parseInt(args[0]);
    VisualAccumulator a = new VisualAccumulator(pointCount, 1.0);

    for (int t = 0; t < pointCount; t++) {
      a.addDataValue(StdRandom.uniform());
    }

    StdOut.println(a);
  }
}
