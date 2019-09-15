import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

public class HashLink<K, V> {

  public int collisions = 0;

  /**
   * Represents a node in a hashlink.
   */
  private class Node {
    public K key;
    public V val;
    Node next;
    public Node(K key, V val) {
      this.key = key;
      this.val = val;
      this.next = null;
    }
  }

  private Node first;

  public HashLink() {
    first = null;
  }

  /**
   * Determines if a key has been mapped to a value.
   * @param key Key to value
   * @return Boolean whether or not the key is mapped to a value here.
   */
  public boolean contains(K key) {
    if (first != null) {
      Node searchNode = first;
      do {
        if (searchNode.key.equals(key)) {
          return true;
        }
        searchNode = searchNode.next;
      } while (searchNode != null);
    }
    return false; // First is null or key is not in list.
  }

  /**
   * Get the value the supplied key maps to.
   * @param key key to value
   * @return the value associated with the supplied key
   * @throws IllegalStateException
   * @throws NoSuchElementException
   */
  public V get(K key) throws IllegalStateException, NoSuchElementException {
    if (first == null) {
      throw new IllegalStateException();
    }
    if (!contains(key)) {
      throw new NoSuchElementException();
    }
    Node temp = first;
    while (!first.key.equals(key)) {
      first = first.next;
    }
    return temp.val;
  }

  /**
   * Associate the supplied key with the supplied value.
   * @param key key to be mapped to the value
   * @param newVal value to be associated with the key
   */
  public void add(K key, V newVal) {
    if (!contains(key)) {
      if (first != null) { //  Elements exist in list.
        System.out.println("Collision!");
        collisions++;
        Node temp = first;
        while (temp.next != null) { // Iterate to end and add there.
          temp = temp.next;
        }
        temp.next = new Node(key, newVal);
      } else { // No elements in list.
        first = new Node(key, newVal); // just add as first.
      }

    }
    // Key already in list. do something?
  }

  /**
   * Disassociate the key with the value
   * @param key key to value
   * @throws IllegalStateException
   * @throws NoSuchElementException
   */
  public void remove(K key) throws IllegalStateException, NoSuchElementException {
    if (first != null) {
      Node temp = first;
      Node prev = null;
      while ((temp.next != null) && (!temp.key.equals(key))) { // Iterate to end of list or key.
        prev = temp;
        temp = temp.next;
      }
      if (temp.key.equals(key)) { // if we found the key
        if (prev == null) { // if key is first element of list
          first = first.next; // just cut it out.
        } else { // key is not first element
          prev.next = temp.next; // cut out that element
        }
      } else { // reached end of list without finding element.
        throw new NoSuchElementException();
      }
    } else { // no elements in list to begin with.
      throw new IllegalStateException();
    }
  }
}
