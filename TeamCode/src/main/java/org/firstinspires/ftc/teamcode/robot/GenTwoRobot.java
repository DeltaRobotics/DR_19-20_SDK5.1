package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    public GenTwoRobot(LinearOpMode linearOpMode)
    {
        // Drive class for first meet with mecanum wheels
        drive = new MecanumDriveTrain(linearOpMode);

        // Block mover class for first meet
        blockMover = new GenTwoBlockMover(linearOpMode);
    }
}
