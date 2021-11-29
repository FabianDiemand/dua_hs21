package ch.ffhs.dua.pva04;

public class KnightsTourProblem {
  private final int[] possibleXMoves = new int[] {2, 2, -2, -2, 1, 1, -1, -1};
  private final int[] possibleYMoves = new int[] {1, -1, 1, -1, 2, -2, 2, -2};

  /**
   * entry point to the application
   *
   * @param args 1 argument which represents the width of the chessboard (how many fields)
   */
  public static void main(String[] args) {
    if (args.length == 1) {
      // read out the argument
      int n = Integer.parseInt(args[0]);
      if (n > 0) {
        KnightsTourProblem knightsTourProblem = new KnightsTourProblem();
        // find one solution
        knightsTourProblem.findOneSolutionKnightsProblem(n);
      } else {
        System.out.println("Please provide a width for the chessboard greater than 0");
      }
    } else {
      System.out.println(
          "Please provide the width N of the chessboard: java KnightsTourProblem <N>");
    }
  }

  /**
   * finds exactly one solution (and not more, even if there exist more) for the knights tour
   * problem.
   *
   * @param n the width of the chessboard (how many fields)
   */
  public void findOneSolutionKnightsProblem(int n) {
    // initialize data containers for storing the results
    int[][] chessboard = new int[n][n];

    // initialize the chessboard with all -1, because no field has been visited yet
    initializeChessboard(chessboard);

    // find a solution starting from 0 0
    if (findSolutionRecursive(0, 0, 1, chessboard)) {
      System.out.println("Springertour für ein " + n + "*" + n + "-Feld gefunden");
      // print the found solution
      printChessboard(chessboard);
    } else {
      System.out.println("Keine Springertour für ein " + n + "*" + n + "-Feld gefunden");
    }
  }

  /**
   * creates and initializes the array which models the chessboard. Sets all values to -1, because
   * no field was visited yet.
   */
  private void initializeChessboard(int[][] chessboard) {
    int n = chessboard.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        chessboard[i][j] = -1;
      }
    }
  }

  /**
   * tries to find a knight's path through the chessboard. If the current field is a dead end,
   * delete the draw and return false
   *
   * @param oldX the x-coordinate, where the knight is before a movement
   * @param oldY the y-coordinate, where the knight is before a movement
   * @param drawCounter the quantity of draws executed so far
   * @param chessboard the data-container which stores the path until now
   * @return true, if a knight's tour is found (=> if we executed n*n draws)
   */
  private boolean findSolutionRecursive(int oldX, int oldY, int drawCounter, int[][] chessboard) {
    if (drawCounter == chessboard.length * chessboard.length) {
      chessboard[oldY][oldX] = drawCounter;
      return true;
    }

    for (int i = 0; i < possibleXMoves.length; i++) {
      int newX = oldX + possibleXMoves[i];
      int newY = oldY + possibleYMoves[i];
      if (fitsOntoChessboard(chessboard, newX, newY)) {
        chessboard[oldY][oldX] = drawCounter;
        if (findSolutionRecursive(newX, newY, drawCounter + 1, chessboard)) {
          return true;
        } else {
          chessboard[oldY][oldX] = -1;
        }
      }
    }
    return false;
  }

  /**
   * checks if a given position is on the chessboard or at least one coordinate over the board and
   * if the field is already visited
   *
   * @param chessboard the board to check
   * @param newX the new x-coordinate to check
   * @param newY the new y-coordinate to check
   * @return true, if no error was found
   */
  private boolean fitsOntoChessboard(int[][] chessboard, int newX, int newY) {
    return newX < chessboard.length
        && newX >= 0
        && newY < chessboard.length
        && newY >= 0
        && chessboard[newY][newX] == -1;
  }

  /** prints the chessboard with the path the knight took on the java console. */
  private void printChessboard(int[][] chessboard) {
    for (int[] row : chessboard) {
      for (int j = 0; j < chessboard.length; j++) {
        System.out.printf("%4s", row[j]);
      }
      System.out.println();
    }
  }
}

