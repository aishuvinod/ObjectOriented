package view;

import java.io.IOException;

import model.IModel;
import model.Pixel;

/**
 * Class representing the view of the image editor and implements the IView interface.
 * Contains the model and an Appendable as fields.
 */
public class TextView implements IView {

  private final IModel model;
  private final Appendable destination;

  /**
   * Constructs the text view given the model.
   *
   * @param model the given model
   * @throws IllegalArgumentException if the model is null
   */
  public TextView(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Image cannot be rendered if null.");
    }

    this.model = model;
    this.destination = System.out;
  }

  /**
   * Constructs the text view with the given model and Appendable destination.
   *
   * @param model       the given model
   * @param destination the given destination
   * @throws IllegalArgumentException if either the model or destination is null
   */
  public TextView(IModel model, Appendable destination) throws IllegalArgumentException {
    if (model == null || destination == null) {
      throw new IllegalArgumentException("Image cannot be rendered if it or the destination " +
          "are null");
    }

    this.model = model;
    this.destination = destination;

  }

  @Override
  public String visualize() {
    StringBuilder image = new StringBuilder();

    for (int r = 0; r < this.model.getRows(); r = r + 1) {
      for (int c = 0; c < this.model.getCols(); c = c + 1) {

        Pixel p = this.model.getPixel(r, c);
        String pixelSingle = "Color of model.pixel (" + r + "," + c + ")" + ": " + p.getR()
            + "," + p.getG() + "," + p.getB() + "\n";

        image.append(pixelSingle);
      }
    }
    return image.toString() + "\n";
  }

  @Override
  public String toPPM() {
    StringBuilder image = new StringBuilder();
    String ppmHeader = "P3\n" + "#\n" + this.model.getRows() + " " + this.model.getCols()
        + " 255\n";
    image.append(ppmHeader);

    for (int c = 0; c < this.model.getCols(); c = c + 1) {
      for (int r = 0; r < this.model.getRows(); r = r + 1) {

        Pixel p = this.model.getPixel(r, c);
        String pixelSingle = p.getR() + "\n" + p.getG() + "\n" + p.getB() + "\n";

        image.append(pixelSingle);
      }
    }
    return image.toString() + "\n";
  }

  @Override
  public void renderImage() throws IOException {
    try {
      destination.append(this.visualize());
    } catch (IOException e) {
      throw new IOException("Transmission failed");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      destination.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission failed");
    }
  }


}
