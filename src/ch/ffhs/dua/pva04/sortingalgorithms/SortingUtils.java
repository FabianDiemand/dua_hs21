package ch.ffhs.dua.pva04.sortingalgorithms;

import edu.princeton.cs.algs4.StdOut;

public class SortingUtils {

  private SortingUtils(){}

  public static <T extends Comparable<T>> boolean less(T v, T w){
    return v.compareTo(w) < 0;
  }

  public static <T extends Comparable<T>> void exch(T[] a, int i, int j){
    T t = a[i];
    a[i] = a[j];
    a[j] = t;
  }

  public static <T extends Comparable<T>> void show(T[] a){
    for(T entry: a){
      StdOut.print(entry + " ");
    }
    StdOut.println();
  }

  public static <T extends Comparable<T>> boolean isSorted(T[] a){
    for(int i = 1; i < a.length; i++){
      if(less(a[i], a[i-1])) return false;
    }

    return true;
  }

//  public static <T extends Comparable<T>> void merge(T[] a, int lo, int mid, int hi){
//    int i = lo;
//    int j = mid + 1;
//    T[] aux= new T[hi-lo];
//
//    for(int k = lo; k <= hi; k++){
//      aux[k] = a[k];
//    }
//  }
}
