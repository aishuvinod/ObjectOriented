package controller;

import java.io.IOException;

/**
 * Represents the method for the controller which processes the image inputted by the user.
 */
public interface IController {

  /**
   * Processes inputs from the user based on how they want to edit their loaded image.
   *
   * @throws IOException when any sort of transmission fails
   */
  void imageProcessor() throws IOException;

}
