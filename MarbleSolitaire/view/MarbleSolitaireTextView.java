package cs3500.marblesolitaire.view;


import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState.Invalid;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import java.io.IOException;


/**
 * class that helps us visually view the marble solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  protected final MarbleSolitaireModelState model;


  private final Appendable destination;

  /**
   * initializes the model that will be used to visualize the english solitaire model.
   *
   * @param model takes in the model.
   * @throws IllegalArgumentException throws an illegal argument exception if the given model is
   *                                  null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("the provided model is null");
    } else {
      this.model = model;
      this.destination = System.out;
    }
  }

  /**
   * transmits the model to the destination.
   *
   * @param model       the given model
   * @param destination the appendable destination
   * @throws IllegalArgumentException if either the model or destination is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
      throws IllegalArgumentException {
    if (model == null || destination == null) {
      throw new IllegalArgumentException("your model or appendable object is null");
    } else {
      this.model = model;
      this.destination = destination;
    }
  }

  /**
   * converts a solitaire model into a string for viewing purposes.
   *
   * @return returns a string of the printed board.
   */
  public String toString() {

    int armThickness = ((model.getBoardSize() + 2) / 3);

    StringBuilder build = new StringBuilder();
    for (int i = 0; i < model.getBoardSize(); i++) {
      for (int j = 0; j < model.getBoardSize(); j++) {
        SlotState e = model.getSlotAt(i, j);
        switch (e) {
          case Marble:
            if (j == model.getBoardSize() - 1) { // make a new line in the three middle rows
              build.append("O\n");
            } else if (model.getSlotAt(i, j + 1) == Invalid) {
              build.append("O\n");
            } else {
              build.append("O ");
            }
            break;
          case Invalid:
            if (j > ((armThickness + 2) / 3)) {
              build.append("");
            } else {
              build.append("  ");
            }
            break;
          case Empty:
            if (j == model.getBoardSize() - 1) {
              build.append("_\n");
            } else if (model.getSlotAt(i, j + 1) == Invalid) {
              build.append("_\n");
            } else {
              build.append("_ ");
            }
            break;
          default:
            System.out.print("error");
        }

      }

    }
    return build.substring(0, build.length() - 1); // removes the last line
  }


  /**
   * transmits the state of the board to the destination.
   *
   * @throws IOException if the transmission fails
   */
  @Override
  public void renderBoard() throws IOException {
    this.destination.append(toString());

  }

  /**
   * transmits the given message to the destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if the transmission of the message fails.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);

  }
}











