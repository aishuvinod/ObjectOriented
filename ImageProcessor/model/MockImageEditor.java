package model;


import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Mock version of the ImageEditor class to test that confirms that the controller is passing the
 * inputs through correctly.
 */
public class MockImageEditor implements IModel {

  private final StringBuilder log;

  /**
   * Constucts a default mock for the ImageEditor class which initializes the log/destination as a
   * StringBuilder.
   */
  public MockImageEditor() {
    this.log = new StringBuilder();
  }

  /**
   * Getter method for the log as a string.
   *
   * @return the log as a string
   */
  public String getLog() {
    return this.log.toString();
  }


  @Override
  public void load(String fileName, String newName) throws FileNotFoundException {
    log.append("file name:" + fileName + " " + "new name: " + newName);
  }

  @Override
  public ImageEditor get(String image) {
    return null;
  }

  @Override
  public int getRows() {
    return 0;
  }

  @Override
  public int getCols() {
    return 0;
  }

  @Override
  public Pixel getPixel(int row, int col) {
    return null;
  }

  @Override
  public void redVisual(String newName) {
    return;
  }

  @Override
  public void greenVisual(String newName) {
    return;
  }

  @Override
  public void blueVisual(String newName) {
    return;
  }

  @Override
  public void brighten(int brightener, String newName) {
    return;
  }

  @Override
  public void darken(int darkener, String newName) {
    return;
  }

  @Override
  public void flipHorizontal(String newName) {
    return;
  }

  @Override
  public void flipVertical(String newName) {
    return;
  }

  @Override
  public void setValue(String newName) {
    return;
  }

  @Override
  public void setIntensity(String newName) {
    return;
  }

  @Override
  public void setLuma(String newName) {
    return;
  }

  @Override
  public Pixel[][] retrieveImage() {
    return new Pixel[0][];
  }

  @Override
  public void apply(String kernelType, String newName) throws IllegalArgumentException {
    return;
  }

  @Override
  public void greyscale(String newName) {
    return;
  }

  @Override
  public void sepia(String newName) {
    return;
  }

  @Override
  public String toPPM() {
    return null;
  }

  @Override
  public void save(String path, String name) throws IOException {
    return;
  }
}
