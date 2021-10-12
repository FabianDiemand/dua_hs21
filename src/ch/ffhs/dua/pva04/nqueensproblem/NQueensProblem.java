package ch.ffhs.dua.pva04.nqueensproblem;

public final class NQueensProblem {
  private static final int N = 8;

  private static final boolean PRINT_ALL_SOLUTIONS = true;

  private final int[] colArray = new int[N];

  public static void main(String[] args) {
    NQueensProblem nQueensProblem = new NQueensProblem();
    nQueensProblem.addQueen(0);
  }

  private void addQueen(int row) {
    for (int col = 0; col < N; col++) {

      /* Continue with next column if queen cannot be placed onto current column. */
      if (!queenFits(row, col)) {
        continue;
      }

      /* Actually place queen onto current column. */
      colArray[row] = col;

      /* Recursively continue with next row, or print solution if current row is the last row. */
      if (row < N - 1) {
        addQueen(row + 1);
      } else {
        printColArray();
        if (!PRINT_ALL_SOLUTIONS) {
          System.exit(0);
        }
      }
    }
  }

  private boolean queenFits(int newRow, int newCol) {
    for (int oldRow = 0; oldRow < newRow; oldRow++) {
      int oldCol = colArray[oldRow];
      boolean queensInSameCol = (oldCol == newCol);
      boolean queensInSameMajorDiagonal = ((oldRow - oldCol) == (newRow - newCol));
      boolean queensInSameMinorDiagonal = ((oldRow + oldCol) == (newRow + newCol));
      if (queensInSameCol || queensInSameMajorDiagonal || queensInSameMinorDiagonal) {
        return false;
      }
    }
    return true;
  }

  private void printColArray() {
    System.out.println();
    for (int row = 0; row < N; row++) {
      int occupiedCol = colArray[row];
      for (int colIndex = 0; colIndex < occupiedCol; colIndex++) {
        System.out.print("· ");
      }
      System.out.print("X ");
      for (int colIndex = occupiedCol + 1; colIndex < N; colIndex++) {
        System.out.print("· ");
      }
      System.out.println();
    }
  }
}
