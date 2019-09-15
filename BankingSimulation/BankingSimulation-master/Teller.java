import java.util.LinkedList;

/**
 * A class that represents a bank teller with associated line.
 * @author Wiley Matthews
 */
public class Teller {
  private int lineLength;
  private int helpedFor;
  private LinkedList<Customer> line;
  private Customer currentlyHelping;
  private Customer lastHelped;
  public int tellerNumber;

  /**
   * A class that represents a bank teller with associated line.
   */
  public Teller() {
    lineLength = 0;
    helpedFor = 0;
    line = new LinkedList();
    currentlyHelping = null;
  }

  /**
   * Add a customer to this tellers line to be served.
   * @param c Customer to be added.
   */
  public void addToLine(Customer c) {
    line.addLast(c);
    lineLength++;
  }

  /**
   * Update teller. Will increment service time of current customer or, if nobody is being served,
   * begin serving the next customer in line. If a customer has finished being served, that customer
   * will be put on hold to be handled by the clean() method.
   */
  public void update() {
    if (currentlyHelping == null) { // If teller isn't currently serving someone
      if (!line.isEmpty()) { //  And there are customers in line to be served
        currentlyHelping = line.getFirst(); // Help that customer.
        helpedFor = 0; //  Reset time teller has been serving the new customer.
        lineLength--; // One less person in line.
        line.removeFirst(); // Remove customer from line. They are being served.
      }
    } else { // Else, the teller is busy serving someone
      helpedFor++; // They have been helping the for +1 time.
      if (helpedFor == currentlyHelping.durOfService) { // If that customer has been served.
        lastHelped = currentlyHelping; // have hat customer wait to be picked up by clean() method.
        currentlyHelping = null; // Not serving anyone.
      }
    }
  }

  /**
   * The number of customers waiting in line.
   * @return The number of customers waiting in line (not including one possibly being served).
   */
  public int getLineLength() {
    return lineLength;
  }

  /**
   * Returns true or false depending on if a served customer is waiting for pickup.
   * @return whether or not the teller needs cleaning.
   */
  public boolean needsCleaning() {
    return lastHelped != null;
  }

  /**
   * Returns the last served customer and  clears the holding space for a new one.
   * @param t Simulation time at time of cleaning.
   * @return The most recently served customer.
   */
  public Customer clean(int t) {
    Customer ret = lastHelped; // Create new reverence of served customer to be returned.
    ret.departureTime = t; // Log time associated with this customer being processed.
    ret.helpedByTeller = tellerNumber; // Log which teller served this customer.
    lastHelped = null; // Clear space for next served customer.
    return ret;
  }
}
