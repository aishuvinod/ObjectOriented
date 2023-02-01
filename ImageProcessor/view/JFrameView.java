package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.IModel;
import model.Pixel;

/**
 * Class whose constructor creates a GUI for the users to be able to visually load, save, and
 * perform operations on an image.
 */
public class JFrameView extends JFrame implements IGUIView {
  private IModel model;
  private final JLabel imgLabel;
  private final JTextField darkenValue;
  private final JTextField brightenValue;
  private final JButton redComponentButton;
  public final JButton greenComponentButton;
  private final JButton blueComponentButton;
  private final JButton darkenButton;
  private final JButton brightenButton;
  private final JButton flipHorizontalButton;
  private final JButton flipVerticalButton;
  private final JButton valueButton;
  private final JButton intensityButton;
  private final JButton lumaButton;
  private final JButton sharpenButton;
  private final JButton blurButton;
  private final JButton sepiaButton;
  private final JButton greyscaleButton;
  private ImageIcon img;
  private final JButton fileOpenButton;
  public final JLabel fileOpenDisplay;
  public final JLabel fileSaveDisplay;
  private final JButton fileSaveButton;
  private BufferedImage hRed;
  private final ImageIcon jRed;
  private JLabel lRed;
  private BufferedImage hGreen;
  private final ImageIcon jGreen;
  private JLabel lGreen;
  private BufferedImage hBlue;
  private final ImageIcon jBlue;
  private JLabel lBlue;
  private BufferedImage hIntensity;
  private final ImageIcon jIntensity;
  private JLabel lIntensity;

  /**
   * Constructor of the JFrameView. Essentially creates the GUI for users to be able to visualize
   * the ImageEditor and its features.
   * @param model the model.
   */
  public JFrameView(IModel model) {
    this.model = model;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window if exited
    this.setLocation(550, 175); // sets the window to open at the middle of the screen
    this.setResizable(true); // user can resize
    this.setMinimumSize(new Dimension(800,600)); // minimum size is set for the window

    // whole panel
    JPanel generalPanel = new JPanel();
    generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

    // top panel - excludes histogram
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(1, 2));
    mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

