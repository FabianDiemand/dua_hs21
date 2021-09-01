package ch.ffhs.dua.pva01.permutations;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Class that creates permutations of a passed integer.
 * Based on an iterative permutative insertion.
 *
 * @author Fabian Diemand
 */
public class Permutations {

  /**
   * Test-Client: Calls permutations on a number, resulting in an array containing all permutations
   * of {1,2,3, ... , number}. Then first prints the permutations array and the total number of
   * permutations.
   */
  public static void main(String[] args) {
    int[][] permutations = permutations(3);

    printPermutations(permutations);
  }

  /**
   * Creates an array of permutations from {1,2,3, ..., n}.
   *
   * @param n number of elements per permutation.
   * @return array of permutations; each permutation is an array of integers.
   */
  public static int[][] permutations(int n) {
    if (n < 0)
      throw new IllegalArgumentException(
          "Number of entries must be a positive number or zero.");

    return createPermutations(n);
  }

  // Creates permutations of {1,2,3,...,n}.
  private static int[][] createPermutations(int n) {
    if(n==0) return new int[0][0];

    // Permutations array for n = 1
    int[][] permutations = new int[][] {{1}};

    for (int i = 2; i <= n; i++) {
      // Create temporary array to store the permutations of the current iteration
      int[][] tempPermutations = new int[getFact(i)][i];
      // Set the index used to start copying to the correct position in the tempPermutations array
      int index = 0;

      for (int[] permutation : permutations) {
        // Put the current number once at every index in the current permutation
        int[][] temp = permutativeInsert(permutation, i);

        // Temporarily store the resulting 2D-array
        System.arraycopy(temp, 0, tempPermutations, index, temp.length);
        // Update the index to the first empty spot in the tempPermutations array
        index += temp.length;
      }

      permutations = tempPermutations;
    }

    return permutations;
  }

  // Inserts the given number once at every index into the given array.
  private static int[][] permutativeInsert(int[] array, int number) {
    int[][] temp = new int[number][array.length + 1];
    int index = 0;

    // Iterate through the array and put the number at every index
    // before storing the resulting array into the 2D temp array
    for (int i = 0; i <= array.length; i++, index++) {
      temp[index] = putIntoArray(array, i, number);
    }

    return temp;
  }

  // Put the given number at the given index into the passed array
  private static int[] putIntoArray(int[] array, int index, int number) {
    int[] temp = new int[array.length + 1];

    // Copy the passed array from index 0 to the given index
    System.arraycopy(array, 0, temp, 0, index);

    // Put the number at the next index
    temp[index] = number;

    // Copy the rest of the passed array
    System.arraycopy(array, index, temp, index + 1, array.length - index);

    return temp;
  }

  // Recursively calculate the factorial of a number
  private static int getFact(int number) {
    if (number <= 1) {
      return 1;
    } else {
      return number * getFact(number - 1);
    }
  }

  // Print all permutation arrays and then the total number of permutations
  private static void printPermutations(int[][] permutations) {
    for (int[] permutation : permutations) {
      StdOut.println(Arrays.toString(permutation));
    }

    // Create a formatter to achieve the thousands separators "'"
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "CH"));
    DecimalFormat df = (DecimalFormat) nf;

    StdOut.printf("\nTotal permutations: %s\n", df.format(permutations.length));
  }
}
