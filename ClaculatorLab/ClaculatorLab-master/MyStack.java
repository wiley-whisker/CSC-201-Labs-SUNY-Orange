import java.util.NoSuchElementException;

/**
 * A LIFO data structure (stack) implemented as a linked list.
 * @param <T> Data type to store.
 */
public class MyStack<T> {
  Node<T> head;

  /**
   * Constructor sets first node equal to null.
   */
  public MyStack() {
    head = null;
  }

  /**
   * Add a new element to the top of the stack.
   * @param newItem New element to be stored.
   */
  public void push(T newItem) {
    Node<T> newNode = new Node(newItem);
    System.out.println("pushing " + newItem);
    if (head == null) { //List is empty.
      head = newNode;
    }
    else { // Existing elements in list.
      newNode.next = head;
      head = newNode;
    }
  }

  /**
   * Retrieve element from the top of the stack. (removes element from stack)
   * @return Element on top of the stack.
   */
  public T pop() {
    if (head == null) { // List empty
      throw new NoSuchElementException();
    }
    T toRet = head.data; // Element(s) in list, return data in head (top element).
    head = head.next;
    System.out.println("popping " + toRet);
    return toRet;
  }

  /**
   * Look at the element on top of the stack (without removing).
   * @return A reference to the element on top of the stack.
   */
  public T peek() {
    if (head == null) { // List empty
      throw new NoSuchElementException();
    }
    return head.data; // Element(s) in list, return data at top.
  }

  public boolean isEmpty() {
    return head == null;
  }

  /**
   * A node with references to the next and previous nodes in the chain.
   * @param <T1> Data type for node to hold.
   */
  private class Node<T1> {
    public T1 data;
    public Node<T1> next;
    public Node<T1> prev;
    public Node(T1 newData) {
      data = newData;
      next = null;
      prev = null;
    }
  }
}
