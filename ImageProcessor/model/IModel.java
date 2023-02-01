package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the ImageEditor of this image processor. The interface contains methods that should be
 * used when trying to load, retrieve, edit, and save an image. Edits that can be done to an image
 * include visualizing its color components, luma, intensity, value, brightening and darkening,
 * flipping, and applying filters such as greyscale and sepia.
 */
public interface IModel {

  /**
   * Takes in the image and temporarily stores it in a hashmap to be used/retrieved from later.
   *
   * @param fileName the name of the PPM file
   * @param newName  the name to rename the file
   * @throws FileNotFoundException when the file cannot be found
   */
  void load(String fileName, String newName) throws IOException;

  /**
   * Sets the text editor to work on the image given.
   *
   * @param image the image to be retrieved from the hashmap
   * @return the model, with the image given being represented as the 2D array of pixels
   */
  ImageEditor get(String image);

  /**
   * Getter method that retrieves the number of rows of pixels in the image.
   *
   * @return the number of rows in the image
   */
  int getRows();

  /**
   * Getter method that retrieves the number of cols of pixels in the image.
   *
   * @return the number of cols in the image
   */
  int getCols();

  /**
   * Retrieves a pixel from an image.
   *
   * @return the pixel at a specific position given the row and column
   */
  Pixel getPixel(int row, int col);

  /**
   * Visualizes the red color component of the image by setting all the color values of a pixel
   * equal to the value of its red color for all the pixels in the image.
   *
   * @param newName the new name to name the image to for temporary storage purposes
   */
  void redVisual(String newName);

  /**
   * Visualizes the green color component of the image by setting all the color values of a pixel
   * equal to the value of its green color for all the pixels in the image.
   *
   * @param newName the new name to name the image to for temporary storage purposes
   */
  void greenVisual(String newName);

  /**
   * Visualizes the blue color component of the image by setting all the color values of a pixel
   * equal to the value of its blue color for all the pixels in the image.
   *
   * @param newName the new name to name the image to for temporary storage purposes
   */
  void blueVisual(String newName);

  /**
   * Brightens the image by the given value by adding the value to each color value for every
   * pixel.
   *
   * @param brightener the value by which to brighten by
   * @param newName    the new name of the image for temporary storage purposes
   */
  void brighten(int brightener, String newName);

  /**
   * Darkens the image by the given value by subtracting the value to each color value for every
   * pixel.
   *
   * @param darkener the value by which to darken by
   * @param newName  the new name of the image for temporary storage purposes
   */
  void darken(int darkener, String newName);

  /**
   * Flips the image horizontally.
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void flipHorizontal(String newName);

  /**
   * Flips the image vertically.
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void flipVertical(String newName);

  /**
   * Changes the value of all the colors of a pixel to the color value that is the highest value for
   * all the pixels in an image.
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void setValue(String newName);

  /**
   * Changes the value of all the colors of a pixel to the average of all the color values for all
   * the pixels in an image.
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void setIntensity(String newName);

  /**
   * Changes the value of all the colors of a pixel according to the luma of the pixel for all the
   * pixels in an image. The formula of the luma of an image is (.2126 * redColorValue) + (.7152 *
   * blueColorValue) + (.0722 * greenColorValue).
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void setLuma(String newName);

  /**
   * Retrieves the image, represented as a 2D array, at its current state.
   *
   * @return the image, represented as a 2D array, at its current state
   */
  Pixel[][] retrieveImage();

  /**
   * Applies the given image filter matrix onto the image.
   *
   * @param kernelType the kind of filter
   * @param newName    the new name of the image for temporary storage purposes
   */
  void apply(String kernelType, String newName) throws IllegalArgumentException;

  /**
   * Converts the image into a greyscale image.
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void greyscale(String newName);

  /**
   * Converts the image to have a sepia tone.
   *
   * @param newName the new name of the image for temporary storage purposes
   */
  void sepia(String newName);

  /**
   * Converts the image to PPM format.
   *
   * @return the image in PPM format, as a String
   */
  String toPPM();

  /**
   * Saves the image, chosen by the user, to a specific path in their files, that the user will also
   * choose.
   *
   * @param path the path to which the user chooses to save their chosen image to
   * @param name the name of the image they choose to save
   * @throws IOException when there is a transmission failure.
   */
  void save(String path, String name) throws IOException;

}
