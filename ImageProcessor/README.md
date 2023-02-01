



PROGRAM USES

- Our image editor program can be used to load in different images that are of bmp, png, ppm, jpg,
and jpeg file formats.
- When the program is opened up, the user can load in an image by clicking the “open a file” button
which will take the user to their local file folder.
- The selected image by the user will show up on the image panel initially labeled
“load image here”.
- The user can then decide to apply whichever operation they would like to apply using the buttons
on the right hand side of the GUI.
- The operations available to the user include flipping vertically, flipping horizontally,
sharpening, blurring, getting the red, blue, or green components, getting the value, intensity,
or luma components, applying the sepia filter, applying the greyscale filter, darkening, and
brightening.
- For darkening and brightening, the user can enter a number by which they would like to darken or
 brighten their image by into the text fields corresponding to the darken and brighten buttons.
- The image on the image panel will update to the new image with the applied operation and the
histograms will update to provide new data according to the newly edited and displayed image
which shows the red, blue, green, and intensity components of each image.
- The user can save the current displayed image. The user can click the “save a file” button in
order to pick where the image is being saved.
- The user will receive an informational message through a popup when they enter an incorrect/
invalid input.
- Some additional features provided by our program is the ability to resize the window,
scroll an image, and exit when the user hits X.

Note
* most private and protected methods, fields, and classes are not exposed in this document
 We made a model, controller, and view as separate packages inside the source
 folder so that to ensure loose coupling and preserve the MVC pattern.

If the user would like to add more functionality, they can make new interfaces that extend or
implement the IModel, IView, or IController interfaces and create classes that implement their new
interface.
The methods included are:
1. int histoData(int num, Histotype type); - collects, stores, and returns the data regarding the
frequency of the specified color value (int num)
2. BufferedImage makeHisto(HistoType type); - creates the histogram based on the data provided by
int histoData(int num, Histotype type) as a BufferedImage to be visible on the GUI
- We added a Histogram class which extends JPanel and implements IHistogram. This class extends
JPanel so that it can be displayed as a separate contained panel in the GUI.
- The histogram stores the red, blue, green, and intensity data in a Hashmap which maps from
integer to integer and displays it on a graph.
- We added an IGUIView interface which extends WindowConstants. This interface contains methods in
order to alter features in the GUI based on user actions.
The methods included in this class are:
1. getDarkenInput( ); -  returns the integer input for the darken operation from the text bar
2. getBrightenInput( ); -  returns the integer input for the brighten operation from the text bar
3. setImageLoad(String f); loads in the image as a buffered image.
4. setImageOp(String s); changes the image after an operation has been done
5. setListener(ActionListener listener); which adds an action listener to every button
6. display( ); helper which sets a component to be visible
7. updateHistogram(Pixel[ ][ ] image); updates the histogram based on the displayed image


- We added a JFrameView class which implements the IGUIView class and extends JFrame.
- This class extends JFrame so that it can use its inbuilt features. This class is not tested since
 java Swing tests itself.
- All the fields in this class which include panels, text fields, and buttons are private final
since its functionality does not change at any point throughout the program and the fields do not
need access elsewhere.
- In the construtor, we build the GUI by making a main grid layout panel which has a left side panel
 and right side panel, both in a box layout, added to it. The left side panel contains the image
 and the right side contains the buttons. The histogram is added to a flow layout panel on the
 bottom of the GUI window.
- We have a void infoBox(String infoMessage, String titleBar); method which is used to produce
popup messages when there is an invalid entry by the user.

Controller
- We added a GUIController class which implements the ActionListener interface.
- This controller takes in the model and the view (JFrameView) in order to receive input from the
user and use that information to retrieve information from the model and display a result through
the view.

Model
Classes and Interfaces:

IPixel
We have an IPixel interface which designs all the methods we need to access a Pixel from an image
and work with it.




Pixel
In our Pixel class, we define what a Pixel is and all of its components. We have getters in this
class since we use it to access the position and red, green, and blue components in other methods.
We have setters to set either all of the colors of each pixel to the same value or different ones.




IModel
We have the IModel interface which designs the image editor and provides functions that allows the
user to edit their image (ImageEditor class).



ImageEditor
We have the ImageEditor class which implements the IModel interface. We have getters for the
purpose of allowing other methods to access the amount of rows and columns. All operations to
edit images are displayed in this class.

