package view;

import java.awt.image.BufferedImage;

/**
 * Interface that creates a histogram. Includes methods for gathering the data for the histogram
 * and actually creating an image for it.
 */
public interface IHistogram {

  /**
   * represents the four different types of histograms as an enum. The four different types are
   * a red histogram, a blue histogram, a green histogram, or an intensity histogram.
   */
  enum HistoType { RED_HISTOGRAM, GREEN_HISTOGRAM, BLUE_HISTOGRAM, INTENSITY_HISTOGRAM }

  /**
   * returns the data for the histogram after taking in the pixel number]
   * and the type of histogram.
   * @param num pixel number.
   * @param type type of histogram, either red, green, blue or intensity.
   * @return the data of the histogram.
   */
  int histoData(int num, HistoType type);

  /**
   * makes the histogram represented as a buffered image.
   * @param type the type of histogram which can either be red, green, blue, or intensity.
   * @return a buffered image of the histogram.
   */
  BufferedImage makeHisto(HistoType type);

}
