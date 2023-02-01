package view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.WindowConstants;
import model.Pixel;

/**
 * Interface that provides methods for the view of the GUI. Methods include retrieving text from
 * text boxes, changing the image shown, setting the GUI to be visible, giving each button a
 * listener, etc...
 */
public interface IGUIView extends WindowConstants {

  /**
   * Retrieves the number, in the form of a String, inputted from the text field that corresponds
   * to the darken button.
   */
  int getDarkenInput();

  /**
   * Retrieves the number, in the form of a String, inputted from the text field that corresponds
   * to the brighten button.
   */
  int getBrightenInput();

  /**
   * Sets the image shown to the current one after loading or performing an operation on the
   * current image.
   */
  void setImage() throws IOException;

  /**
   * Adds an ActionListener to every button in the constructor so each button knows
   * when it is called to perform its corresponding actions.
   * @param listener the listener to be added to each button
   */
  void setListener(ActionListener listener);

  /**
   * Sets the view to be visible.
   */
  void display();

  /**
   * Updates the histogram after any edits have been done to any image or if a new one has been
   * loaded.
   * @param image the current image to which to update the histogram by
   */
  void updateHistogram(Pixel[][] image);
}
