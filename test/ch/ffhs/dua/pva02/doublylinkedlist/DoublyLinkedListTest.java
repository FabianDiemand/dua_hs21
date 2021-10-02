package ch.ffhs.dua.pva02.doublylinkedlist;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;

final class DoublyLinkedListTest {

  @Test
  void testBasics() throws NoSuchElementException {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    assertEquals(0, list.size());
    assertTrue(list.add("B"));
    assertTrue(list.add("C"));
    assertTrue(list.add("E"));
    assertEquals("B", list.get(0));
    assertEquals("C", list.get(1));
    assertEquals("E", list.get(2));
    list.add(2, "D");
    assertEquals("D", list.get(2));
    assertEquals("E", list.get(3));
    list.add(0, "AA");
    assertEquals("AA", list.get(0));
    assertEquals("B", list.get(1));
    assertEquals(5, list.size());
    assertEquals("AA", list.set(0, "A"));
    assertEquals("A", list.get(0));
    assertEquals(5, list.size());
    String str = list.remove(3);
    assertEquals("D", str);
    assertEquals(4, list.size());
    assertTrue(list.contains("B"));
    assertFalse(list.contains(null));
    assertTrue(list.add(null));
    assertTrue(list.contains(null));
  }

  @Test
  void testIterator() {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();
    assertTrue(list.add("A"));
    assertTrue(list.add("B"));
    assertTrue(list.add("C"));
    Iterator<String> iterator = list.iterator();
    assertTrue(iterator.hasNext());
    assertEquals("A", iterator.next());
    assertEquals("B", iterator.next());
    assertTrue(iterator.hasNext());
    iterator.remove();
    assertTrue(iterator.hasNext());
    assertEquals("C", iterator.next());
    assertFalse(iterator.hasNext());
    assertEquals(2, list.size());
    assertEquals("A", list.get(0));
    assertEquals("C", list.get(1));
  }
}
