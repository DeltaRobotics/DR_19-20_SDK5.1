package org.firstinspires.ftc.teamcode.deltacamera;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    public SkystonePositions getSkystonePosition(CameraBox box1, CameraBox box2)
    {
        if(skystoneAnalysis(box1.average))
        {
            return SkystonePositions.LEFT;
        }
        else if(skystoneAnalysis(box2.average))
        {
            return SkystonePositions.CENTER;
        }
        else
        {
            return SkystonePositions.RIGHT;
        }
    }
}
