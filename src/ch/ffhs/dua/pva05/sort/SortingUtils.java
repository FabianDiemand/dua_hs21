package ch.ffhs.dua.pva05.sort;

public class SortingUtils {
  private SortingUtils(){}

  public static <T extends Comparable<T>> boolean less(T v, T w){
    return v.compareTo(w) < 0;
  }

  public static void exch(int[] a, int i, int j){
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
  }
}