We added a void apply method which applies a given filter board to an image and produces a new image
with the filter on it which is stored in the hashmap
If a user wants to add more functionality beyond blur and sharpen, they can add it on as an else if
statement inside the apply method. The apply method is public, so the user can do this in their own
implementation of the method, if they wish to do so.
A greyscale method which takes in a string name for the new file to be named as.
This method applies the greyscale filter to a given image.
A sepia method which takes in a string name for the new file to be named as.
This method applies the greyscale filter to a given image.

These methods were added into the IModel interface since they are used to enhance the image.
We chose not to create a new interface for these methods since our code is not closed to
modifications by us, the creators. This class is open to being extended by third party users.


We have a package private kernels class which has no access modifiers because the kernel matrixes
do not need to be accessed by the client and only the model needs private access to the kernel
values within the apply method.

View

IView
Interface which designs the view outputted to the user by the controller.

TextView
This class represents the methods that allow the controller to output a view of the model to the
user. We have two constructors where one takes in the model and the second one takes in the model
and the destination. An illegal argument exception is thrown if the model or destination is null.



Controller

IController
Interface which designs the controller for the image processor.

TextController
This class reads user inputs and transmits the input to the model and the output is displayed
through the view. It performs the allocated method as called by the user. The constructor for
TextController throws an Illegal argument exception when the model, view or reader is null.

public void imageProcessor( )  throws IllegalArgumentException, IOException;
The ImageProcessor method takes in an input from the user, reads it, carries out the required
operation, and outputs a message that tell the user whether their attempted operation worked
successfully or not.

Image Util
Implements the command line usability by the user through the main method and also ensures that any
image inputted by the user to be operated on is in ppm format through the readPPM method. ReadPPM
determines if the given file is in PPM format and it also pulls out all the pixels from the image
PPM and creates a 2D array of pixels to represent the image.



Our command line can run a variety of inputs including loading, saving, a vertical flip, a
horizontal flip, brightening, darkening, getting the intensity, luma, or value, and getting the
red component, blue component, or green component.
Some added features also include being able to blur or sharpen the image and also apply either a
sepia or greyscale filter to the image. You can run these script commands in the command line set
up through the main method to run these functions.
All of these operations can be applied to ppm, jpg, png, and bpm image types.
Our program supports loading an image as one image type and saving it as another




RED COMPONENT
load  ./res/colorblock.ppm cb
red-component  cb red
save ./res/colorblock-red.ppm red

GREEN COMPONENT
load  ./res/colorblock.ppm cb
green-component  cb green
save ./res/colorblock-green.ppm green


BLUE COMPONENT
load  ./res/colorblock.ppm cb
blue-component  cb blue
save ./res/colorblock-blue.ppm blue


LUMA COMPONENT
load  ./res/colorblock.ppm cb
luma-component  cb luma
save ./res/colorblock-luma.ppm luma


INTENSITY COMPONENT
load  ./res/colorblock.ppm cb
intensity-component  cb intensity
save ./res/colorblock-intensity.ppm intensity


VALUE COMPONENT
load  ./res/colorblock.ppm cb
value-component  cb value
save ./res/colorblock-value.ppm value


VERTICAL FLIP
load  ./res/colorblock.ppm cb
vertical-flip  cb vflip
save ./res/colorblock-vertical.ppm vflip

HORIZONTAL FLIP
load  ./res/colorblock.ppm cb
horizontal-flip  cb hflip
save ./res/colorblock-horizontal.ppm hflip




BRIGHTEN
load  ./res/colorblock.ppm cb
brighten 20 cb brighten
save ./res/colorblock-brightened.ppm brighten



DARKEN
load  ./res/colorblock.ppm cb
darken 20 cb darken
save ./res/colorblock-darkened.ppm darken


BLUR
load ./res/googleSMALL.jpg j
blur j j-blur
save ./res/google-sharpen.jpg j-sharpen

SHARPEN
load ./res/googleSMALL.jpg j
sharpen j j-sharpen
save ./res/google-sharpen.jpg j-sharpen

SEPIA
load ./res/googleSMALL.jpg j
sepia j j-sepia
save ./res/google-sepia.jpg j-sepia

GREYSCALE
load ./res/googleSMALL.jpg j
greyscale j j-greyed
save ./res/google-greyscale.jpg j-greyed







