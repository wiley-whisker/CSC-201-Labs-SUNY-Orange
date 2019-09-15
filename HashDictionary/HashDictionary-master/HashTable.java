import java.util.NoSuchElementException;

public class HashTable<V> {
  public HashLink<String, V>[] hashes;
  private int size;
  private int outputSpace;

  /**
   * A hashtable
   * @param outputSpace the array size to be placed into.
   */
  public HashTable(int outputSpace) {
    hashes = new HashLink[outputSpace];
    size = 0;
    this.outputSpace = outputSpace;
    for (int i = 0; i < outputSpace; i++) {
      hashes[i] = new HashLink<String, V>();
    }
  }

  /**
   * A simple hashing algorithm to represent a string as an integer.
   * @param key the string to be hashed
   * @return the hashcode of the key.
   */
  private int hash(String key) {
    char[] arr = key.toCharArray();
    int h = 1;
    for (int i = 0; i < arr.length; i++) {
      h += arr[i] ^ (h + arr.length *7);
    }
    h = h % outputSpace;
    return h;
//    int h = key.hashCode();
//    if (h < 0) { h = -h; }
//    return h % outputSpace;
  }

  /**
   * Add a new key-value pair to the map.
   * @param key String key to the value.
   * @param newVal The value to be associated with the key.
   */
  public void add(String key, V newVal) {
    int hash = hash(key);
    if (!hashes[hash].contains(key)) {
      System.out.println(key + " --> " + newVal + " by code: " + hash);
      hashes[hash].add(key, newVal);
      size++;
    }
  }

  /**
   * Determines if the supplied key has an entry in the map.
   * @param key String key
   * @return Boolean whether or not the key has been mapped to a value.
   */
  public boolean contains(String key) {
    int hash = hash(key);
    if (hashes[hash].contains(key)) {
      return true;
    }
    return false;
  }

  /**
   * Get the value associated with the provided key.
   * @param key String key
   * @return The value that the key maps to.
   * @throws NoSuchElementException
   */
  public V get(String key) throws NoSuchElementException {
    int hash = hash(key);
    System.out.println("Searching for " + key + " using code: " + hash);
    if (!hashes[hash].contains(key)) {
      throw new NoSuchElementException();
    }
    return hashes[hash].get(key);
  }

  /**
   * Dissociate a key wit3h a particular value. key-value pair will be removed from map.
   * @param key key of value.
   * @throws NoSuchElementException
   */
  public void remove(String key) throws NoSuchElementException {
    int hash = hash(key);
    if (hashes[hash].contains(key)) {
      hashes[hash].remove(key);
      size--;
    } else {
      throw new NoSuchElementException();
    }
  }

  /**
   * Get the number of key-value pairs in map.
   * @return int number of pairs.
   */
  public int size() {
    return size;
  }

  /**
   * Determine the number of hash collisions.
   * @return int number of collisions in map.
   */
  public int getCollisions() {
    int sum = 0;
    for (int i = 0; i < hashes.length; i++) {
      sum += hashes[i].collisions;
    }
    return sum;
  }
}
