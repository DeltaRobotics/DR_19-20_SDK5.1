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
        return LinearOpModeCamera.convertYuvImageToRgb(camera.yuvImage, camera.width, camera.height, camera.ds);
    }

    public CameraBox drawBox(int xMax, int xMin, int yMax, int yMin, Bitmap rgbImage)
    {
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                if (x == xMax && y <= yMax) {
                    rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                }
                if (x <= xMax && y == yMin) {
                    rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                }
                if (x == xMin && y <= yMax) {
                    rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));
                }
                if (x <= xMax && y == yMax) {
                    rgbImage.setPixel(x, y, Color.rgb(0, 255, 0));

                }
            }
        }

        return new CameraBox(xMin, xMax, yMin, yMax, rgbImage, new RGBAverage(0, 0, 0));
}

    public CameraBox getBoxAverage(CameraBox box)
    {
        for (int x = box.xMin; x < box.xMax; x++)
        {
            for (int y = box.yMin; y < box.yMax; y++)
            {
                int pixel = box.image.getPixel(x, y);
                redAverage += camera.red(pixel);
                blueAverage += camera.blue(pixel);
                greenAverage += camera.green(pixel);
            }
        }

        redAverage = camera.normalizePixels(redAverage);
        blueAverage = camera.normalizePixels(blueAverage);
        greenAverage = camera.normalizePixels(greenAverage);

        return new CameraBox(box.xMin, box.xMax, box.yMin, box.yMax, box.image, new RGBAverage(redAverage, greenAverage, blueAverage));

    }

    public void setCameraDownsampling(int downsampling)
    {
        camera.setCameraDownsampling(downsampling);
    }

}


