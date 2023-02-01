package model;

// Package private class that creates the matrixes of the filters as a 2D array of Doubles and
// the kernels for the blurring and sharpening effect.
class Kernels {

  // the blurring kernel
  Double[][] blurKernel() {
    Double[][] k = {{(.0625), (.125), (.0625)},
        {(.125), (.25), (.125)},
        {(.0625), (.125), (.0625)}};

    return k;
  }

  // the sharpening kernel
  Double[][] sharpenKernel() {
    Double[][] k = {{(-.125), (-.125), (-.125), (-.125), (-.125)},
        {(-.125), (.25), (.25), (.25), (-.125)},
        {(-.125), (.25), (1.00), (.25), (-.125)},
        {(-.125), (.25), (.25), (.25), (-.125)},
        {(-.125), (-.125), (-.125), (-.125), (-.125)}};

    return k;
  }

  // the greyscale matrix
  Double[][] greyscaleKernel() {
    Double[][] k = {{(.2126), (.7152), (.0722)},
        {(.2126), (.7152), (.0722)},
        {(.2126), (.7152), (.0722)}};
    return k;
  }

  // the sepia matrix
  Double[][] sepiaKernel() {
    Double[][] k = {{(.393), (.769), (.189)},
        {(.349), (.686), (.168)},
        {(.272), (.534), (.131)}};
    return k;
  }


}
