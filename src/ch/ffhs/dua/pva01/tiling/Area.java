package ch.ffhs.dua.pva01.tiling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class models the area that has to be covered with domino bricks. Creates an area divided into
 * tiles. One tile equals half a domino brick.
 */
public class Area {

  private final int width;
  private final int height;
  private final Set<Brick> bricks;
  private final Tile[][] tiles;

  /**
   * Create an area of width x height tiles.
   *
   * @param width the width of the area
   * @param height the height of the area
   */
  public Area(int width, int height) {
    this.width = width;
    this.height = height;
    bricks = new HashSet<>();

    // fill the area with tiles who know their exact coordinates
    tiles = new Tile[width][height];
    for (int xPos = 0; xPos < width; xPos++) {
      for (int yPos = 0; yPos < height; yPos++) {
        tiles[xPos][yPos] = new Tile(xPos, yPos);
      }
    }
  }

  public Set<Brick> getBricks() {
    return bricks;
  }

  /**
   * Find all uncovered spaces for domino bricks by checking wether the neighbor
   * of a tile is covered or not.
   * Store all the possible bricks into a set to avoid duplicates.
   *
   * @return Set of bricks that cover the area
   */
  public Set<Brick> findUncoveredBricks() {
    Set<Brick> possibleBricks = new HashSet<>();
    for (Tile[] row : tiles) {
      for (Tile tile : row) {
        if (tile.isUncovered()) {
          for (Tile freeNeighbour : findUncoveredNeighbors(tile)) {
            possibleBricks.add(new Brick(tile, freeNeighbour));
          }
        }
      }
    }
    return possibleBricks;
  }


  /**
   * Cover the two tiles with a brick.
   * Mark the tiles as covered.
   *
   * @param brick the brick that covers the tiles
   */
  public void setBrick(Brick brick) {
    final Tile firstTile = brick.getFirstTile();
    final Tile secondTile = brick.getSecondTile();

    tiles[firstTile.getXPos()][firstTile.getYPos()].cover();
    tiles[secondTile.getXPos()][secondTile.getYPos()].cover();
    this.bricks.add(brick);
  }

  /**
   * Check if the area is fully covered.
   *
   * @return true if the area is covered, false otherwise
   */
  public boolean isFullyCovered() {
    return bricks.size() == height * width / 2;
  }

  /**
   * Copies the area as it is to allow multiple solutions.
   *
   * @return copy of this area object
   */
  public Area copy() {
    final Area area = new Area(width, height);

    for (Brick brick : this.bricks) {
      area.setBrick(brick);
    }
    return area;
  }

  // Check and return the uncovered neighbors of a given tile.
  private List<Tile> findUncoveredNeighbors(Tile tile) {
    List<Tile> neighbors = new ArrayList<>();
    List<Tile> uncoveredNeighbors = new ArrayList<>();

    final int x = tile.getXPos();
    final int y = tile.getYPos();

    // tiles at the border of the area have one or two neighbors less.
    // avoid right border overflow
    if (x + 1 < width) {
      neighbors.add(tiles[x + 1][y]);
    }
    // avoid left border overflow
    if (x - 1 >= 0) {
      neighbors.add(tiles[x - 1][y]);
    }
    // avoid top border overflow
    if (y + 1 < height) {
      neighbors.add(tiles[x][y + 1]);
    }
    // avoid bottom border overflow
    if (y - 1 >= 0) {
      neighbors.add(tiles[x][y - 1]);
    }

    // store neighboring tiles that are not yet covered
    for (Tile neighbor : neighbors) {
      if (neighbor.isUncovered()) uncoveredNeighbors.add(neighbor);
    }

    return uncoveredNeighbors;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Area area = (Area) o;

    if (width != area.width) {
      return false;
    }
    if (height != area.height) {
      return false;
    }
    return bricks.equals(area.bricks);
  }

  @Override
  public int hashCode() {
    int result = width;
    result = 31 * result + height;
    result = 31 * result + bricks.hashCode();
    return result;
  }
}
