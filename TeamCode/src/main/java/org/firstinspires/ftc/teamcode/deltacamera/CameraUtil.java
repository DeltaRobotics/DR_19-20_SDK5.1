package org.firstinspires.ftc.teamcode.deltacamera;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.YuvImage;

import for_camera_opmodes.LinearOpModeCamera;

public class CameraUtil
{

    public int redAverage = -76800;
    public int blueAverage = -76800;
    public int greenAverage = -76800;

    public LinearOpModeCamera camera;

    public CameraUtil(LinearOpModeCamera camera)
    {
        this.camera = camera;
    }


    public void startCamera()
    {
        camera.startCamera();
    }

    public void stopCamera()
    {
        camera.stopCamera();
    }

    public void saveImage(Bitmap rgbImage)
    {
        camera.SaveImage(rgbImage);
    }

    public Bitmap takePicture()
    {
        Bitmap rgbImage = null;

        if(camera.imageReady())
        {

            rgbImage = LinearOpModeCamera.convertYuvImageToRgb(camera.yuvImage, camera.width, camera.height, camera.ds);

        }

        return rgbImage;
    }

    public Bitmap drawBox(int xMax, int xMin, int yMax, int yMin, Bitmap rgbImage)
    {
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                if (x == xMax && y <= yMax) {
                    rgbImage.setPixel(x, y, Color.rgb(255, 0, 0));
                }
                if (x <= xMax && y == yMin) {
                    rgbImage.setPixel(x, y, Color.rgb(255, 0, 0));
                }
                if (x == xMin && y <= yMax) {
                    rgbImage.setPixel(x, y, Color.rgb(255, 0, 0));
                }
                if (x <= xMax && y == yMax) {
                    rgbImage.setPixel(x, y, Color.rgb(255, 0, 0));

                }
            }
        }

        return rgbImage;
    }

    public RGBAverage boxAnalysis(int xMax, int xMin, int yMax, int yMin, Bitmap rgbImage)
    {
        for (int x = xMin; x < xMax; x++)
        {
            for (int y = yMin; y < yMax; y++)
            {
                int pixel = rgbImage.getPixel(x, y);
                redAverage += camera.red(pixel);
                blueAverage += camera.blue(pixel);
                greenAverage += camera.green(pixel);
            }
        }

        redAverage = camera.normalizePixels(redAverage);
        blueAverage = camera.normalizePixels(blueAverage);
        greenAverage = camera.normalizePixels(greenAverage);

        return new RGBAverage(redAverage, greenAverage, blueAverage);
    }

    public void setCameraDownsampling(int downsampling)
    {
        camera.setCameraDownsampling(downsampling);
    }

}

