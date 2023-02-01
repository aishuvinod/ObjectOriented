package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import static controller.ImageUtil.read;
import static controller.ImageUtil.readPPM;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Class representing an image editor that can perform a number of editing functions on an image.
 */
public class ImageEditor implements IModel {
  private int rows;
  private int cols;
  private Pixel[][] state;

  private Map<String, Pixel[][]> store; // storing the image

  /**
   * Constructs a default image editor.
   */
  public ImageEditor() {
    this.rows = rows;
    this.cols = cols;
    this.state = new Pixel[rows][cols];
    this.store = new HashMap<>();
  }

  @Override
  public void load(String fileName, String newName) throws IOException {
    Pixel[][] imageConverter;

    if (fileName.contains(".ppm")) {
      imageConverter = readPPM(fileName);
      store.put(newName, imageConverter); //temporary keeping of image
      // setting the model since it is null before loading
      this.state = imageConverter;
      this.rows = imageConverter.length;
      this.cols = this.state[0].length;

    } else if (fileName.contains(".jpg") || fileName.contains(".jpeg")
        || fileName.contains(".bmp") || fileName.contains(".png")) {
      imageConverter = read(fileName);
      store.put(newName, imageConverter); //temporary keeping of image
      // setting the model since it is null before loading
      this.state = imageConverter;
      this.rows = imageConverter.length;
      this.cols = this.state[0].length;
    }

  }

  public Pixel[][] retrieveImage() {
    return this.state;
  }

  @Override
  public ImageEditor get(String image) throws IllegalArgumentException {
    if (store.containsKey(image)) {
      Pixel[][] imageRep = store.get(image);
      this.state = imageRep;
    } else {
      throw new IllegalArgumentException("Image not found!");
    }
    return this;
  }

  @Override
  public int getRows() {
    return this.rows;
  }

  @Override
  public int getCols() {
    return this.cols;
  }

  @Override
  public Pixel getPixel(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row > rows - 1 || col > cols - 1) {
      throw new IllegalArgumentException("This position is beyond the scopes of the image");
    }

