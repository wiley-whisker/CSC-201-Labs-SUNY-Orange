public class Main {
  /**
   * Demo the N Queens problem.
   * @param args Command line arguments.
   */
  public static void main(String[] args) {
    Solver s = new Solver();
    double startTime;
    double endTime;
    for (int i = 1; i < 13; i++) { // Run simulation for 4, 5, and 6 chessboards.
      startTime = System.currentTimeMillis() / 1000.0; // Time immediately before algorithm started.
      ChessBoard b = s.solveFor(i);
      endTime = System.currentTimeMillis() / 1000.0; // Time immediately after algorithm started.
      System.out.println("Solution for " + i + "x" + i + " chessboard.\nQueens: " + b.queens + ".");
      b.print();
      System.out.println("Took " + (endTime - startTime)); // Show total time algorithm takes to run.
    }
  }
}
