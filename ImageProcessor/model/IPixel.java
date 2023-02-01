package model;

/**
 * Interface representing a pixel in an image which is represented by a 2D array. This interface
 * contains getters to retrieve components of the pixel, such as its position in the array and
 * color values.
 */
public interface IPixel {

  /**
   * Getter method which retrieves the x position of a singular pixel.
   *
   * @return the x value of the position of a singular pixel
   */
  int getX();

  /**
   * Getter method which retrieves the y position of a singular pixel.
   *
   * @return the y value of the position of a singular pixel
   */
  int getY();

  /**
   * Getter method which retrieves the red color value of a singular pixel.
   *
   * @return the red color value of the position of a singular pixel
   */
  int getR();

  /**
   * Getter method which retrieves the green color value of a singular pixel.
   *
   * @return the green color value of the position of a singular pixel
   */
  int getG();

  /**
   * Getter method which retrieves the blue color value of a singular pixel.
   *
   * @return the blue color value of the position of a singular pixel
   */
  int getB();

  /**
   * Determines the intensity of a pixel which is essentially the average of the color values (red,
   * green, and blue).
   * @return the intensity of a pixel
   */
  int getIntensity();
}