    return this.state[row][col];
  }

  @Override
  public void redVisual(String newName) {
    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {

        int redValue = this.state[r][c].getR();
        this.state[r][c].setAllColors(redValue);
      }
    }

    store.put(newName, this.state);

  }

  @Override
  public void greenVisual(String newName) {
    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {

        int greenValue = this.state[r][c].getG();
        this.state[r][c].setAllColors(greenValue);
      }
    }

    store.put(newName, this.state);
  }

  @Override
  public void blueVisual(String newName) {
    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {

        int blueValue = this.state[r][c].getB();
        this.state[r][c].setAllColors(blueValue);
      }
    }

    store.put(newName, this.state);
  }

  @Override
  public void brighten(int brightener, String newName) throws IllegalArgumentException {
    if (brightener < 0) {
      throw new IllegalArgumentException("Brightening level cannot be negative.");
    }

    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {

        int rVal = this.state[r][c].getR();
        int bVal = this.state[r][c].getB();
        int gVal = this.state[r][c].getG();

        rVal = rVal + brightener;
        if (rVal > 255) {
          rVal = 255;
        }

        bVal = bVal + brightener;
        if (bVal > 255) {
          bVal = 255;
        }

        gVal = gVal + brightener;
        if (gVal > 255) {
          gVal = 255;
        }

        this.state[r][c].setNewColors(rVal, gVal, bVal);
      }
    }
    store.put(newName, this.state);
  }

  @Override
  public void darken(int darkener, String newName) throws IllegalArgumentException {
    if (darkener < 0) {
      throw new IllegalArgumentException("Darkening level cannot be negative.");
    }

    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {

        int rVal = this.state[r][c].getR();
        int bVal = this.state[r][c].getB();
        int gVal = this.state[r][c].getG();


        rVal = rVal - darkener;
        if (rVal < 0) {
          rVal = 0;
        }

        bVal = bVal - darkener;
        if (bVal < 0) {
          bVal = 0;
        }

        gVal = gVal - darkener;
        if (gVal < 0) {
          gVal = 0;
        }

        this.state[r][c].setNewColors(rVal, gVal, bVal);
      }
    }
    store.put(newName, this.state);
  }

  @Override
  public void flipVertical(String newName) {
    Pixel[][] newImage = new Pixel[this.rows][this.cols];
    Pixel p = new Pixel();

    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {
        int newY = (this.cols - 1 - c);
        p = this.state[r][newY];
        newImage[r][c] = p;

      }
    }

    this.state = newImage;
    store.put(newName, this.state);

  }

  @Override
  public void flipHorizontal(String newName) {
    Pixel[][] newImage = new Pixel[this.rows][this.cols];
    Pixel p;

    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {
        int newX = (this.rows - 1 - r);
        p = this.state[newX][c];
        newImage[r][c] = p;

      }
    }
    this.state = newImage;
    store.put(newName, this.state);
  }


  @Override
  public void setValue(String newName) {
    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {
        //MAYBE MAKE AS HELPER?????
        int rVal = this.state[r][c].getR();
        int bVal = this.state[r][c].getB();
        int gVal = this.state[r][c].getG();

        int halfMax = Math.max(rVal, bVal);
        int realMax = Math.max(gVal, halfMax);

        this.state[r][c].setAllColors(realMax);
      }
    }

    store.put(newName, this.state);

  }

  @Override
  public void setIntensity(String newName) {
    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {
        int rVal = this.state[r][c].getR();
        int bVal = this.state[r][c].getB();
        int gVal = this.state[r][c].getG();

        int average = ((rVal + bVal + gVal) / 3);

        this.state[r][c].setAllColors(average);
      }
    }
    store.put(newName, this.state);

  }

  @Override
  public void setLuma(String newName) {
    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {
        int rVal = this.state[r][c].getR();
        int bVal = this.state[r][c].getB();
        int gVal = this.state[r][c].getG();

        int luma = (int) Math.round((.2126 * rVal) + (.7152 * bVal) + (.0722 * gVal));
        this.state[r][c].setAllColors(luma);
      }
    }
    store.put(newName, this.state);
  }

  @Override
  public void apply(String kernelType, String newName) throws IllegalArgumentException {
    Kernels kern = new Kernels();
    Double[][] kBoard = new Double[0][0];

    if (kernelType.equals("blur")) {
      kBoard = kern.blurKernel();

    } else if (kernelType.equals("sharpen")) {
      kBoard = kern.sharpenKernel();
    } else {
      throw new IllegalArgumentException("kernelType does not exist");
    }

    Pixel[][] copyState = new Pixel[this.rows][this.cols];

    // image loops
    for (int r = 0; r < this.rows; r = r + 1) {
      for (int c = 0; c < this.cols; c = c + 1) {

        float red = 0;
        float green = 0;
        float blue = 0;

        // kernel loops
        for (int x = 0; x < kBoard.length; x++) {
          for (int y = 0; y < kBoard[0].length; y++) {

            int iR = (r - (kBoard.length / 2)) + x;
            int iC = (c - (kBoard[0].length / 2)) + y;

            try {
              red += this.state[iR][iC].getR() * kBoard[x][y];
              green += this.state[iR][iC].getG() * kBoard[x][y];
              blue += this.state[iR][iC].getB() * kBoard[x][y];
            } catch (ArrayIndexOutOfBoundsException a) {
              a.printStackTrace();
            }
          }
        }

        if (red > 255) {
          red = 255;
        }
        if (green > 255) {
          green = 255;
        }
        if (blue > 255) {
          blue = 255;
        }
        if (red < 0) {
          red = 0;
        }
        if (green < 0) {
          green = 0;
        }
        if (blue < 0) {
          blue = 0;
        }


        copyState[r][c] = new Pixel(r, c, Math.round(red), Math.round(green), Math.round(blue));
      }
    }
    store.put(newName, copyState);
    this.state = copyState;
  }


  // multiplies the respective values from the matrixs onto the image color values
  // and sets them into a new pixel to create a new version of the image with a color image
  private Pixel[][] colorTransformation(Double[][] factors) {
    Pixel[][] copyState = new Pixel[this.rows][this.cols];

    for (int c = 0; c < this.cols; c = c + 1) {
      for (int r = 0; r < this.rows; r = r + 1) {


        int newR = (int) (Math.round((factors[0][0] * this.state[r][c].getR()) + (factors[0][1]
            * this.state[r][c].getG()) + (factors[0][2] * this.state[r][c].getB())));
        if (newR > 255) {
          newR = 255;
        }

        int newG = (int) (Math.round((factors[1][0] * this.state[r][c].getR()) + (factors[1][1]
            * this.state[r][c].getG()) + (factors[1][2] * this.state[r][c].getB())));
        if (newG > 255) {
          newG = 255;
        }

        int newB = (int) (Math.round((factors[2][0] * this.state[r][c].getR()) + (factors[2][1]
            * this.state[r][c].getG()) + (factors[2][2] * this.state[r][c].getB())));
        if (newB > 255) {
          newB = 255;
        }

        copyState[r][c] = new Pixel(r, c, newR, newG, newB);
      }
    }
    this.state = copyState;
    return copyState;
  }

  @Override
  public void greyscale(String newName) {
    Kernels k = new Kernels();
    Pixel[][] colorTransform = colorTransformation(k.greyscaleKernel());
    this.store.put(newName, colorTransform);

  }

  @Override
  public void sepia(String newName) {
    Kernels k = new Kernels();
    Pixel[][] colorTransform = colorTransformation(k.sepiaKernel());
    this.store.put(newName, colorTransform);
  }

  //ADDED- FROM VIEW - ALSO STILL IN VIEW - keep in original view for text ui support
  @Override
  public String toPPM() {
    StringBuilder image = new StringBuilder();
    String ppmHeader = "P3\n" + "#\n" + this.getRows() + " " + this.getCols()
        + " 255\n";
    image.append(ppmHeader);

    for (int c = 0; c < this.getCols(); c = c + 1) {
      for (int r = 0; r < this.getRows(); r = r + 1) {

        Pixel p = this.getPixel(r, c);
        String pixelSingle = p.getR() + "\n" + p.getG() + "\n" + p.getB() + "\n";

        image.append(pixelSingle);
      }
    }
    return image.toString() + "\n";
  }

  //ADDED- REMOVED FROM CONTROLLER
  @Override
  public void save(String path, String name) throws IOException {
    Pixel[][] img = this.get(name).retrieveImage(); // the image to be saved

    if (path.contains(".ppm")) {
      Files.writeString(Paths.get(path), this.toPPM(), CREATE);
    }

    if (path.contains(".jpg") || (path.contains(".jpeg") || (path.contains(".png")) ||
        (path.contains(".bmp")))) {
      int height = img[0].length;
      int width = img.length;

      BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) {

          Pixel colors = img[y][x];

          Color newColor = new Color(colors.getR(), colors.getG(), colors.getB());
          buffer.setRGB(y, x, newColor.getRGB());

        }
      }

      File output = new File(path);
      String fileType = "";

      if (path.contains(".jpeg")) {
        fileType = "jpeg";
      } else if (path.contains(".png")) {
        fileType = "png";
      } else if (path.contains(".bmp")) {
        fileType = "bmp";
      } else if (path.contains(".jpg")) {
        fileType = "jpg";
      }

      ImageIO.write(buffer, fileType, output);

    }
  }

}
