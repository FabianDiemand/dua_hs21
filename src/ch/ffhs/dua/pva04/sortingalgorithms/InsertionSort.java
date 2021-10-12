package ch.ffhs.dua.pva04.sortingalgorithms;

import static ch.ffhs.dua.pva04.sortingalgorithms.SortingUtils.*;

import edu.princeton.cs.algs4.Insertion;


public class InsertionSort {

  private InsertionSort(){}

  public static <T extends Comparable<T>> void sort(T[] a){
    for(int i = 1; i<a.length; i++){
      for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
        exch(a, j, j-1);
      }
    }
  }
}
