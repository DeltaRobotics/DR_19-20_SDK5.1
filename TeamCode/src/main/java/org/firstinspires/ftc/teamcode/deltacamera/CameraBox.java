package org.firstinspires.ftc.teamcode.deltacamera;

import android.graphics.Bitmap;

// Stores information about an analysis area for the camera
public class CameraBox
{
   // Box coordinates
   public  int xMin = 0;
   public  int xMax = 0;
   public  int yMin = 0;
   public  int yMax = 0;

   // Image that contains the area
   public  Bitmap image = null;

   // The RGB average of all the pixels in the area
   public  RGBAverage average;


    // Constructor
    public CameraBox(int xMin, int xMax, int yMin, int yMax, Bitmap image, RGBAverage average)
    {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;

        this.image = image;

        this.average = average;

    }
}
