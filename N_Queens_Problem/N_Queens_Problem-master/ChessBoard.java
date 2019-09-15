/**
 * @author Wiley Matthews
 * A class that represents an n by n chessboard.
 */
public class ChessBoard {

  public char[][] tiles; // Board.
  public int size; // Width and length of board.
  public int queens; // Number of queens present on board.

  /**
   * A class that represents an n by n chessboard.
   * @param n Width and length of the board.
   */
  public ChessBoard(int n) {
    tiles = new char[n][n]; // Size board.
    size = n;
    queens = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        tiles[i][j] = 'o'; // Fill board with open spaces.
      }
    }
  }

  /**
   * A method to place a queen on the board and mark off resulting attacked spaces.
   * @param row x coord
   * @param col y coord
   */
  public void place_queen(int row, int col) {
    for (int i = 0; i < size; i++) {
      tiles[row][i] = 'x'; // Mark row as attacked.
    }
    for (int i = 0; i < size; i++) {
      tiles[i][col] = 'x'; // Mark column as attacked.
    }
    // Mark diagonals as attacked.
    // Up, right.
    int offset = 1;
    int newRow = row - offset;
    int newCol = col + offset;
    while ((newRow >= 0) && newCol < size) {
      tiles[newRow][newCol] = 'x';
      offset++;
      newRow = row - offset;
      newCol = col + offset;

    }
    // Down, right
    offset = 1;
    newRow = row + offset;
    newCol = col + offset;
    while ((newRow < size) && newCol < size) {
      tiles[newRow][newCol] = 'x';
      offset++;
      newRow = row + offset;
      newCol = col + offset;

    }
    // Up, left
    offset = 1;
    newRow = row - offset;
    newCol = col - offset;
    while ((newRow >= 0) && newCol >= 0) {
      tiles[newRow][newCol] = 'x';
      offset++;
      newRow = row - offset;
      newCol = col - offset;

    }
    // Down, left
    offset = 1;
    newRow = row + offset;
    newCol = col - offset;
    while ((newRow < size) && newCol >= 0) {
      tiles[newRow][newCol] = 'x';
      offset++;
      newRow = row + offset;
      newCol = col - offset;

    }
    tiles[row][col] = 'Q'; // Indicate location of new queen
    queens++; // Update number of queens on board.
  }

  /**
   * Prints the board to the command line.
   */
  public void print() {
    for (int i = 0; i < size; i++) {
      String line = "|"; // Will fill a row then print.
      for (int j = 0; j < size; j++) {
        line = line + tiles[i][j] + "|";
      }
      System.out.println(line);
    }
  }

  /**
   * Makes parent chessboard into an exact replica supplied board.
   * @param originalBoard Board to replicate.
   */
  public void asCopyOf(ChessBoard originalBoard) {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        tiles[i][j] = originalBoard.tiles[i][j];
      }
    }
    queens = originalBoard.queens;
  }
}
