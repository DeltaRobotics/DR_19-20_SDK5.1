package org.firstinspires.ftc.teamcode.deltacamera;

import android.graphics.Bitmap;

public class CameraBox
{
    public int xMin = 0;
    public int xMax = 0;
    public int yMin = 0;
    public int yMax = 0;

    public Bitmap image = null;

    public RGBAverage average;


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
