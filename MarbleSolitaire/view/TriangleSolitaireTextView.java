package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;

/**
 * converts the triangle model into a visual representation.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView implements
    MarbleSolitaireView {


  /**
   * initializes the model that will be used to visualize the triangle solitaire model.
   *
   * @param model takes in the model.
   * @throws IllegalArgumentException throws an illegal argument exception if the given model is
   *                                  null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
      throws IllegalArgumentException {
    super(model);
  }

  /**
   * transmits the model to the destination.
   *
   * @param model       the given model
   * @param destination the appendable destination
   * @throws IllegalArgumentException if either the model or destination is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination) {
    super(model, destination);
  }

  /**
   * renders the model as a visual board.
   *
   * @throws IOException if there is an input or output error.
   */
  @Override
  public void renderBoard() throws IOException {
    super.renderBoard();
  }

  /**
   * renders messages to the user.
   *
   * @throws IOException if there is an input or output error.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    super.renderMessage(message);
  }

  /**
   * converts the given triangle model to a visual representation that uses "O" for marbles, "_" for
   * an empty cell, and "" for an Invalid cell.
   *
   * @return the visual representation of the board as a string.
   */
  @Override
  public String toString() {
    StringBuilder build = new StringBuilder();
    for (int i = 0; i < model.getBoardSize(); i++) {
      int spaces = model.getBoardSize() - 1 - i;
      while (spaces > 0) { // adds spaces before every row
        build.append(" ");
        spaces--;
      }
      for (int j = 0; j < model.getBoardSize(); j++) {
        SlotState e = model.getSlotAt(i, j);
        switch (e) {
          case Marble:
            if (i == model.getBoardSize() && j == model.getBoardSize()) {
              build.append("O"); // last marble, prevents from making a new line
            } else if (i == j) {
              build.append("O\n"); // last marble in each row, makes a new line
            } else {
              build.append("O "); // all other marbles
            }
            break;
          case Empty:
            if (i == model.getBoardSize() && j == model.getBoardSize()) {
              build.append("_"); // last marble, prevents from making a new line
            } else if (i == j) {
              build.append("_\n"); // last marble in each row, makes a new line
            } else {
              build.append("_ "); // all other marbles
            }
            break;
          case Invalid:
            // no invalid print required
            break;
          default:
            System.out.print("error");
        }
      }
    }
    return build.substring(0, build.length() - 1); // removes the last line
  }
}




