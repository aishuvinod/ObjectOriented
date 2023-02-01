package cs3500.marblesolitaire.controller;

/**
 * represents the interface for the marble solitaire controller. the controller takes in information
 * from the user and acts as the intermediary between the model and view.
 */
public interface MarbleSolitaireController {

  /**
   * plays a new game of marble solitaire.
   *
   * @throws IllegalArgumentException if the controller is unable to read input or transmit output.
   */
  void playGame() throws IllegalArgumentException;
}
