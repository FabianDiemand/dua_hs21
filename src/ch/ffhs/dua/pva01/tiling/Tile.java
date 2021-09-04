package ch.ffhs.dua.pva01.tiling;

/**
 * Class models a tile (one part of a domino brick)
 */
public class Tile {
  // each tile knows its position in the area
  private final int xPos;
  private final int yPos;
  // each tile knows wether it is covered or not
  private boolean covered = false;

  /**
   * Create a tile and pass it the coordinates of its position in the area
   *
   * @param xPos x coordinate of the tiles position
   * @param yPos y coordinate of the tiles position
   */
  public Tile(int xPos, int yPos) {
    this.xPos = xPos;
    this.yPos = yPos;
  }

  /**
   * Cover a tile.
   *
   * @throws IllegalStateException if the tile is already covered
   */
  public void cover() throws IllegalStateException{
    if (covered){
      throw new IllegalStateException("Tile is covered already.");
    } else{
      covered = true;
    }
  }

  /**
   * Check if a tile is covered.
   *
   * @return true if the tile is covered, false otherwise
   */
  public boolean isUncovered() {
    return !covered;
  }

  public int getXPos() {
    return xPos;
  }

  public int getYPos() {
    return yPos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Tile tile = (Tile) o;

    if (xPos != tile.xPos) {
      return false;
    }
    return yPos == tile.yPos;
  }

  @Override
  public int hashCode() {
    int res = xPos;
    res = 31 * res + yPos;
    res = 31 * res + (covered ? 1 : 0);
    return res;
  }
}
