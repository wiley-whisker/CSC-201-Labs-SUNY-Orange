import java.util.ArrayList;

/**
 * Solves the n Queens problem using recursion. Needs the ChessBoard class.
 */
public class Solver {
  /**
   * A method that simulates and solves the N Queens problem.
   * @param n The dimension of the chessboard.
   * @return A Chessboard with the optimal placement for maximum queens.
   */
  public ChessBoard solveFor(int n) {
    ChessBoard bestBoard = new ChessBoard(n);
    return trial(bestBoard);
  }

  /**
   * A method that examines all possible next moves from a provided board state.
   * @param initial_board ChessBoard starting position.
   * @return A Chessboard with the optimal placement for maximum queens.
   */
  private ChessBoard trial(ChessBoard initial_board) {
    /*
      What this method does, when supplied with an initial board state, is:
      <p>RECURSIVE CASE
        * For every tile on the supplied board that is not occupied by a queen or under attack from a queen:
            Simulate a new chessboard that is an exact copy of the supplied chessboard.
            Place a queen on the simulated board on the next open tile.
            Call the method again on the simulated board.
            Compare resulting # of queens to current best board.
              if better, that is the new bestBoard
                if better board has n queens, return. a solution has been found
              else, disregard new beard and try another move.

      <p>BASE CASE
        * If there are no empty tiles left on the supplied board:
            Return the board, as there is no more to be done. End of branch.
    */
    // Here for efficiency, if the board is filled with optimal solution return immediately.
    if (initial_board.queens == initial_board.size) {
      return initial_board; // Board is filled with maximum number of queens.
    }
    // RECURSIVE CASE.
    int size = initial_board.size;
    int mostQueens = initial_board.queens;
    ChessBoard bestBoard = new ChessBoard(size);
    ChessBoard newBoard = new ChessBoard(size);
    for (int i = 0; i < size; i++) { // Iterating through rows.
      for (int j = 0; j < size; j++) { // iterating through columns.
        if (initial_board.tiles[i][j] == 'o') { // If so, create a new branch of trials from this initial state.
          newBoard.asCopyOf(initial_board); // Copying board state.
          newBoard.place_queen(i, j); // Placing new queen on open tile.
          newBoard = trial(newBoard); // Branching.
          if (newBoard.queens > mostQueens) {
            bestBoard.asCopyOf(newBoard); // New board is better (has more queens) than current best. remember new best.
          }
          if (bestBoard.queens == bestBoard.size) { // If ideal solution is found, return immediately.
            return bestBoard;
          }
        }
      }
    }
    // BASE CASE. the program has escaped the for loops, the board must be full. Also doesn't contain an ideal solution.
    return initial_board;
  }
}
