package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robot.components.GenOneBlockMover;
import org.firstinspires.ftc.teamcode.robot.components.MecanumDriveTrain;


public class GenOneRobot
{

    public MecanumDriveTrain drive;
    public GenOneBlockMover blockMover;

    /*
        Component Class names will be very specific
        The object names will be general and won't change
    */

    // Constructor
    public GenOneRobot(HardwareMap hardwareMap, Telemetry telemetry)
    {
        // Drive class for first meet with mecanum wheels
        drive = new MecanumDriveTrain(hardwareMap, telemetry);

        // Block mover class for first meet
        blockMover = new GenOneBlockMover(hardwareMap);
    }

// Test commit comment woo

}
