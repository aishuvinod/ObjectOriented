package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import java.io.IOException;
import java.util.Scanner;

/**
 * implements the class that takes in user input.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;


  /**
   * constructs the controller.
   *
   * @param model the model for the game.
   * @param view  transmits user input into an appendable object
   * @param input Readable object which takes in inpur from the user
   * @throws IllegalArgumentException if either the model, view, or controller is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("no null inputs are allowed");
    } else {
      this.model = model;
      this.view = view;
      this.input = input;

    }
  }


  /**
   * allows user to play the game. quits when "q" or "Q" is pressed. asks the user to re-enter a
   * value if the input isnt a positive number or 'q' or 'Q' prints out a message for all scenarios
   * and when the game is over
   *
   * @throws IllegalArgumentException if any of the inputs are null.
   * @throws IOException              if any of the transmission fails.
   */
  @Override
  public void playGame() throws IllegalStateException {
    int[] arrayList = new int[4];
    boolean quit = false;
    int counter = 0;
    try {
      view.renderBoard();
      this.view.renderMessage("\nScore: " + model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
    Scanner scanner = new Scanner(this.input);

    while (!model.isGameOver() && !quit) { // while the game is ongoing
      // and while the game still has more inputs left and the user hasn't hit quit
      if (!(scanner.hasNext())) {
        throw new IllegalStateException("Readable has run out of inputs");
      }
      String next = scanner.next();
      if (next.equals("q") || next.equals("Q")) {
        try {
          quit = true;
          this.view.renderMessage("Game quit!\n");
          this.view.renderMessage("State of game when quit:\n");
          this.view.renderBoard();
          this.view.renderMessage("\n" + "Score: " + model.getScore());
          break;
        } catch (IOException e) {
          throw new IllegalStateException("transmission failed");
        }
      }
      int toInt = 0;
      if (!(next.equals("q") || next.equals("Q"))) {

        try {
          toInt = Integer.parseInt(next);
        } catch (NumberFormatException n) {
          n.printStackTrace();
        }

        if (toInt > 0) {
          arrayList[counter] = toInt; // add to the counter if the 4 values are correct
          counter++;
          if (counter == 4) {
            try {
              // pass info to model and make the move
              model.move(arrayList[0] - 1, arrayList[1] - 1,
                  arrayList[2] - 1, arrayList[3] - 1);
              counter = 0;
              arrayList = new int[4];
              this.view.renderBoard();
              this.view.renderMessage("\n" + "Score: " + model.getScore() + "\n");
            } catch (IOException e) {
              throw new IllegalStateException("transmission failed");
            } catch (IllegalArgumentException e) {
              try {
                this.view.renderMessage(
                    "Invalid move.Play again. Your move was one of these: not two spots away, "
                        + "the to spot was empty, to spot was invalid, or "
                        + "there wasn't a marble in between\n");
                counter = 0;
                arrayList = new int[4];
              } catch (IOException f) {
                throw new IllegalStateException(f);
              }
            }
          }
        } else {
          tryCatchHelper();

        }
      }
      if (model.isGameOver()) {
        try {
          quit = true;
          this.view.renderMessage("Game over!\n");
          this.view.renderBoard();
          this.view.renderMessage("\nScore: " + model.getScore() + "\n");
          scanner.close();
        } catch (IOException e) {
          throw new IllegalStateException("transmission failed");
        }
      }
    }
  }

  /**
   * try catch helper for when an entered value is invalid. entered value is not "q", "Q", or a
   * positive number.
   */
  public void tryCatchHelper() {
    try {
      this.view.renderMessage("re-enter your value\n");
    } catch (IOException e) {
      throw new IllegalStateException("transmission failed");
    }
  }

}
