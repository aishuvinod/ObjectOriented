package controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


import model.IModel;
import view.IView;



/**
 * Represents the TextController class which implements the IController interface and has the
 * model, view, and reader fields. Has the method imageProcessor to take in user input.
 */
public class TextController implements IController {

  private final IModel model;
  private final IView view;
  private final Readable reader;

  /**
   * Constructs the text controller which initializes the model, view and reader.
   *
   * @param model  the given model of the image editor
   * @param view   the given display of the image
   * @param reader the readable reader
   * @throws IllegalArgumentException if either the model, view, or reader is null
   */
  public TextController(IModel model, IView view, Readable reader) throws IllegalArgumentException {
    if (model == null || view == null || reader == null) {
      throw new IllegalArgumentException("The model nor the view cannot be null");
    }

    this.model = model;
    this.view = view;
    this.reader = reader;
  }


  /**
   * Process the image by loading the image in, checking for any functions that the client wants
   * to apply to the image, and finally saving the image to their files if they wish to do so.
   *
   * @throws IllegalArgumentException if any of the inputs the user enters is invalid
   * @throws IOException              when the board or the message output transmission has failed
   */
  @Override
  public void imageProcessor() throws IllegalArgumentException, IOException {
    Scanner scan = new Scanner(reader);

    String input = scan.next(); // what the user puts in first
    String inputPath = scan.next();
    String newName = scan.next(); // new name given

    if (input.equals("load")) {
      try {
        this.model.load(inputPath, newName);
        successMessageAndBoard();
      } catch (FileNotFoundException e) {
        failMessage();
      }
    } else {
      failMessage();
    }

    while (scan.hasNext()) {
      String argAfterLoad = scan.next();

      if (argAfterLoad.equals("vertical-flip")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).flipVertical(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("load")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.load(name, newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("red-component")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).redVisual(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("green-component")) {
        try {
          String name = scan.next();

          String newName1 = scan.next();
          this.model.get(name).greenVisual(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("blue-component")) {
        try {
          String name = scan.next();

          String newName1 = scan.next();
          this.model.get(name).blueVisual(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("brighten")) {
        try {
          String name = scan.next();
          String oldName = scan.next();
          String newName1 = scan.next();
          this.model.get(oldName).brighten(Integer.parseInt(name), newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("darken")) {
        try {
          String name = scan.next();
          String oldName = scan.next();
          String newName1 = scan.next();
          this.model.get(oldName).darken(Integer.parseInt(name), newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("horizontal-flip")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).flipHorizontal(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("value-component")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).setValue(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("intensity-component")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).setIntensity(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("luma-component")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).setLuma(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("blur")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).apply("blur", newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("sharpen")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).apply("sharpen", newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("greyscale")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).greyscale(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      } else if (argAfterLoad.equals("sepia")) {
        try {
          String name = scan.next();
          String newName1 = scan.next();
          this.model.get(name).sepia(newName1);
          successMessageAndBoard();
        } catch (IllegalArgumentException e) {
          failMessage();
        }
      }


      // saving
      else if (argAfterLoad.equals("save")) {
        try {

          String name = scan.next();
          this.model.save(name, newName);
          successMessageAndBoard();

        } catch (IllegalArgumentException e) {
          failMessage();
        }
        String saveVersion = scan.next();
      }

    }

  }


  // outputs a message to the user if the task they inputted was executed successfully and
  // throws an IO Exception if it has failed
  private void successMessageAndBoard() throws IOException {
    view.renderMessage("Successfully completed operation!\n");
    view.renderImage();
  }

  // outputs a message to the user if the task they inputted was not executed successfully and
  // throws an IO Exception
  private void failMessage() throws IOException {
    view.renderMessage("Operation attempt failed. Please try again.\n");
  }

}


