package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.IModel;
import model.ImageEditor;
import model.Pixel;
import view.IView;
import view.TextView;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Pixel[][] readPPM(String filename) throws FileNotFoundException {
    Scanner sc;
    sc = new Scanner(new FileInputStream(filename));

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }

    StringBuilder builder = new StringBuilder();

    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (!s.equals("") && s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();

    Pixel[][] pix = new Pixel[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        // singular pixel
        Pixel p = new Pixel(j, i, r, g, b);
        pix[j][i] = p;
      }
    }
    return pix;
  }

  /**
   * Allows conventional file formats like jpeg, bmp, png, etc to be loaded and read.
   *
   * @param filename the given file name
   * @return each pixel in the image
   * @throws IOException if there is an error in loading and reading the file at any point
   */
  public static Pixel[][] read(String filename) throws IOException {
    File file = new File(filename); // takes in file
    BufferedImage image = ImageIO.read(file); // read the file

    // gets the pixels as width and height
    Pixel[][] dimension = new Pixel[image.getWidth()][image.getHeight()];

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) { // go through image

        int pixel = image.getRGB(j, i); //get the rgb values
        Color color = new Color(pixel, true); // make a new color object
        //Retrieving the R G B values
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        Pixel pix = new Pixel(j, i, red, green, blue); // make a new pixel with the rgb values
        dimension[j][i] = pix; // pixel with previous rgb values
      }
    }
    return dimension;
  }


  /**
   * Runs the program by letting the user enter commands to edit their image.
   *
   * @param args stores the inputs as an arraylist of strings
   * @throws IOException when any sort of transmission has failed
   */
  public static void main(String[] args) throws IOException {

    IModel model = new ImageEditor();
    IView view = new TextView(model, System.out);
    IController controller = new TextController(model, view, new InputStreamReader(System.in));
    controller.imageProcessor();
  }
}

