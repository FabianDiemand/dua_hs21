package ch.ffhs.dua.pva02.doublylinkedlist;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;

/**
 * Class to model a doubly linked list.
 *
 * @param <E> specify the type of data that can be stored within the list
 */
public final class DoublyLinkedList<E> extends BaseList<E> {
  private final Node head = new Node();
  private final Node tail = new Node();
  private int size = 0;

  /**
   * Testclient to demonstrate the basic functionallity of the doubly linked list by writing "FFHS"
   * to the console.
   *
   * @param args command line arguments provided
   */
  public static void main(String[] args) {
    DoublyLinkedList<String> list = new DoublyLinkedList<>();

    list.add("F");
    list.add("H");
    list.add("H");
    list.add("S");
    list.add("S");
    StdOut.printf("Size before correction: %s\n", list.size());

    list.remove(3);
    list.remove("H");
    StdOut.printf("Size after removing wrong characters: %s\n", list.size());

    list.add(1, "F");
    StdOut.printf("Size after adding missing 'F': %s\n\n", list.size());

    list.printList();

    for (String content : list) {
      StdOut.print(content);
      StdOut.println("\n");
    }
  }

  /**
   * Check if the list is empty.
   *
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Get the number of elements currently stored in the list
   *
   * @return number of elements in the list
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Add an element to the back of the list.
   *
   * @param element the element to add
   * @return true if the operation was successful, false otherwise
   */
  @Override
  public boolean add(E element) {

    Node newNode = new Node(element);

    if (isEmpty()) {
      insertInEmptyList(newNode);

    } else {
      insertAtLastIndex(newNode);
    }

    return true;
  }

  /**
   * Insert an element at a specified index into the list.
   *
   * @param index the index to insert the element at
   * @param content the element to insert
   * @throws NoSuchElementException if the index provided is negative or bigger than the number of
   *     elements in the list
   */
  @Override
  public void add(int index, E content) throws NoSuchElementException {
    if (index < 0 || index > size)
      throw new NoSuchElementException(
          String.format("Insertion index must be positive and smaller than %s.", size));

    Node newNode = new Node(content);

    if (index == 0) {
      // the index is at the beginning of the list
      insertAtFirstIndex(newNode);
    } else if (index == size) {
      // the index is at the end of the list
      insertAtLastIndex(newNode);
    } else {
      // the index is within the list
      Node nextNeighbour = getNode(index);
      Node prevNeighbour = nextNeighbour.prev;

      // create pointers to the next neighbour
      newNode.next = nextNeighbour;
      nextNeighbour.prev = newNode;

      // create pointers to the previous neighbour
      newNode.prev = prevNeighbour;
      prevNeighbour.next = newNode;

      // increase the size of the list
      size += 1;
    }
  }

  /**
   * Check if a certain element is contained within the list.
   *
   * @param element the element whose existens is to be checked
   * @return true, if the list contains the element; false otherwise
   */
  @Override
  public boolean contains(Object element) {

    for (Node node = head.next; node.next != null; node = node.next) {
      // for each node, check if its content matches the element to lookup; return true if so
      if (node.content == element) {
        return true;
      }
    }

    // if the loop runs out the element was not found, therefore return false
    return false;
  }

  /**
   * Get the content of the entry at the specified index in the list.
   *
   * @param index the index of the entry
   * @return the content of the entry at the passed index
   * @throws NoSuchElementException if the list is empty or if the index provided is negative or
   *     bigger than the number of elements in the list
   */
  @Override
  public E get(int index) throws NoSuchElementException {
    if (isEmpty()) throw new NoSuchElementException("The list is empty. No elements to retrieve.");
    if (index < 0 || index > size - 1)
      throw new NoSuchElementException(
          String.format("Lookup index must be positive and smaller than %s.", size));

    // get the node at the specified index
    Node targetNode = getNode(index);

    // return the nodes content
    return targetNode.content;
  }

  /**
   * Set the content of an already existing node specified by its index. Get the old content
   * returned.
   *
   * @param index the index whose entry is to be modified
   * @param content the content to store in the specified node
   * @return the content that was stored in the node before the change
   * @throws NoSuchElementException if the list is empty or the index provided is negative or bigger
   *     than the number of elements in the list
   */
  @Override
  public E set(int index, E content) throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException("The list is currently empty. No nodes to be set.");
    if (index < 0 || index > size - 1)
      throw new NoSuchElementException(
          String.format("Setting index must be positive and smaller than %s.", size));

    // get the targetted node and store its old content
    Node targetNode = getNode(index);
    E oldContent = targetNode.content;

    // update the content of the node
    targetNode.content = content;

