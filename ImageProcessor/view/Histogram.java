package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import model.Pixel;

/**
 * Class that creates a histogram based on an image. Includes methods to gather the data of an
 * image based on its frequency of color values (red, green, blue) and intensity from a scale of 0
 * to 255 and create the actual image of the histrogram with such data included.
 */
public class Histogram extends JPanel implements IHistogram {
  Pixel[][] img;

  Histogram(Pixel[][] image) {
    this.img = image;
  }


  @Override
  public int histoData(int num, HistoType type) {
    int amount = 0;

    try {

      Map<Integer, Integer> redStore = new HashMap<Integer, Integer>();
      Map<Integer, Integer> greenStore = new HashMap<Integer, Integer>();
      Map<Integer, Integer> blueStore = new HashMap<Integer, Integer>();
      Map<Integer, Integer> intensityStore = new HashMap<Integer, Integer>();


      for (int x = 0; x < this.img.length; x = x + 1) {
        for (int y = 0; y < this.img[0].length; y = y + 1) {

          if (type == (HistoType.RED_HISTOGRAM)) {
            redStore.put(img[x][y].getR(), redStore.getOrDefault(img[x][y].getR(),
                0) + 1);
            // redStore.get(255) to get the amount when making the bargraph
          } else if (type == (HistoType.GREEN_HISTOGRAM)) {
            greenStore.put(img[x][y].getG(), greenStore.getOrDefault(img[x][y].getG(),
                0) + 1);
          } else if (type == (HistoType.BLUE_HISTOGRAM)) {
            blueStore.put(img[x][y].getB(), blueStore.getOrDefault(img[x][y].getB(),
                0) + 1);
          } else if (type.equals(HistoType.INTENSITY_HISTOGRAM)) {
            intensityStore.put(img[x][y].getIntensity(),
                intensityStore.getOrDefault(img[x][y].getIntensity(), 0) + 1);
          }
        } // inner loop
      } // outer loop

      if (type == (HistoType.RED_HISTOGRAM)) {
        amount = redStore.get(num); // redStore.get(255) to get the amount when making the bargraph
      } else if (type == (HistoType.GREEN_HISTOGRAM)) {
        amount = greenStore.get(num);
      } else if (type == (HistoType.BLUE_HISTOGRAM)) {
        amount = blueStore.get(num);
      } else if (type == (HistoType.INTENSITY_HISTOGRAM)) {
        amount = intensityStore.get(num);
      }

    } catch (NullPointerException e) {
      e.printStackTrace();
    }
    return amount;
  } // close method

  // type must be one of... HistoType from the above function - test- count the pixel
  @Override
  public BufferedImage makeHisto(HistoType type) {
    BufferedImage histo;

    histo = new BufferedImage(375, 500, BufferedImage.TYPE_INT_RGB);
    Graphics g = histo.createGraphics();

    g.drawLine(25, 460, 500, 460); // x-axis
    g.drawString("color value", 180,490);
    g.drawLine(25, 460, 25, 20); // y- axis
    g.drawString("frequency",10, 10);

    for (int i = 0; i < 256; i = i + 1) {
      int frequency = this.histoData(i, type);
      g.drawLine(i + 25, 460, i + 25, (int) Math.round(460 - (frequency / 1.5)));

      if (type == HistoType.RED_HISTOGRAM) {
        g.setColor(Color.red);
        g.drawString("Red Component", 170, 15);
      }
      else if (type == HistoType.GREEN_HISTOGRAM) {
        g.setColor(Color.green);
        g.drawString("Green Component", 170, 15);
      }
      else if (type == HistoType.BLUE_HISTOGRAM) {
        g.setColor(Color.blue);
        g.drawString("Blue Component", 170, 15);
      }
      else if (type == HistoType.INTENSITY_HISTOGRAM) {
        g.setColor(Color.gray);
        g.drawString("Intensity Component", 170, 15);
      }
    }


    g.dispose();
    return histo;
  } // end of method


} // end of class
