package view;

import java.io.IOException;

/**
 * Mock version of the TextView class to test that confirms that
 * the controller is passing the inputs through correctly.
 */
public class TextViewMock implements IView {
  private final StringBuilder log;

  /**
   * Constucts a default mock for the ImageEditor class which initializes
   * the log/destination as a StringBuilder.
   */
  public TextViewMock() {
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
  public String visualize() {
    return "";
  }

  @Override
  public String toPPM() {
    return "";
  }

  @Override
  public void renderImage() throws IOException {
    return;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(message);
  }
}
