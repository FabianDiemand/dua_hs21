package ch.ffhs.dua.pva05.sort;

public final class IntroSort {

  private IntroSort() {}

  /**
   * Sortiert ein Array der Länge n mit IntroSort. Das Array wird mit Quicksort sortiert, wenn aber
   * die Rekursionstiefe mehr als 2 * log(n) wird, wird auf HeapSort ungestellt.
   *
   * @param array das zu sortierende Array
   */
  public static void sort(int[] array) {
    int l = array.length;
    sort(array, 0, l - 1, 2 * log2(l));
  }

  /**
   * Sortiert ein Teilstück eines Arrays mit IntroSort.
   *
   * @param array das zu sortierende Array
   * @param startIndex Index des ersten Elementes des zu sortierenden Stücks
   * @param endIndex Index des letzten Elementes des zu sortierenden Stücks
   * @param maxDepth die maximale Tiefe, ab der auf HeapSort umgestellt wird
   */
  public static void sort(int[] array, int startIndex, int endIndex, int maxDepth) {

    /* TODO */
  }

  /**
   * Bestimmt den (abgerundeten) Zweierlogarithmus ohne Float-Arithmetik.
   *
   * @param n der Wert, von dem der Logarithmus berechnet werden soll
   * @return der Zweierlogarithmus von n
   */
  public static int log2(int n) {
    int log = 0;
    while (n > 1) {
      n >>>= 1;
      log++;
    }

    return log;
  }
}
