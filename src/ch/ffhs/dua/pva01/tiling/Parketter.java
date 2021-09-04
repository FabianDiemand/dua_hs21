package ch.ffhs.dua.pva01.tiling;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.HashSet;
import java.util.Set;

public class Parketter {

  public static void main(String[] args) {
    final Stopwatch stopwatch = new Stopwatch();
    StdOut.println(Parketter.layFloor(2, 4));
    StdOut.println(stopwatch.elapsedTime());
  }

  /**
   * Cover an area of 'width x height' tiles with a floor of bricks. Print all the possible options
   * of floor layouts.
   *
   * @param width the width of the area to cover
   * @param height the height of the area to cover
   * @return the number of options for floor layouts
   * @throws IllegalArgumentException if the area is invalid (negative, zero or uneven)
   */
  public static int layFloor(int width, int height) throws IllegalArgumentException {
    // Check arguments for validity.
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Enter positive numbers for the side lengths.");
    }
    if ((width * height) % 2 == 1) {
      throw new IllegalArgumentException(
          String.format("The area of '%s x %s' can't be totally covered.", width, height));
    }

    // Create the area object and check for possible brick layouts
    Area emptyArea = new Area(width, height);
    final Set<Area> coveredAreas = layFloor(emptyArea);

    // Give each tile a number depending on which brick the tile belongs to
    for (Area coveredArea : coveredAreas) {
      int[][] tilesArea = new int[width][height];
      int i = 0;
      for (Brick brick : coveredArea.getBricks()) {
        tilesArea[brick.getFirstTile().getXPos()][brick.getFirstTile().getYPos()] = i;
        tilesArea[brick.getSecondTile().getXPos()][brick.getSecondTile().getYPos()] = i;
        i++;
      }

      for (int[] row : tilesArea) {
        for (int tile : row) {
          StdOut.print(tile);
        }
        StdOut.println();
      }
      StdOut.println();
    }
    return coveredAreas.size();
  }

  // Look for possible positioning of bricks
  // Abort search, if there aren't any more options
  private static Set<Area> layFloor(Area area) {
    // Find possible brick positions
    Set<Brick> possibleBricks = area.findUncoveredBricks();

    // Abort condition
    if (possibleBricks.isEmpty()) {
      if (area.isFullyCovered()) {
        return Set.of(area);
      } else {
        return new HashSet<>();
      }
    }

    // Create a new floor; lay one brick at each iteration and go throught the options recursively.
    Set<Area> possibleAreas = new HashSet<>();
    for (Brick brick : possibleBricks) {
      Area newArea = area.copy();
      newArea.setBrick(brick);
      possibleAreas.addAll(layFloor(newArea));
    }

    return possibleAreas;
  }
}
