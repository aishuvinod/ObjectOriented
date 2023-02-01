package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.IModel;
import view.JFrameView;

/**
 * Represents the controller for the GUI. Takes in user action/input, and displays the result
 * through the view by communicating with the model.
 */
public class GUIController implements ActionListener {

  private IModel model;
  private JFrameView view;

  /**
   * constructor for the controller which takes in the model and the view.
   *
   * @param m the model.
   * @param v the view.
   */
  public GUIController(IModel m, JFrameView v) {
    model = m;
    view = v;
    view.setListener(this);
    view.display();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    // determines which button is pressed
    switch (e.getActionCommand()) {
      case "load button":

        final JFileChooser fchooser = new JFileChooser("./User/");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Images", "jpg", "gif", "png", "bmp", "jpeg", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(this.view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          view.fileOpenDisplay.setText(f.getAbsolutePath());
          try {
            this.model.load(f.getAbsolutePath(), "");
            view.setImage();
            view.updateHistogram(this.model.retrieveImage());
            view.repaint();
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        }

        break;

      case "save button":
        final JFileChooser fchooserSave = new JFileChooser(".");
        int retvalueSave = fchooserSave.showSaveDialog(this.view);
        if (retvalueSave == JFileChooser.APPROVE_OPTION) {
          File f = fchooserSave.getSelectedFile();
          view.fileSaveDisplay.setText(f.getAbsolutePath());
          try {
            this.model.save(f.getAbsolutePath(), "");
          } catch (IOException ex) {
            ex.printStackTrace();
          }
        }

        break;

      case "red component button":
        // if button was pressed then tell the controller
        this.model.redVisual("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "green component button":
        this.model.greenVisual("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "blue component button":
        this.model.blueVisual("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "flip horizontal button":
        this.model.flipHorizontal("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }

        break;

      case "flip vertical button":
        this.model.flipVertical("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "value button":
        this.model.setValue("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "intensity button":
        this.model.setIntensity("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "luma button":
        this.model.setLuma("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "sharpen button":
        this.model.apply("sharpen", "");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "blur button":
        this.model.apply("blur", "");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "sepia button":
        this.model.sepia("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }

        break;

      case "greyscale button":
        this.model.greyscale("");
        try {
          this.view.setImage();
          this.view.repaint();
          view.updateHistogram(this.model.retrieveImage());
          view.repaint();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;

      case "darken button":

        try {
          this.model.darken(view.getDarkenInput(), "");

          try {
            this.view.setImage();
            this.view.repaint();
            view.updateHistogram(this.model.retrieveImage());
            view.repaint();
          } catch (IllegalArgumentException | IOException ex) {
            ex.printStackTrace();
          }
        } catch (IllegalArgumentException e1) {
          view.infoBox("please enter a positive integer only",
              "How To Darken");
        }
        break;

      case "brighten button":

        try {
          this.model.brighten(view.getBrightenInput(), "");

          try {
            this.view.setImage();
            this.view.repaint();
            view.updateHistogram(this.model.retrieveImage());
            view.repaint();
          } catch (IllegalArgumentException | IOException ex) {
            ex.printStackTrace();

          }
        } catch (IllegalArgumentException e1) {
          view.infoBox("please enter a positive integer only", "How To Brighten");
        }
        break;
      default:
        System.out.println("error");
    }


  }


} // end of class