    // left side of top panel
    JPanel leftSide = new JPanel();
    leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));

    // right side of the top panel
    JPanel rightSide = new JPanel();
    rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));


    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    leftSide.add(dialogBoxesPanel);

    String path;
    dialogBoxesPanel.add(fileopenPanel);
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("load button");
    fileopenPanel.add(fileOpenButton);
    path = "File path will appear here";
    fileOpenDisplay = new JLabel(path);

    fileopenPanel.add(fileOpenDisplay);

    // image
    path = "./res/loadHere.png"; // basic image
    img = new ImageIcon(path);
    imgLabel = new JLabel(img);
    JScrollPane imageScroll = new JScrollPane(imgLabel);
    leftSide.add(imageScroll);

    // save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("save button");
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);

    // edit buttons
    JPanel middleImageWithEdits = new JPanel(new GridLayout(6, 2));
    redComponentButton = new JButton("Red-component");
    greenComponentButton = new JButton("Green-component");
    blueComponentButton = new JButton("Blue-component");
    flipHorizontalButton = new JButton("Flip Horizontally");
    flipVerticalButton = new JButton("Flip Vertically");
    valueButton = new JButton("Value-component");
    intensityButton = new JButton("Intensity-component");
    lumaButton = new JButton("Luma-component");
    sharpenButton = new JButton("Sharpen");
    blurButton = new JButton("Blur");
    sepiaButton = new JButton("Sepia");
    greyscaleButton = new JButton("Greyscale");
    middleImageWithEdits.add(redComponentButton);
    redComponentButton.setActionCommand("red component button");
    middleImageWithEdits.add(greenComponentButton);
    greenComponentButton.setActionCommand("green component button");
    middleImageWithEdits.add(blueComponentButton);
    blueComponentButton.setActionCommand("blue component button");
    middleImageWithEdits.add(flipHorizontalButton);
    flipHorizontalButton.setActionCommand("flip horizontal button");
    middleImageWithEdits.add(flipVerticalButton);
    flipVerticalButton.setActionCommand("flip vertical button");
    middleImageWithEdits.add(valueButton);
    valueButton.setActionCommand("value button");
    middleImageWithEdits.add(intensityButton);
    intensityButton.setActionCommand("intensity button");
    middleImageWithEdits.add(lumaButton);
    lumaButton.setActionCommand("luma button");
    middleImageWithEdits.add(sharpenButton);
    sharpenButton.setActionCommand("sharpen button");
    middleImageWithEdits.add(blurButton);
    blurButton.setActionCommand("blur button");
    middleImageWithEdits.add(sepiaButton);
    sepiaButton.setActionCommand("sepia button");
    middleImageWithEdits.add(greyscaleButton);
    greyscaleButton.setActionCommand("greyscale button");
    rightSide.add(middleImageWithEdits);


    JPanel darkenBrighten = new JPanel(new FlowLayout());
    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("darken button");
    darkenBrighten.add(darkenButton);
    darkenValue = new JTextField(7);
    darkenBrighten.add(darkenValue);
    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("brighten button");
    darkenBrighten.add(brightenButton);
    brightenValue = new JTextField(7);
    darkenBrighten.add(brightenValue);
    rightSide.add(darkenBrighten);

    // histograms
    JPanel histoRow = new JPanel(new FlowLayout());
    hRed = (new Histogram(this.model.retrieveImage()).
        makeHisto(IHistogram.HistoType.RED_HISTOGRAM));
    jRed = new ImageIcon(hRed);
    lRed = new JLabel(jRed);
    histoRow.add(lRed);

    hGreen = (new Histogram(this.model.retrieveImage()).
        makeHisto(IHistogram.HistoType.GREEN_HISTOGRAM));
    jGreen = new ImageIcon(hGreen);
    lGreen = new JLabel(jGreen);
    histoRow.add(lGreen);

    hBlue = (new Histogram(this.model.retrieveImage()).
        makeHisto(IHistogram.HistoType.BLUE_HISTOGRAM));
    jBlue = new ImageIcon(hBlue);
    lBlue = new JLabel(jBlue);
    histoRow.add(lBlue);

    hIntensity = (new Histogram(this.model.retrieveImage()).
        makeHisto(IHistogram.HistoType.INTENSITY_HISTOGRAM));
    jIntensity = new ImageIcon(hIntensity);
    lIntensity = new JLabel(jIntensity);
    histoRow.add(lIntensity);

    // adding panels together
    mainPanel.add(leftSide);
    mainPanel.add(rightSide);
    generalPanel.add(mainPanel);
    generalPanel.add(histoRow);
    this.add(generalPanel);
    this.pack(); // resizes the window so it only uses the space needed for its components
    // - don't need to think about calculations of where everything needs to go
    this.setVisible(true); // makes the window visible
  }

  @Override
  public int getDarkenInput() {
    return Integer.parseInt(this.darkenValue.getText());
  }

  @Override
  public int getBrightenInput() {
    return Integer.parseInt(this.brightenValue.getText());
  }

  @Override
  public void setImage() throws IOException {
    Pixel[][] imge = this.model.retrieveImage();
    int height = imge[0].length;
    int width = imge.length;

    BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < height; x = x + 1) {
      for (int y = 0; y < width; y = y + 1) {
        Pixel colors = imge[y][x];

        Color newColor = new Color(colors.getR(), colors.getG(), colors.getB());
        buffer.setRGB(y, x, newColor.getRGB());

      }
    }

    img = new ImageIcon(buffer);
    imgLabel.setIcon(img);
  }

  @Override
  public void setListener(ActionListener listener) {
    redComponentButton.addActionListener(listener);
    greenComponentButton.addActionListener(listener);
    blueComponentButton.addActionListener(listener);
    flipHorizontalButton.addActionListener(listener);
    flipVerticalButton.addActionListener(listener);
    valueButton.addActionListener(listener);
    intensityButton.addActionListener(listener);
    lumaButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    darkenButton.addActionListener(listener);
    brightenButton.addActionListener(listener);
    fileOpenButton.addActionListener(listener);
    fileSaveButton.addActionListener(listener);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public void updateHistogram(Pixel[][] image) {
    hRed = (new Histogram(image).makeHisto(IHistogram.HistoType.RED_HISTOGRAM));
    jRed.setImage(hRed);
    lRed = new JLabel(jRed);

    hGreen = (new Histogram(image).makeHisto(IHistogram.HistoType.GREEN_HISTOGRAM));
    jGreen.setImage(hGreen);
    lGreen = new JLabel(jGreen);

    hBlue = (new Histogram(image).makeHisto(IHistogram.HistoType.BLUE_HISTOGRAM));
    jBlue.setImage(hBlue);
    lBlue = new JLabel(jBlue);

    hIntensity = (new Histogram(image).makeHisto(IHistogram.HistoType.INTENSITY_HISTOGRAM));
    jIntensity.setImage(hIntensity);
    lIntensity = new JLabel(jIntensity);
  }

  /**
   * an info box for the user that pops up when the user tries to perform an invalid operation.
   * @param infoMessage the message that will be shown to the user.
   * @param titleBar the title bar of the message shown.
   */
  public void infoBox(String infoMessage, String titleBar) {
    JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar,
        JOptionPane.INFORMATION_MESSAGE);
  }
}