    // return the old content
    return oldContent;
  }

  /**
   * Remove a node specified by the element it contains.
   *
   * @param element the element whose containing node must be removed
   * @return true if the element was found and the node was removed, false otherwise
   */
  @Override
  public boolean remove(Object element) throws IllegalArgumentException {
    if (isEmpty())
      throw new IllegalStateException("The list is currently empty. No objects to remove.");
    if (element == null) throw new IllegalArgumentException("Can not look up null.");

    for (Node node = head.next; node.next != null; node = node.next) {
      // for each node, check if its content matches the element to lookup
      // remove the node and return true if so
      if (node.content.equals(element)) {
        removeNode(node);
        return true;
      }
    }

    // return false if the list does not contain the lookup element
    return false;
  }

  /**
   * Remove a node specified by its index in the list. Retrieve the content of the deleted node.
   *
   * @param index the index whose node to remove from the list
   * @return the content of the removed node
   * @throws NoSuchElementException if the list is empty or the index provided is negative or bigger
   *     than the number of elements in the list
   */
  @Override
  public E remove(int index) throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException("The list is currently empty. No objects to remove.");
    if (index < 0 || index > size - 1)
      throw new NoSuchElementException(
          String.format("Setting index must be positive and smaller than %s.", size));

    // get the node at the specified index and store its content
    Node targetNode = getNode(index);
    E content = targetNode.content;

    // remove the node
    removeNode(targetNode);

    // return the content
    return content;
  }

  /** Clear the doubly linked list, by nullifying all relations between the nodes */
  @Override
  public void clear() {
    // return if the list is already empty
    if (isEmpty()) return;

    for (Node node = head.next; node.next != null; node = node.next) {
      // get the previous node
      Node prevNode = node.prev;

      // nullify all the relations to the previous node
      // relations to the next node must be kept to allow the iteration
      prevNode.next = null;
      node.prev = null;

      // set listsize to 0
      size = 0;
    }
  }

  /**
   * Get an iterator that allows traversing the list.
   *
   * @return Iterator to traverse the list
   */
  @Override
  public @NotNull Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  /** Print the list as <Index>: <Content> - pairs */
  public void printList() {
    int index = 0;

    for (Node node = head.next; node.next != null; node = node.next, index++) {
      StdOut.printf("Index %s: %s\n", index, node.content);
    }

    StdOut.println("\n");
  }

  // Remove the node that is passed as an argument.
  private void removeNode(Node remNode) {
    Node prevNeighbour = remNode.prev;
    Node nextNeighbour = remNode.next;

    // Setup relations to skip the node to remove
    prevNeighbour.next = nextNeighbour;
    remNode.prev = null;

    nextNeighbour.prev = prevNeighbour;
    remNode.next = null;

    // Decrease the size by 1
    size -= 1;
  }

  // Insert the first element into the empty list.
  private void insertInEmptyList(Node newNode) {
    // Setup relation to the lists head
    head.next = newNode;
    newNode.prev = head;

    // Setup relation to the lists tail
    newNode.next = tail;
    tail.prev = newNode;

    size += 1;
  }

  // Insert an element at the beginning of the list
  private void insertAtFirstIndex(Node newNode) {
    Node tempNode = head.next;

    // Setup relation to the lists head
    head.next = newNode;
    newNode.prev = head;

    // Setup relation to the next node in the list
    newNode.next = tempNode;
    tempNode.prev = newNode;

    size += 1;
  }

  // Insert an element at the very end of the list
  private void insertAtLastIndex(Node newNode) {
    Node tempNode = tail.prev;

    // Setup relation to the lists tail
    tail.prev = newNode;
    newNode.next = tail;

    // Setup relation to the previous node in the list
    newNode.prev = tempNode;
    tempNode.next = newNode;

    size += 1;
  }

  // Retrieve a node from a specified index within the list
  private Node getNode(int index) {

    int half = size / 2;
    Node lookupNode;
    Node node;

    // performance: if the index is in the first half, start traversing from the head
    if (index <= half) {
      node = head.next;
      lookupNode = head.next;

      // travers the nodes until the targetted node is reached
      for (int i = 0; i <= index; i++, node = node.next) {
        lookupNode = node;
      }

      // performance: if the index is in the first half, start traversing from the tail
    } else {
      node = tail.prev;
      lookupNode = tail.prev;

      // travers the nodes until the targetted node is reached
      for (int i = size - 1; i >= index; i--, node = node.prev) {
        lookupNode = node;
      }
    }

    // return the targetted node
    return lookupNode;
  }

  // Inner class to model Nodes that each consist of a reference to the next and to the previous
  // node, as well as container for the element to be stored within the node
  private class Node {
    Node prev;
    Node next;
    E content;

    /** Default constructor used for head and tail of the list. */
    public Node() {}

    /**
     * Create a new node with content.
     *
     * @param content the content of the node
     * @throws IllegalArgumentException if no content was provided
     */
    public Node(E content) throws IllegalArgumentException {
      this.content = content;
    }
  }

  // Inner class to model an iterator that matches the doubly linked list functionallity and is able
  // to traverse from head to tail and check for next and previous neighbours of nodes
  private class LinkedListIterator implements Iterator<E> {
    Node currNode;

    /** Set the starting point as the head of the list */
    public LinkedListIterator() {
      currNode = head;
    }

    /**
     * Check if a node has a next element ignoring the tail of the list.
     *
     * @return true if there is a next element; false otherwise
     */
    @Override
    public boolean hasNext() {
      // check for next.next to ensure that the tail does not count as a node of the list
      return currNode.next != tail;
    }

    /**
     * Retrieve the content of the next node in the list.
     *
     * @return the content of the next node
     * @throws NoSuchElementException if trying to access a node that is out of bounds
     */
    @Override
    public E next() throws NoSuchElementException {
      currNode = currNode.next;
      if (currNode == tail)
        throw new NoSuchElementException(
            String.format(
                "The node you want to access is out of bounds for list of length %s", size));
      return currNode.content;
    }

    /** Remove the current node from the list by eliminating all references to it. */
    @Override
    public void remove() {
      Node previousNode = currNode.prev;
      Node nextNode = currNode.next;

      // make the references skip the current node
      previousNode.next = nextNode;
      nextNode.prev = previousNode;

      // decrease the size of the list
      size -= 1;
    }
  }
}
