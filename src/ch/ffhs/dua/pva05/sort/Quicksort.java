package ch.ffhs.dua.pva05.sort;

import java.util.Arrays;

public final class Quicksort {

  /**
   * Schwellwert, bei welcher Array-Grösse in der Rekursion InsertionSort statt Quicksort aufgerufen
   * werden sollte.
   */
  private static final int THRESHOLD = 4; // TODO: Finden Sie hier einen sinnvollen Wert.

  private Quicksort() {}

  public static void main(String[] args) {
    int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
    sort(array);

    System.out.println(Arrays.toString(array));
  }

  /**
   * Sortiert ein Array mit Quicksort.
   *
   * @param array das zu sortierende Array
   */
  public static void sort(int[] array) {
    /* TODO */
    sort(array, 0, array.length-1);
  }

  /**
   * Sortiert ein Teilstück eines Arrays mit Quicksort.
   *
   * @param array das zu sortierende Array
   * @param startIndex Index des ersten Elementes des Teils, das sortiert werden muss
   * @param endIndex Index des letzen Elementes des Teils, das sortiert werden muss
   */
  public static void sort(int[] array, int startIndex, int endIndex) {
    /* TODO */
    if(endIndex <= startIndex) return;

    int pivotIndex = findPivot(startIndex, endIndex);
    int j = partition(array, startIndex, endIndex, pivotIndex);

    sort(array, startIndex, j-1);
    sort(array, j+1, endIndex);
  }

  /**
   * Modifizierter Quicksort. Wenn die Grösse des zu sortierenden Arrays in der Rekursion einen
   * Schwellwert unterschreitet, wird InsertionSort statt Quicksort aufgerufen.
   *
   * @param array das zu sortierende Array
   */
  public static void sortPlus(int[] array) {

    /* TODO */
  }

  /**
   * Modifizierter Quicksort zum Sortieren eines Teilstücks eines Arrays. Wenn die Grösse des zu
   * sortierenden Arrays in der Rekursion einen Schwellwert unterschreitet, wird InsertionSort statt
   * Quicksort aufgerufen.
   *
   * @param array das zu sortierende Array
   * @param startIndex Index des ersten Elementes des zu sortierenden Teilstücks
   * @param endIndex Index des letzten Elementes des zu sortierenden Teilstücks
   */
  public static void sortPlus(int[] array, int startIndex, int endIndex) {

    /* TODO */
  }

  /**
   * Hilfsmethode für Quicksort. Ein Teilstück eines Arrays wird geteilt, so dass alle Elemente, die
   * kleiner als ein gewisses Pivot-Element sind, links stehen, und alle Elemente, die grösser als
   * das Pivot-Element sind, rechts stehen.
   *
   * @param array das umzuordnende Array
   * @param startIndex Index des ersten Elements des Teilstücks, das geteilt werden muss
   * @param endIndex Index des letztes Elements des Teilstücks, das geteilt werden muss
   * @param pivotIndex Index des Pivot-Elements
   * @return Index des Pivot-Elements nach der Partitionierung
   */
  public static int partition(int[] array, int startIndex, int endIndex, int pivotIndex) {
    /* TODO */
    int i = startIndex-1;
    //int j = endIndex+1;
    int pivotElement = array[pivotIndex];

    for(int j = startIndex; j < endIndex; j++){
      if(array[j] <= pivotElement){
        i++;

        swap(array, i, j);
      }
    }

    swap(array, i, endIndex);

    return i+1;
  }

  /** Hilfsmethode zum Vertauschen zweier Array-Elemente */
  private static void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  /**
   * Hilfsmethode zum Finden eines Pivot-Elements für Quicksort. Zu einem Array und den zwei Indizes
   * startIndex und endIndex wird der Index eines möglichen Pivot-Elements zurückgegeben.
   */
  private static int findPivot(int startIndex, int endIndex) {
    int median;
    int n = endIndex - startIndex;

    if(n%2 == 1){
      median = ((n+1)/2)-1;
    } else{
      median = (n / 2) - 1;
    }

    return median;
  }
}
