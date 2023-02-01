package model;

/**
 * Class representing a singular pixel in an image.
 */
public class Pixel implements IPixel {
  private int x;
  private int y;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor that creates a default pixel of an image.
   */
  Pixel() {
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  Pixel(int xCord, int yCord) {
    this.x = xCord;
    this.y = yCord;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Constructor that sets all the values of a pixel according to its location within the image and
   * its color values. Used to create the 2D array of pixels to represent an image after it is read
   * by the method readPPM(String filename).
   *
   * @param x the x value of the position of the pixel with respect to the image as a whole
   * @param y the y value of the position of the pixel with respect to the image as a whole
   * @param r the red color value of the pixel
   * @param g the green color value of the pixel
   * @param b the blue color value of the pixel
   */
  public Pixel(int x, int y, int r, int g, int b) throws IllegalArgumentException {
    if (x < 0 || y < 0 || r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("No pixel values can be negative nor can the color" +
          "values be larger than 255");
    }
    this.x = x;
    this.y = y;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }

  // sets all of the colors of a pixel to the same given number
  protected void setAllColors(int sameNum) throws IllegalArgumentException {
    if (sameNum < 0 || sameNum > 255) {
      throw new IllegalArgumentException("Color values of a pixel must not be negative or larger" +
          "than 255.");
    }
    r = sameNum;
    g = sameNum;
    b = sameNum;
  }

  // sets all the colors of a pixel to the new given values
  protected void setNewColors(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("Color values of a pixel must not be negative or larger" +
          "than 255.");
    }
    r = red;
    g = green;
    b = blue;
  }

  // NEW ADDED
  @Override
  public int getIntensity() {
    return ((r + g + b) / 3);
  }


}
