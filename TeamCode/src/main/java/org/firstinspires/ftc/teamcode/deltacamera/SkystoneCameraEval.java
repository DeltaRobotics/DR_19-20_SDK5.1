package org.firstinspires.ftc.teamcode.deltacamera;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// Class that contains game specific analysis code
public class SkystoneCameraEval
{
    // RGB average of Skystone
    private static RGBAverage SKYSTONE_AVERAGE = new RGBAverage(30, 30, 30);

    // Method to compare the given RGB average to that of the Skystone
    // Returns true if it is a Skystone
    // Returns false if it is not a Skystone
    private boolean skystoneAnalysis(RGBAverage average)
    {
        // Looks at the difference between the red and blue values to do analysis
        if((average.red - average.blue) < SKYSTONE_AVERAGE.red)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Uses the method skystoneAnalysis
    // Takes in two CameraBox objects
    public SkystonePositions getSkystonePosition(CameraBox box1, CameraBox box2)
    {
        // Uses the skystoneAnalysis method to decide which stone is the Skystone
        // Returns the position of the Skystone
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
