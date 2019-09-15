/**
 * A class that represents a bank customer and associated data values.
 * @author Wiley Matthews
 */
public class Customer implements Comparable<Customer> {
  public int customerNumber;
  public int arrivalTime;
  public int departureTime;
  public int durOfService;
  public int helpedByTeller;

  /**
   * A class that represents a bank customer and associated data values.
   * @param cNum customer (ID?) number
   * @param arrTime Time at which the customer arrives to simulation and can begin being processed.
   * @param dOS How long the customer will need to spend with the teller.
   */
  public Customer(int cNum, int arrTime, int dOS) {
    customerNumber = cNum;
    arrivalTime = arrTime;
    durOfService = dOS;
  }

  /**
   * Gets total time customer was waiting (really how long they were in the bank).
   * @return total time the customer was waiting and being served.
   */
  public int getTotalWait() {
    return (departureTime - arrivalTime + durOfService);
  }

  /**
   * Implements the compare method of the comparable interface. Compares total wait times of each
   * customer. Implemented for statistical analysis after simulation.
   * @param c Customer to be compared to.
   * @return Difference between this customer's total wait time and supplied's total wait time.
   */
  @Override
  public int compareTo(Customer c) {
    return (getTotalWait() - c.getTotalWait());
  }
}
