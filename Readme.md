# JGraphics

This package is aimed at making Java more oriented towards creative coding and creating a platform for visual drawing to be created easily

## Version
1.3.0
## Features
This package has a total of 8 accessible classes:
<ul>
<li>Console - This class just simplifies print statements and the console cls function
<li>File - This class eases out File I/O
<li>Utilities -  This class provides utilities such as conversion of angles and mapping of numbers
<li>Vector -  This class provides complete support for 2D euclidean vectors
<li>Keys - This class provides support for keyboard input and some other keyboard related functions
<li>Paint - This class provides support for drawing static images
<li>Graphics - This class provides support for drawing dynamic images
<li>Matrix - This class provides support for matrices and linear transforms of Vectors
</ul>

## Installation and Usage
As of now, the package has to be manually installed by either cloning this repository or downloading the zip file and unzipping it.

A Maven repository will be made available soon.

## License
The project is licensed under the MIT License.

## Special instructions 
<ul>
<li>The package is not yet compatible with JavaFX and works with Swing only.
<li>It is recommended that you use the latest version of Java.
<li>It is recommended that you use both Keys and Graphics together
<li>To use either Paint or Graphics, you should first create a subclass of JGraphics.Paint or JGraphics.Graphics 
    and must then override the setup() and draw() methods.
<li>the setup() method is called once when the program starts and the draw() method is called every time the screen is refreshed.
<li>The draw() method is never called in the Paint class and therefore only exists in the Graphics class.
<li>Vector being 2D, only 2x2 Matrices are allowed for linear transforms.
</ul>