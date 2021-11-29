package ch.ffhs.dua.pva05.sort;

public final class HeapSort {

  private HeapSort() {}

  /**
   * Sortiert ein Array mit Heapsort.
   *
   * @param array das zu sortierende Array
   */
  public static void sort(int[] array) {
    sort(array, 0, array.length - 1);
  }

  /**
   * Sortiert ein Teilstück eines Arrays mit Heapsort.
   *
   * @param array das zu sortierende Array
   * @param startIndex Index des ersten Elementes des zu sortierenden Teils
   * @param endIndex Index des letzten Elementes des zu sortierenden Teils
   */
  public static void sort(int[] array, int startIndex, int endIndex) {

    /* TODO */
  }

  /**
   * Erzeugt aus einem angegebenen Teilstück einen Heap.
   *
   * @param array das zu sortierende Array
   * @param startIndex Index des ersten Elementes, aus dem ein Heap erzeugt werden sollte. Das ist
   *     auch der Index der Wurzel des Heaps. Die Kinder der Wurzel liegen dann an den Positionen
   *     startIndex + 1 und startIndex + 2.
   * @param endIndex Index des letzten Elementes des Stücks, aus dem ein Heap erzeugt werden sollte.
   */
  public static void makeHeap(int[] array, int startIndex, int endIndex) {

    /* TODO */
  }

  /**
   * Entfernt das Wurzelelement eines Heaps, baut den Heap um, so dass er nach dem Entfernen wieder
   * ein Heap ist (mit einem Element weniger), und setzt das ehemalige Wurzelelement an die vormals
   * letzte Stelle im Heap (die nun nicht mehr zum Heap gehört).
   *
   * @param array ein Array, das als Teilstück einen Heap enthält
   * @param startIndex Index der Wurzel des Heaps
   * @param endIndex Index des letzten Heap-Elements
   */
  public static void removeHeapRoot(int[] array, int startIndex, int endIndex) {

    /* TODO */
  }

  /**
   * Hilfsmethode für Heapsort: Aus einem Teilstück eines Arrays mit den Grenzen startIndex und
   * endIndex sollte ein Heap erzeugt werden. Für Indizes grösser als index sei die Heap-Eigenschaft
   * bereits erfüllt. Die Methode ordnet das Stück zwischen index und endIndex so um, dass die
   * Heap-Eigenschaft für alle Elemente erfüllt ist.
   */
  private static void sink(int[] array, int startIndex, int endIndex, int index) {

    /* TODO */
  }

  /** Berechnet den Index des linken Kindelementes in einem Heap. */
  private static int leftChild(int parentIndex, int offset) {

    /* TODO */
    return 0;
  }

  /** Berechnet den Index des rechten Kindelementes in einem Heap. */
  private static int rightChild(int parentIndex, int offset) {

    /* TODO */
    return 0;
  }
}
