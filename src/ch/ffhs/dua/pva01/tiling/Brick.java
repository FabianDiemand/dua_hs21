package ch.ffhs.dua.pva01.tiling;

import java.util.Set;

/**
 * Class models a domino brick that covers two tiles of an area.
 */
public class Brick {

  private final Tile firstTile;
  private final Tile secondTile;
  private final Set<Tile> tiles;

  /**
   * Create a brick that covers two tiles.
   *
   * @param firstTile the first tile to be covered
   * @param secondTile the second tile to be covered
   */
  public Brick(Tile firstTile, Tile secondTile) {
    this.firstTile = firstTile;
    this.secondTile = secondTile;

    tiles = Set.of(firstTile, secondTile);
  }

  public Tile getFirstTile() {
    return firstTile;
  }

  public Tile getSecondTile() {
    return secondTile;
  }

  /**
   * Check if two bricks are equal.
   * Work with the tiles Set so that order does not matter.
   *
   * @param o the object to compare against
   * @return true if the brick (tiles) are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Brick brick = (Brick) o;

    return tiles.equals(brick.tiles);
  }

  @Override
  public int hashCode() {
    return tiles.hashCode();
  }
}
