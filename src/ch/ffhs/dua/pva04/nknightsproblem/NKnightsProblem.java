package ch.ffhs.dua.pva04.nknightsproblem;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class NKnightsProblem {
  private final int n;
  private final int[][] board;
  private int numOfMoves;

  // Array contains all the moves/ deltas a knight could make on a board {x, y}
  private final int[][] knightsDeltas =
      new int[][] {
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1},
        {-1, -2}
      };

  public NKnightsProblem(int boardSize) {
    n = boardSize;
    board = new int[n][n];
    numOfMoves = 0;

    for (int[] col : board) {
      Arrays.fill(col, -1);
    }
  }

  public static void main(String[] args) {
    int boardSize = 8;
    NKnightsProblem knightsProblem = new NKnightsProblem(boardSize);

    if (knightsProblem.solveKnightsProblem(0, 0)) {
      StdOut.printf("\nFound the following solution for board of size: %sx%s\n", boardSize, boardSize);
      knightsProblem.printBoard();
    } else {
      StdOut.printf("\nNo solution for board of size: %sx%s\n", boardSize, boardSize);
    }
  }

  public boolean solveKnightsProblem(int col, int row) {
    board[col][row] = numOfMoves;

    for (int[] delta : knightsDeltas) {
      int newCol = col + delta[0];
      int newRow = row + delta[1];

      if (knightFits(newCol, newRow)) {
        numOfMoves += 1;
        boolean pathContinues = solveKnightsProblem(newCol, newRow);

        if (!pathContinues) {
          board[newCol][newRow] = -1;
          numOfMoves -= 1;
        } else {
          break;
        }
      }
    }

    return numOfMoves == n * n - 1;
  }

  private boolean knightFits(int newCol, int newRow) {
    return isOnBoard(newCol, newRow) && isUnvisited(newCol, newRow);
  }

  private boolean isUnvisited(int newCol, int newRow) {
    return board[newCol][newRow] == -1;
  }

  private boolean isOnBoard(int newCol, int newRow) {
    boolean validCol = newCol >= 0 && newCol < n;
    boolean validRow = newRow >= 0 && newRow < n;
    return validCol && validRow;
  }

  private void printBoard() {
    for (int[] col : board) {
      StdOut.println(Arrays.toString(col));
    }

    StdOut.println();
  }
}
