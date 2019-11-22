package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.components.GenTwoBlockMover;
import org.firstinspires.ftc.teamcode.robot.components.MecanumDriveTrain;


public class GenTwoRobot
{

    public MecanumDriveTrain drive;
    public GenTwoBlockMover blockMover;

    /*
        Component Class names will be very specific
        The object names will be general and won't change
    */

    // Constructor
    public GenTwoRobot(HardwareMap hardwareMap)
    {
        // Drive class for first meet with mecanum wheels
        drive = new MecanumDriveTrain(hardwareMap);

        // Block mover class for first meet
        blockMover = new GenTwoBlockMover(hardwareMap);
    }

// Test commit comment woo

}
