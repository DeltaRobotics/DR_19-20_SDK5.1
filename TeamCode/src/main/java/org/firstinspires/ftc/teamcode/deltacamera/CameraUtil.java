package org.firstinspires.ftc.teamcode.deltacamera;
import android.graphics.Bitmap;
import android.graphics.Color;

import for_camera_opmodes.LinearOpModeCamera;

// This classes purpose is to simplify basic actions with the existing camera code
public class CameraUtil
{

   // Stores averages for R G and B values
   private int redAverage = -76800;
   private int blueAverage = -76800;
   private int greenAverage = -76800;

   // Local instance of Camera Class
   private LinearOpModeCamera camera;

    // Constructor
    public CameraUtil(LinearOpModeCamera camera)
    {
        this.camera = camera;
    }

    // Starts the camera
    public void startCamera()
    {
        camera.startCamera();
    }

    // Stops the camera
    public void stopCamera()
    {
        camera.stopCamera();
    }

    // Saves the Bitmap image given
    public void saveImage(Bitmap rgbImage)
    {
        camera.SaveImage(rgbImage);
    }

    // Takes a Bitmap picture - Returns a Bitmap object
    public Bitmap takePicture()
    {
        return LinearOpModeCamera.convertYuvImageToRgb(camera.yuvImage, camera.width, camera.height, camera.ds);
    }

    // Draws a box on a given Bitmap image. This box defines the area that will be analyzed
    public CameraBox drawBox(int xMax, int xMin, int yMax, int yMin, Bitmap rgbImage, RGBAverage color)
    {
        // Iterates through all the perimeter pixels using the dimensions given
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                if (x == xMax && y <= yMax) {
                    // Sets the pixel color
                    rgbImage.setPixel(x, y, Color.rgb(color.red, color.green, color.blue));
                }
                if (x <= xMax && y == yMin) {
                    // Sets the pixel color
                    rgbImage.setPixel(x, y, Color.rgb(color.red, color.green, color.blue));
                }
                if (x == xMin && y <= yMax) {
                    // Sets the pixel color
                    rgbImage.setPixel(x, y, Color.rgb(color.red, color.green, color.blue));
                }
                if (x <= xMax && y == yMax) {
                    // Sets the pixel color
                    rgbImage.setPixel(x, y, Color.rgb(color.red, color.green, color.blue));

                }
            }
        }

        // Returns an instance of the CameraBox class with the data declared above. No RGB average is stored quite yet
        return new CameraBox(xMin, xMax, yMin, yMax, rgbImage, new RGBAverage(0, 0, 0));
    }

    // Takes in a CameraBox object and returns that object but with an RGB average of the pixels in the given area
    public CameraBox getBoxAverage(CameraBox box)
    {
        // Iterates through the pixels within the given area
        for (int x = box.xMin; x < box.xMax; x++)
        {
            for (int y = box.yMin; y < box.yMax; y++)
            {
                // Gets the current pixel
                int pixel = box.image.getPixel(x, y);

                // Sets the average for all the pixels in the area (RGB)
                redAverage += LinearOpModeCamera.red(pixel);
                blueAverage += LinearOpModeCamera.blue(pixel);
                greenAverage += LinearOpModeCamera.green(pixel);
            }
        }

        // Normalizes pixels for all values (RGB)
        redAverage = camera.normalizePixels(redAverage);
        blueAverage = camera.normalizePixels(blueAverage);
        greenAverage = camera.normalizePixels(greenAverage);

        // Returns the CameraBox object with all the existing data plus the averages
        return new CameraBox(box.xMin, box.xMax, box.yMin, box.yMax, box.image, new RGBAverage(redAverage, greenAverage, blueAverage));

    }

    // Sets the camera's downsampling
    public void setCameraDownsampling(int downsampling)
    {
        camera.setCameraDownsampling(downsampling);
    }

}


