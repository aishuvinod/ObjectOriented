package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * represents the European Solitaire Model class which is similar to the english model but with four
 * extra cells added in the corners.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel {


  // default constructor that makes side length 3 and empty slot in the center
  public EuropeanSolitaireModel() {
    super();

  }

  // second constructor with a single parameter
  public EuropeanSolitaireModel(int armThickness) {
    super(armThickness);

  }

  // third constructor with two parameters
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(sRow, sCol);

  }

  // fourth constructor with three parameters
  public EuropeanSolitaireModel(int armThickness, int sRow, int sCol)
      throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }


  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    super.move(fromRow, fromCol, toRow, toCol);
  }

  @Override
  public boolean isGameOver() {
    return super.isGameOver();
  }

  @Override
  public int getBoardSize() {
    return super.getBoardSize();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return super.getSlotAt(row, col);
  }

  @Override
  public int getScore() {
    return super.getScore();
  }

  @Override
  protected SlotState[][] fillMarbles() {
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (!isValidPosition(armThickness, i, j)) {
          board[i][j] = SlotState.Invalid;
        } else {
          board[i][j] = SlotState.Marble;
          this.score = score + 1;
        }
      }
    }
    board[sRow][sCol] = SlotState.Empty;
    this.score = score - 1;
    return board;
  }

  //for the position
  private boolean isValidPosition(int armThickness, int r, int c) {
    int lower = armThickness - 1;
    int higher = ((armThickness - 1) * 2);
    if (r < lower && c <= lower && c < lower - r) {
      return false; // top left invalids
    } else if (r < lower && c >= higher && c > higher + r) {
      return false; // top right invalids
    } else if (r > higher && c <= lower && c < armThickness - (getBoardSize() - r)) {
      return false; // bottom left invalids
    } else if (r > higher && c >= higher && c > higher + (getBoardSize() - r - 1)) {
      return false; // bottom left invalids
    }
    return true;
  }


  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    return super.isValidMove(fromRow, fromCol, toRow, toCol);
  }
}


