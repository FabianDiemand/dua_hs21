package ch.ffhs.dua.pva01.permutations;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class PermutationsHeaps {
  public static void main(String[] args) {
    permutations(5);
  }

  public static void permutations(int n) {
    int[] array = createArray(n);

    createPermutations(array, array.length, array.length);
  }

  public static void createPermutations(int[] array, int length, int number) {
    if (length == 1) {
      printPermutation(array);
    }

    for (int i = 1; i <= length; i++) {
      createPermutations(array, length - 1, number);

      if (length % 2 == 1) {
        int temp = array[0];
        array[0] = array[length - 1];
        array[length - 1] = temp;
      } else {
        int temp = array[i];
        array[i] = array[length - 1];
        array[length - 1] = temp;
      }
    }
  }

  private static void printPermutation(int[] array) {
    StdOut.println(Arrays.toString(array));
  }

  private static int[] createArray(int n){
    int[] array = new int[n];
    int j = 0;
    for (int i = 1; i <= n; i++, j++) {
      array[j] = i;
    }

    return array;
  }
}
