package ch.ffhs.dua.pva05.sort;


import static ch.ffhs.dua.pva05.sort.SortingUtils.*;

public final class InsertionSort {

  private InsertionSort() {}

  /**
   * Sortiert ein Array mit Einfügesortieren.
   *
   * @param array das zu sortierende Array
   */
  public static void sort(int[] array) {
    /* TODO */
    for(int i = 1; i<array.length; i++){
      for(int j = i; j > 0 && less(array[j], array[j-1]); j--){
        exch(array, j, j-1);
      }
    }
  }

  /**
   * Sortiert einen durch zwei Grenzen angebenen Teil eines Arrays mit Einfügesortieren.
   * Array-Elemente ausserhalb der Grenzen werden nicht verschoben.
   *
   * @param array das zu sortierende Array
   * @param startIndex Index des ersten Elementes des Teils, das sortiert werden muss
   * @param endIndex Index des letzten Elementes des Teils, das sortiert werden muss
   */
  public static void sort(int[] array, int startIndex, int endIndex) {
    /* TODO */
    for(int i = startIndex + 1; i<endIndex + 1; i++){
      for(int j = i; j > 0 && less(array[j], array[j-1]); j--){
        exch(array, j, j-1);
      }
    }
  }
}
