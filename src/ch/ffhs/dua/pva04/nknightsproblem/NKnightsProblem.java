package ch.ffhs.dua.pva04.nknightsproblem;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Arrays;

/**
 * Class solves the NKnightsProblem: Finds a tour with Knight-Moves on a chessboard. The tour must
 * not be closed. If a tour is found, the board and moves are printed to the console.
 */
public class NKnightsProblem {
  // The size (length and width) of the board
  private final int boardSize;
  // Counter for the number of moves made
  private int movesCount;
  // 2D-Array representation of a chessboard
  private final int[][] board;

  public int counter;

  // All the move deltas of a knight on a chessboard
  private final int[][] knightsDeltas =
      new int[][] {
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, -1},
        {-2, 1},
        {-1, -2}
      };

  /**
   * Constructor for the NKnightsProblem. Sets up the board, its size and the number of moves made.
   *
   * @param boardSize how long and wide the board will be
   */
  public NKnightsProblem(int boardSize) {
    // Set variables
    this.boardSize = boardSize;
    board = new int[boardSize][boardSize];
    movesCount = 0;

    // Fill the board array with -1 to allow more intuitive tracking of the steps
    for (int[] row : board) {
      Arrays.fill(row, -1);
    }
  }

  /**
   * Client to get a solution for the knights problem. Expects to get three arguments that are the
   * width/ length of the board and two arguments for the starting position
   *
   * @param args three arguments that are the width/ length of the board and two arguments for the
   *     starting position
   */
  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException(
          "Please provide an integer for the length/ width (l > 0) of the board. Provide also two numbers (n >= 0, n < length/width) for the starting position (x, y).");
    }
    if (!checkInputs(args)) {
      throw new IllegalArgumentException(
          "Boardsize must be greater than zero, starting position parameters must be greater than or equal to zero and smaller than the boardsize.");
    }

    int boardSize = Integer.parseInt(args[0]);
    int row = Integer.parseInt(args[1]);
    int col = Integer.parseInt(args[2]);

    NKnightsProblem knightsProblem = new NKnightsProblem(boardSize);
    Stopwatch s = new Stopwatch();
    knightsProblem.solveKnightsProblem(row, col);
    System.out.println(s.elapsedTime());
    System.out.println(knightsProblem.counter);
  }

  // Solve the problem and create output according to the outcome.
  private void solveKnightsProblem(int row, int col) {
    if (findNextMove(row, col)) {
      // If a tour was found
      StdOut.printf(
          "\nFound the following solution for board of size %sx%s and starting position (%s, %s)\n",
          boardSize, boardSize, row, col);
      printBoard();
    } else {
      // If no tour was found
      StdOut.printf(
          "\nNo solution for board of size %sx%s and starting position (%s, %s)\n",
          boardSize, boardSize, row, col);
    }
  }

  // Recursive method that uses backtracking to find a knights-tour on a chessboard.
  private boolean findNextMove(int row, int col) {
    // Use the number of moves made to index the move
    board[row][col] = movesCount;

    // Go through all the move deltas
    for (int[] delta : knightsDeltas) {
      // Determine the new position parameters
      int newRow = row + delta[0];
      int newCol = col + delta[1];

      // Check if a knight can be placed at the new position (on board and unvisited field)
      if (knightFits(newRow, newCol)) {
        // Increment the counter variable
        movesCount += 1;
        // Recursive call to find the following move
        boolean tourContinues = findNextMove(newRow, newCol);

        if (tourContinues) {
          // If the path continues, leave the loop
          break;
        } else {
          counter++;
          // If the path cannot continue with the current delta
          // mark the next position as unvisited ...
          board[newRow][newCol] = -1;
          // ... and decrement the moves counter
          movesCount -= 1;
        }
      }
    }

    // If movesCount is number of fields on the board, a tour has been found.
    // Else we went through all deltas and were not able to find a tour, hence there is none.
    return movesCount == boardSize * boardSize - 1;
  }

  // Check if a knight can be placed at the calculated position.
  private boolean knightFits(int newRow, int newCol) {
    return isOnBoard(newRow, newCol) && isUnvisited(newRow, newCol);
  }

  // Check if a field is on the board
  private boolean isOnBoard(int newRow, int newCol) {
    // Row and Col must be on the board (>= 0, < boardSize)
    boolean validRow = newRow >= 0 && newRow < boardSize;
    boolean validCol = newCol >= 0 && newCol < boardSize;
    return validRow && validCol;
  }

  // Check if a field is unvisited (not -1)
  private boolean isUnvisited(int newRow, int newCol) {
    return board[newRow][newCol] == -1;
  }

  // Print the board in a formatted manner
  private void printBoard() {
    for (int[] row : board) {
      for (int col : row) {
        // Print the number of each field
        StdOut.printf("%4s ", col);
      }
      StdOut.println();
    }

    StdOut.println();
  }

  // Check if the user inputs are valid.
  private static boolean checkInputs(String[] args) {
    int boardsize = Integer.parseInt(args[0]);
    int row = Integer.parseInt(args[1]);
    int col = Integer.parseInt(args[2]);

    // Board size must be bigger than 0
    boolean boardsizeValid = boardsize > 0;

    // Row and Col must be on the board
    boolean rowValid = row >= 0 && row < boardsize;
    boolean colValid = col >= 0 && col < boardsize;

    return boardsizeValid && rowValid && colValid;
  }
}
