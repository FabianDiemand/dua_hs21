package ch.ffhs.dua.pva05.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class SortTest {

  private ArrayList<int[]> arrays;

  @BeforeEach
  public void init() {
    arrays = new ArrayList<>();
    arrays.add(new int[] {});
    arrays.add(new int[] {1});
    arrays.add(new int[] {2, 1});
    arrays.add(new int[] {1, 1, 2, 2, 3});
    arrays.add(new int[] {1, 1, 2, 2, 1});
    arrays.add(new int[] {2, 1});
    arrays.add(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    arrays.add(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    arrays.add(new int[] {10, 3, 5, 3, 6, 3, 2, 8, 7, 2, 2});
    arrays.add(new int[10]);
  }

  private void testSort(Consumer<int[]> sorter) {
    for (int[] array : arrays) {
      int[] arrayCopy = array.clone();
      Arrays.sort(arrayCopy);
      sorter.accept(array);
      Assertions.assertEquals(toList(arrayCopy), toList(array));
    }
  }

  private List<Integer> toList(int[] array) {
    return IntStream.of(array).boxed().collect(Collectors.toList());
  }

  @Test
  void testInsertionSort() {
    testSort(InsertionSort::sort);
  }

  @Test
  void testQuicksort() {
    testSort(Quicksort::sort);
  }

  @Test
  void testLog2() {
    Assertions.assertEquals(0, IntroSort.log2(1));
    Assertions.assertEquals(1, IntroSort.log2(2));
    Assertions.assertEquals(1, IntroSort.log2(3));
    Assertions.assertEquals(2, IntroSort.log2(4));
    Assertions.assertEquals(2, IntroSort.log2(5));
    Assertions.assertEquals(2, IntroSort.log2(7));
    Assertions.assertEquals(3, IntroSort.log2(8));
    Assertions.assertEquals(3, IntroSort.log2(9));
    Assertions.assertEquals(0, IntroSort.log2(0));
  }

  @Test
  void testMakeHeap() {
    for (int[] array : arrays) {
      int[] arrayCopy = array.clone();
      HeapSort.makeHeap(array, 0, array.length - 1);
      Assertions.assertTrue(isHeap(array));
      Arrays.sort(array);
      Arrays.sort(arrayCopy);
      Assertions.assertArrayEquals(arrayCopy, array);
    }

    int[] array;
    array = new int[] {1, 2, 3, 4, 5, 6, 7};
    HeapSort.makeHeap(array, 0, array.length - 1);
    Assertions.assertTrue(isHeap(array));

    array = new int[] {1, 2, 3, 4, 5, 6, 7};
    HeapSort.makeHeap(array, 1, array.length - 2);
    Assertions.assertTrue(isHeap(array, 1, array.length - 2));
    Assertions.assertEquals(1, array[0]);
    Assertions.assertEquals(7, array[6]);

    array = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    HeapSort.makeHeap(array, 0, array.length - 1);
    Assertions.assertTrue(isHeap(array));

    array = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    HeapSort.makeHeap(array, 1, array.length - 2);
    Assertions.assertTrue(isHeap(array, 1, array.length - 2));
    Assertions.assertEquals(1, array[0]);
    Assertions.assertEquals(8, array[7]);
  }

  private boolean isHeap(int[] array) {
    return isHeap(array, 0, array.length - 1);
  }

  private boolean isHeap(int[] array, int start, int end) {
    int offset = start - 1;
    int length = end - start + 1;
    for (int index = length; index > 1; index--) {
      if (array[offset + index] > array[offset + index / 2]) return false;
    }
    return true;
  }

  @Test
  void testRemoveHeapRoot() {
    for (int[] array : arrays) {
      if (array.length == 0) continue;
      int[] arrayCopy = array.clone();
      Arrays.sort(arrayCopy);
      HeapSort.makeHeap(array, 0, array.length - 1);
      Assertions.assertTrue(isHeap(array));
      HeapSort.removeHeapRoot(array, 0, array.length - 1);
      Assertions.assertTrue(isHeap(array, 0, array.length - 2));
      Arrays.sort(array);
      Assertions.assertEquals(arrayCopy[array.length - 1], array[array.length - 1]);
      Assertions.assertArrayEquals(array, arrayCopy);
    }

    int[] array;
    int[] arrayCopy;
    array = new int[] {8, 7, 6, 5, 4, 4, 3, 3, 2, 1};
    arrayCopy = array.clone();
    Assertions.assertTrue(isHeap(array));
    HeapSort.removeHeapRoot(array, 0, array.length - 1);
    Assertions.assertTrue(isHeap(array, 0, array.length - 2));
    Assertions.assertEquals(arrayCopy[0], array[array.length - 1]);
    Arrays.sort(array);
    Arrays.sort(arrayCopy);
    Assertions.assertArrayEquals(array, arrayCopy);
  }

  @Test
  void testHeapSort() {
    testSort(HeapSort::sort);
  }

  @Test
  void testIntroSort() {
    testSort(IntroSort::sort);
  }
}
