package org.firstinspires.ftc.teamcode.deltacamera;

public class SkystoneCameraEval
{
    public static RGBAverage SKYSTONE_AVERAGE = new RGBAverage(30, 30, 30);

    public boolean skystoneAnalysis(RGBAverage average)
    {
        if((average.red - average.blue) < SKYSTONE_AVERAGE.red)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
