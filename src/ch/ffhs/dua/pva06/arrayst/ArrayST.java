package ch.ffhs.dua.pva06.arrayst;

import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ArrayST<K extends Comparable<K>, V extends Comparable<V>> {
  private int size = 0;
  private final int capacity;
  private KeyValuePair<K, V>[] data;

  public static void main(String[] args) {
    String numOfEntriesListLength = "\n# of entries: %s, Length of list: %s";

    ArrayST<Integer, String> arrayST = new ArrayST<>(1);

    // Demo for growing list
    arrayST.put(1, "one");
    arrayST.put(2, "two");
    StdOut.printf(numOfEntriesListLength, arrayST.size(), arrayST.length());
    arrayST.put(3, "three");
    StdOut.printf(numOfEntriesListLength, arrayST.size(), arrayST.length());
    arrayST.put(4, "four");
    arrayST.put(5, "five");
    StdOut.printf(numOfEntriesListLength, arrayST.size(), arrayST.length());
    StdOut.println();

    // Positive demo for contains
    StdOut.printf("\nCheck for key 3: %s\n", arrayST.contains(3));

    // Demo get and keys
    StdOut.print("\nCurrent list: ");
    for(int key: arrayST.keys()){
      StdOut.printf("%s ", arrayST.get(key));
    }

    StdOut.printf("\n\nMost/ Last looked up pair is: [key=%s, value=%s]", arrayST.getFirstPair().getKey(), arrayST.getFirstPair().getValue());

    // Demo min, Demo max
    StdOut.printf("\n\nSmallest key in the list: %s\n", arrayST.min());
    StdOut.printf("Greatest key in the list: %s\n", arrayST.max());

    // Demo for shrinking list
    arrayST.delete(1);
    StdOut.printf(numOfEntriesListLength, arrayST.size(), arrayST.length());
    arrayST.delete(2);
    arrayST.delete(3);
    StdOut.printf(numOfEntriesListLength, arrayST.size(), arrayST.length());
    arrayST.delete(4);
    StdOut.printf(numOfEntriesListLength, arrayST.size(), arrayST.length());
    arrayST.delete(5);
    StdOut.printf(                numOfEntriesListLength, arrayST.size(), arrayST.length());
    StdOut.println();

    // Negative demo for contains
    StdOut.printf("\nCheck for key 3: %s\n", arrayST.contains(3));
  }

  /**
   * Constructor for the unordered array implementation of a Symbol List
   * @param initialCapacity initial capacity for the array containing the key-value-pairs
   */
  @SuppressWarnings("unchecked")
  public ArrayST(int initialCapacity){
    this.capacity = initialCapacity;
    this.data = new KeyValuePair[initialCapacity];
  }

  /**
   * Put a key-value-pair into the list
   * @param key the key to be put into the list
   * @param value the value to be put into the list
   */
  public void put(K key, V value){
    // a value of null results in the pair being deleted
    if(value == null){
      delete(key);
      return;
    }

    for(int i = 0; i < size; i++){
      if(data[i].getKey() == key){
        KeyValuePair<K, V> pair = new KeyValuePair<>(key, value);
        delete(key);
        data[size] = pair;
        size += 1;
        return;
      }
    }


    // double the length of the list, if the current boundary would be exceeded
    if(size == data.length){
      grow();
    }

    // add the pair and increment the number of pairs stored
    KeyValuePair<K, V> pair = new KeyValuePair<>(key, value);
    data[size] = pair;
    size += 1;
  }

  /**
   * Get the value located at the passed key
   * @param key the key whose value must be returned
   * @return value stored at the passed key
   * @throws NoSuchElementException if there is pair with the desired key
   */
  public V get(K key) throws NoSuchElementException{
    for(int i = 0; i < size; i++){

      // return the value if the keys match
      if(data[i].getKey() == key){

        V value = data[i].getValue();

        shiftToFront(i);

        return value;
      }
    }

    // else throw an exception
    throw new NoSuchElementException(String.format("Symbol list does not contain a key %s.", key));
  }

  /**
   * Removes the key value pair specified by the passed key
   * @param key the key of the pair that must be deleted
   */
  @SuppressWarnings("unchecked")
  public void delete(K key){
    // no pairs to delete
    if(isEmpty()) return;

    // create a temp array to store all the values except for the one to be deleted
    KeyValuePair<K, V>[] temp = new KeyValuePair[data.length];

    int j = 0;

    // assign the size to a variable so that size can be changed in the loop
    int n = size;

    for(int i = 0; i < n; i++){
      // if the key is found, decrement size and skip the storing step
      if(data[i].getKey() == key){
        size -= 1;
        continue;
      }

      // if the key is not the one to be deleted, store the corresponding pair in the temp array
      temp[j++] = data[i];
    }

    // assign the temp array to the actual data array
    data = temp;

    // shrink if half the list is empty, never go below the initial capacity
    if(data.length/2 >= capacity && size <= data.length/2){
      shrink();
    }
  }

  /**
   * Check if the desired key is in the list
   * @param key the key to be looked up
   * @return true, if the key is found, false otherwise
   */
  public boolean contains(K key){
    for(int i = 0; i < size; i++){
      if(data[i].getKey() == key){
        return true;
      }
    }

    return false;
  }

  /**
   * Check if the list is empty
   * @return true if the list is empty, false otherwise
   */
  public boolean isEmpty(){
    return size == 0;
  }

  /**
   * Get the size of the list
   * @return the size of the list
   */
  public int size(){
    return size;
  }

  /**
   * Get the smallest key of all pairs stored in the list
   * @return the smallest key
   */
  public K min(){
    if(isEmpty()) throw new IllegalStateException("The symbol list is currently empty. No min in an empty list.");

    K tempMin = data[0].getKey();

    for(int i = 1; i < size; i++){
      K currentKey = data[i].getKey();

      if(currentKey.compareTo(tempMin) < 0){
        tempMin = currentKey;
      }
    }

    return tempMin;
  }
  /**
   * Get the greatest key of all pairs stored in the list
   * @return the greatest key
   */
  public K max(){
    if(isEmpty()) throw new IllegalStateException("The symbol list is currently empty. No max in an empty list.");

    K tempMax = data[0].getKey();

    for(int i = 1; i < size; i++){
      K currentKey = data[i].getKey();

      if(currentKey.compareTo(tempMax) > 0){
        tempMax = currentKey;
      }
    }

    return tempMax;
  }

  /**
   * Get all the keys from the pairs in the list as an iterable
   * @return Iterable of all the keys in the list
   */
  public Iterable<K> keys(){
    Queue<K> queue = new LinkedList<>();

    for(int i = 0; i < size; i++){
      queue.add(data[i].getKey());
    }

    return queue;
  }

  /**
   * Shift the desired index to the front
   *
   * @param index the index of the pair to move to the front
   */
  @SuppressWarnings("unchecked")
  private void shiftToFront(int index){
    KeyValuePair<K, V>[] temp = new KeyValuePair[data.length];

    temp[0] = data[index];
    int j = 1;

    for(int i = 0; i < size; i++){
      if(i == index){
        continue;
      }

      temp[j] = data[i];
      j += 1;
    }

    data = temp;
  }

  public KeyValuePair<K, V> getFirstPair(){
    return data[0];
  }

  // Get the length of the list (! not the # of entries !)
  // Used for only in the test client, therefore private
  private int length(){
    return data.length;
  }

  // Cut the current list in half and copy all the entries into the
  // now shorter list
  @SuppressWarnings("unchecked")
  private void shrink(){
    KeyValuePair<K, V>[] temp = new KeyValuePair[data.length/2];

    System.arraycopy(data, 0, temp, 0, temp.length);

    data = temp;
  }

  // Double the size of the current list and copy all the entries
  // into the now longer list
  @SuppressWarnings("unchecked")
  private void grow(){
    KeyValuePair<K, V>[] temp = new KeyValuePair[data.length * 2];

    System.arraycopy(data, 0, temp, 0, data.length);

    data = temp;
  }

  // Helper class for key-value-pairs the enhance working with generics
  @SuppressWarnings("ClassCanBeRecord") // because Google Formatter doesn't support records yet
  private static class KeyValuePair<K extends Comparable<K>, V extends Comparable<V>> {
    public final K key;
    public final V value;

    // Create a key-value-pair
    public KeyValuePair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    // Get the key of the current pair
    public K getKey(){
      return key;
    }

    // Get the value of the current pair
    public V getValue(){
      return value;
    }

    // Create a String representation of the current pair
    @Override
    public String toString() {
      return String.format("%n[key=%s, val=%s]", key, value);
    }
  }
}
