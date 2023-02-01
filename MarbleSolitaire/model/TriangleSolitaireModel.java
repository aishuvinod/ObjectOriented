package cs3500.marblesolitaire.model.hw04;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Empty;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Marble;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * represents the triangle solitaire model game which allows the user to play the marble solitaire
 * game with a triangular shape.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {

  // arm length
  private final int armThickness;
  // grid
  private SlotState[][] board;
  // the number of rows in the board.
  private final int rows;
  // the number of columns in the board.
  private final int columns;
  // row of the empty cell
  private final int emptyR;
  // column of the empty cell
  private final int emptyC;
  //number of marbles present
  private int score;

  /**
   * initializes the default constructor which creates a triangle of side length zero with an empty
   * cell at 0,0.
   */
  public TriangleSolitaireModel() {
    this.score = 0;
    this.emptyR = 0;
    this.emptyC = 0;
    this.armThickness = 5;
    this.rows = armThickness - 1;
    this.columns = this.rows;
    board = new SlotState[this.armThickness][this.armThickness];
    this.fillMarbles();
  }

  /**
   * initializes the second constructor which constructs a triangle with the given side length with
   * the empty cell at 0,0.
   *
   * @param armThickness the given side length of the triangle.
   * @throws IllegalArgumentException if the given side length is less than 2.
   */
  public TriangleSolitaireModel(int armThickness) throws IllegalArgumentException {
    if (armThickness < 2) {
      throw new IllegalArgumentException("dimension is invalid");
    } else {
      this.score = 0;
      this.emptyR = 0;
      this.emptyC = 0;
      this.armThickness = armThickness;
      this.rows = this.armThickness - 1;
      this.columns = this.rows;
      board = new SlotState[this.armThickness][this.armThickness];
      this.fillMarbles();
    }
  }

  /**
   * initializes the third constructor which constructs a triangle with side length 5 and the empty
   * cell at the given coordinate.
   *
   * @param sRow the row of the empty coordinate
   * @param sCol the column of the empty coordinate
   * @throws IllegalArgumentException if the given empty position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol)
      throws IllegalArgumentException { // number of slots in the bottom most row
    this.armThickness = 5;
    this.rows = this.armThickness - 1;
    this.columns = this.rows;
    if (!isValidPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException("empty position is invalid");
    } else {
      this.score = 0;
      this.emptyR = sRow;
      this.emptyC = sCol;
      board = new SlotState[this.armThickness][this.armThickness];
      this.fillMarbles();
    }
  }

  /**
   * initializes the fourth constructor which takes in the side length and the empty cell
   * coordinates of a triangle.
   *
   * @param armThickness the side length of the triangle
   * @param sRow         the row of the empty cell
   * @param sCol         the column of the empty cell
   * @throws IllegalArgumentException if the given dimension is less than 2 or if the empty cell
   *                                  position is invalid.
   */
  public TriangleSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    // a triangle wont work if the length is less than 2
    if (armThickness < 2) {
      throw new IllegalArgumentException("dimensions are not valid");
    }
    this.armThickness = armThickness;
    this.rows = this.armThickness - 1;
    this.columns = this.rows;
    if (!isValidPosition(armThickness, sRow, sCol)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }
    this.emptyR = sRow;
    this.emptyC = sCol;
    this.score = 0;
    board = new SlotState[this.armThickness][this.armThickness];
    this.fillMarbles();
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!(isValidMove(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("your attempted move is not valid");
    } else {
      board[(fromRow + toRow) / 2][(toCol + fromCol) / 2] = Empty; //  in between is empty
      board[fromRow][fromCol] = Empty;
      board[toRow][toCol] = Marble;

    }
    this.score = score - 1;
  }


  @Override
  public boolean isGameOver() { // fix
    for (int fromRow = 0; fromRow < columns; fromRow++) {
      for (int fromCol = 0; fromCol < rows; fromCol++) {
        for (int toRow = 0; toRow < columns; toRow++) {
          for (int toCol = 0; toCol < rows; toCol++) {
            if (isValidMove(fromRow, fromCol, toRow, toCol)) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) { // fix
    return ((fromCol >= 0) && (toCol >= 0) &&
        (fromRow <= rows) && (fromCol <= columns) && (toRow <= rows) &&
        (toCol <= columns) && (fromRow >= fromCol) && (toRow >= toCol) &&
        // makes sure all coordinates are inside bounds of the board and valid positions

        (board[fromRow][fromCol] == Marble) && (board[toRow][toCol] == Empty)
        && (board[(fromRow + toRow) / 2][(toCol + fromCol) / 2] == Marble) // makes sure that the
        // to coordinate is empty, and the from and cell in  between are marbles

        // right, left, top, and bottom moves
        && (((Math.abs(toRow - fromRow) == 2) && (Math.abs(toCol - fromCol) == 0))
        || ((Math.abs(toCol - fromCol) == 2) && (Math.abs(toRow - fromRow) == 0))
        // diagonals
        || ((Math.abs(toRow - fromRow) == 2) && (Math.abs(toCol - fromCol) == 2)) //right most col
        || ((Math.abs(toRow - fromRow) == 0) && (Math.abs(toCol - fromCol) == 2))));
  }


  @Override
  public int getBoardSize() {
    return this.armThickness;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > rows || col > columns || row < 0 || col < 0) { //isValidPosition???
      throw new IllegalArgumentException("position is invalid");
    } else {
      return board[row][col];
    }
  }

  @Override
  public int getScore() {
    return this.score;
  }

  private SlotState[][] fillMarbles() {
    for (int i = 0; i <= this.rows; i++) {
      for (int j = 0; j <= this.columns; j++) {
        if (!isValidPosition(armThickness, i, j)) {
          board[i][j] = SlotState.Invalid;
        } else {
          board[i][j] = SlotState.Marble;
          score++;
        }
      }
    }
    board[emptyR][emptyC] = SlotState.Empty;
    this.score = score - 1;
    return board;
  }

  private boolean isValidPosition(int armThickness, int r, int c) {
    if (r > rows || c > columns || r < 0 || c < 0) {
      return false; // position is out of bounds of the board
    }
    return r >= c; // row will never be less than the column
  }
}
