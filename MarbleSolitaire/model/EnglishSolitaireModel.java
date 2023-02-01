package cs3500.marblesolitaire.model.hw02;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Invalid;
import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Marble;

/**
 * class that represents the marble solitaire model. creates a solitaire game on a board where a
 * user can make specific moves. game ends when there are no moves left.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {

  // the number of marbles in the top row, bottom row, left row, and right row.
  protected final int armThickness;
  // grid
  protected SlotState[][] board;
  // the number of rows in the board.
  protected final int rows;
  // the number of columns in the board.
  protected final int columns;
  // row of the empty cell
  protected int sRow;
  // column of the empty cell
  protected int sCol;
  //number of marbles present
  protected int score;


  /**
   * initializes the game board with arm thickness 3 and the empty slot in the center.
   */
  public EnglishSolitaireModel() {
    this.armThickness = 3;
    this.rows = (armThickness * 3) - 2;
    this.columns = this.rows;
    this.board = new SlotState[this.rows][this.columns];
    this.sRow = armThickness;
    this.sCol = armThickness;
    this.score = 0;
    this.board = this.fillMarbles();
  }

  /**
   * initializes the game board with arm thickness 3 and the empty slot at the given position.
   *
   * @param sRow the row of the empty cell.
   * @param sCol the column of the empty cell.
   * @throws IllegalArgumentException if the specified position is invalid.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this.armThickness = 3;
    if (sRow < 0
        || sCol < 0 || sRow > (armThickness * 3) - 2 || sCol > (armThickness * 3) - 2) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else if (!((sRow >= armThickness - 1 && sRow <= (armThickness - 1) * 2) || (
        sCol >= armThickness - 1 && sCol <= (armThickness - 1) * 2))) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");

    } else {
      this.sRow = sRow;
      this.sCol = sCol;
      this.rows = (this.armThickness * 3) - 2;
      this.columns = this.rows;
      this.board = new SlotState[this.rows][this.columns];
      this.score = 0;
      this.board = this.fillMarbles();
    }


  }


  /**
   * initializes the game board with the empty slot at the center.
   *
   * @param armthickness the armthickness of the board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EnglishSolitaireModel(int armthickness) throws IllegalArgumentException {
    if (armthickness % 2 != 1) {
      throw new IllegalArgumentException("arm thickness is not a positive odd number");
    } else {
      this.armThickness = armthickness;
      this.rows = (armthickness * 3) - 2;
      this.columns = this.rows;
      this.board = new SlotState[this.rows][this.columns];
      this.sRow = this.rows / 2;
      this.sCol = sRow;
      this.score = 0;
      this.board = this.fillMarbles();
    }

  }

  /**
   * initializes the game board with the given arm thickness, row, and column.
   *
   * @param armThickness the given arm thickness for the board.
   * @param sRow         the given row of the empty slot for the board.
   * @param sCol         the given column of the empty slot for the board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or if the
   *                                  empty cell position is invalid.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    this.armThickness = armThickness;
    if (armThickness % 2 != 1 || sRow < 0
        || sCol < 0 || sRow >= (armThickness * 3) - 2
        || sCol >= (armThickness * 3) - 2) {
      throw new IllegalArgumentException("arm thickness is not a positive odd number");
    } else {
      this.rows = (armThickness * 3) - 2;
      this.columns = this.rows;
      board = new SlotState[this.rows][this.columns];
      this.sRow = sRow;
      this.sCol = sCol;
      this.score = 0;
      if (board[sRow][sCol] == Invalid || board[sRow][sCol] == Marble) {
        throw new IllegalArgumentException("");
      }
      this.fillMarbles();
    }
  }

  /**
   * fills the board with the slots that are supposed to be invalid, empty, or a marble.
   *
   * @return the board with all the different slot states
   */
  protected SlotState[][] fillMarbles() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (i < armThickness - 1 && j < armThickness - 1) {
          board[i][j] = Invalid; // top left
        } else if (i > (armThickness - 1) * 2 && j < armThickness - 1) {
          board[i][j] = Invalid; //top right
        } else if (i < armThickness - 1 && j > (armThickness - 1) * 2) {
          board[i][j] = Invalid; //bottom left
        } else if (i > (armThickness - 1) * 2 && j > (armThickness - 1) * 2) {
          board[i][j] = Invalid; // bottom right
        } else if (i == sRow && j == sCol) {
          board[i][j] = SlotState.Empty;
        } else {
          this.score = score + 1;
          board[i][j] = Marble;
        }
      }
    }
    return this.board;
  }

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!(isValidMove(fromRow, fromCol, toRow, toCol))) {
      throw new IllegalArgumentException("your attempted move is not valid");
    } else {
      board[(fromRow + toRow) / 2][(toCol + fromCol) / 2] = SlotState.Empty; //  in between is empty
      board[fromRow][fromCol] = SlotState.Empty;
      board[toRow][toCol] = Marble;

    }
    this.score = score - 1;
  }


  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() { // check to see conditions across the whole board
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

  /**
   * checks to see if the given coordinates to move from and to are valid.
   *
   * @param fromRow the coordinate of the row from where the marble will be moved.
   * @param fromCol the coordinate of the column from where the marble will be moved.
   * @param toRow   the coordinate of the row to where the marble will be moved.
   * @param toCol   the coordinate of the column to where the marble will be moved.
   * @return true if the move spot is valid.
   */
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) { //protected
    return ((fromRow >= 0) && (fromCol >= 0) && (toRow >= 0) && (toCol >= 0) &&
        (fromRow < getBoardSize()) &&
        (fromCol < getBoardSize()) && (toRow < getBoardSize()) &&
        (toCol < getBoardSize()) && (board[fromRow][fromCol] == Marble) && (board[toRow][toCol]
        == SlotState.Empty)
        && (board[(fromRow + toRow) / 2][(toCol + fromCol) / 2] == Marble)
        && (((Math.abs(toRow - fromRow) == 2) && ((toCol - fromCol) == 0))
        || ((Math.abs(toCol - fromCol) == 2) && (Math.abs(toRow - fromRow) == 0))));
  }


  /**
   * Return the size of this board which is the longest dimension of a board.
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.columns; // longest dimension on the board
  }

  /**
   * Gets the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond the dimensions of the
   *                                  board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0
        || col < 0 || row >= (armThickness * 3) - 2 || col >= (armThickness * 3) - 2) {
      throw new IllegalArgumentException("the row or the column are beyond the dimensions of"
          + " the board");
    } else {
      return this.board[row][col];
    }
  }

  /**
   * Returns the number of marbles currently on the board which is also the score.
   *
   * @return the number of marbles currently on the board.
   */
  @Override
  public int getScore() {
    return this.score;
  }
}

