package ch.ffhs.dua.pva04.sortingalgorithms;

import static ch.ffhs.dua.pva04.sortingalgorithms.SortingUtils.*;

public class SelectionSort {

  private SelectionSort(){}

  public static <T extends Comparable<T>> void sort(T[] a){
    for(int i = 0; i < a.length; i++){
      int min = i;
      for(int j = i+1; j<a.length; j++){
        if(less(a[j], a[min])) min = j;
      }
      exch(a, i, min);
    }
  }
}
