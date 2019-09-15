import java.io.File;
import java.util.ArrayList;

/**
 * This class demos the bank simulation program. the main method runs simulations of the bank with
 * the same customer data set for 2 - 10 tellers then prints the average customer wait time for
 * each number of tellers. (lower numbers of tellers printed higher on screen).
 */
public class Main {
  public static void main(String[] args){
    ArrayList<Double> waitTimes = new ArrayList();
    for (int i = 2; i < 11; i++){
      try {
        Simulation sim = new Simulation(i, new File("src/data/cust_data1.txt"));
        sim.run_simulation();
        waitTimes.add(sim.computeAveWait());
        //sim.saveStats();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    int simNum = 2;
    for (Double t : waitTimes) {
      System.out.println("Average customer wait time with " + simNum + " tellers was: " + t);
      simNum++;
    }
  }
}
