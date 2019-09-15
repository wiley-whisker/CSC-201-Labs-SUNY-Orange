import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Object that runs a bank simulation with supplied number of tellers and customer service info.
 * @author Wiley Matthews
 */
public class Simulation {

  public Teller[] tellers;
  public Stack<Customer> incomingCustomers;
  public LinkedList<Customer> processedCustomers;
  private int totalCustomers;

  /**
   * Object that runs a bank simulation with supplied number of tellers and customer service info.
   * @param num_tellers int Number of tellers (lines) to serve customers in simulation.
   * @param customerData File Customer csv data file.
   */
  public Simulation(int num_tellers, File customerData) {
    try {
      // Parse data file and load customers (1 row = one cust) into linked list.
      csvFile input = new csvFile(customerData);
      incomingCustomers = new Stack();
      totalCustomers = 0;
      for (int i = 0; i < input.data.length; i++) { // csvRows -> Customer -> Stack.
        String[] row = input.data[(input.data.length - 1) - i];
        Customer newCust = new Customer(Integer.parseInt(row[0]),
            Integer.parseInt(row[1]), Integer.parseInt(row[2]));
        incomingCustomers.push(newCust); // Pushing in reverse so lowest
                                         // entry times are at head of stack.
        totalCustomers++;
      }
      tellers = new Teller[num_tellers]; // Create appropriate sized array of tellers.
      for(int i = 0; i < num_tellers; i++) {
        tellers[i] = new Teller();
        tellers[i].tellerNumber = (i + 1);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Couldn't find data file.");
      e.printStackTrace();
    }
  }

  /**
   * Runs simulation with current number of tellers and saves results.
   */
  public void run_simulation() {
    int simulationTime = 0;
    int numOfProcessed = 0;
    processedCustomers = new LinkedList();
    while(true) {
      /* Add all currently arriving customers to shortest lines. */
      while (!incomingCustomers.empty() &&
          (incomingCustomers.peek().arrivalTime == simulationTime)) {
        addToShortestLine(incomingCustomers.pop(), simulationTime); // Add customer to shortest line
      }

      /* Have tellers process their customers. */
      for (Teller teller : tellers) {
        teller.update();
      }

      /* Remove processed customers from teller and save them for data extraction. */
      for (Teller teller : tellers) {
        if (teller.needsCleaning()) {
          Customer finishedCust = teller.clean(simulationTime);
          processedCustomers.addFirst(finishedCust);//teller.clean(simulationTime));
          numOfProcessed++;
          System.out.println("Teller #" + teller.tellerNumber + " finished helping cust #" +
              finishedCust.customerNumber + " at time " + simulationTime);
        }
      }

      /* Determine if all customers have been served. */
      if (numOfProcessed == totalCustomers) {
        System.out.println("Exit at time " + simulationTime + " having served " +
            numOfProcessed + " customers");
        break; // If all served, end simulation.
      }

      simulationTime++; // Cycle complete, increment time and repeat.
    }
  }

  /**
   * Method to return average customer wait time after simulation was run.
   * @return double Average wait time.
   */
  public double computeAveWait() {
    int totalWait = 0;
    for (Customer c : processedCustomers) {
      totalWait += c.departureTime - c.arrivalTime + c.durOfService;
    }
    double averageWait = totalWait / totalCustomers;
    System.out.println("Average wait time with " + tellers.length + " tellers: " + averageWait);
    return averageWait;
  }

  public void saveStats() {
    String[] headers = {"Customer Number", "Total Wait Time", "Arrival Time", "Departure Time",
        "Duration of Service", "Served by Teller"};
    String[][] data = new String[totalCustomers][];
    int i = 0;
    for (Customer c : processedCustomers) {
      String[] rowData = {c.customerNumber + "", c.getTotalWait() + "", c.arrivalTime +"",
          c.departureTime + "", c.durOfService + "", c.helpedByTeller + ""};
      data[i] = rowData;
      i++;
    }
    try {
      File f = new File("src/data/results_" + tellers.length + "_tellers.txt");
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Couldn't make file.");
      }
      csvFile csv = new csvFile(f);
      csv.write(headers, data);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Helper method to assign supplied customer to the teller with fewest waiting customers.
   * @param c Customer to be assigned.
   * @param t Simulation time of assignment.
   */
  private void addToShortestLine(Customer c, int t) {
    int shortest = totalCustomers + 1; // There will always be a line shorter than initial value.
    Teller associatedTeller = null;
    for (Teller teller : tellers) {
      if (teller.getLineLength() < shortest) {
        shortest = teller.getLineLength();
        associatedTeller = teller;
      }
    }
    associatedTeller.addToLine(c); // Add customer to teller associated with the shortest line.
    System.out.println("Added cust #" + c.customerNumber + " to teller #" +
        associatedTeller.tellerNumber + "'s line at time " + t + " line len now at " +
        associatedTeller.getLineLength());
  }

}
