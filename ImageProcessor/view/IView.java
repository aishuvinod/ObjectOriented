package view;

import java.io.IOException;

/**
 * Represents the view interfaces which visualizes the possible operations that can be done to the
 * image by rendering the image and a message.
 */
public interface IView {

  /**
   * Produces the pixels of an image with its locations and color values.
   *
   * @return the pixels of an image with its x and y positions and the color values for the
   *          red, blue and green components.
   */
  String visualize();

  /**
   * Produces the image in ppm format.
   *
   * @return String version of the image in ppm format
   */
  String toPPM();

  /**
   * Displays the image to the user in terms of its location and color values.
   *
   * @throws IOException if any transmission has failed
   */
  void renderImage() throws IOException;

  /**
   * Displays a message according to if the user's inputted operation to the image has failed or
   * succeeded.
   *
   * @param message the message that will be outputted to the user
   * @throws IOException if any transmission has failed
   */
  void renderMessage(String message) throws IOException;

}
