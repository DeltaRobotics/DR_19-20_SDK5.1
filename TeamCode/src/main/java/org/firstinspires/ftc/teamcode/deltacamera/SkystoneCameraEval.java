package org.firstinspires.ftc.teamcode.deltacamera;

public class SkystoneCameraEval
{
    public static RGBAverage SKYSTONE_AVERAGE = new RGBAverage(0, 0, 0);

    public SkystonePositions skystoneAnalysis(RGBAverage average, int tolerance, SkystonePositions skystonePosition)
    {
        if((average.red >= SKYSTONE_AVERAGE.red + tolerance || average.red <= SKYSTONE_AVERAGE.red - tolerance) &&
                (average.green >= SKYSTONE_AVERAGE.green + tolerance || average.green <= SKYSTONE_AVERAGE.green - tolerance) &&
                (average.blue >= SKYSTONE_AVERAGE.blue + tolerance || average.blue <= SKYSTONE_AVERAGE.blue - tolerance))
        {
            return skystonePosition;
        }
        else
        {
            return SkystonePositions.NOT_FOUND;
        }
    }
}
