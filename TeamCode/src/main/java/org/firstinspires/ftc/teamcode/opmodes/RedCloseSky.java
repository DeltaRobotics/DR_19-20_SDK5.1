package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="RedCloseSky",group = "Auto")
public class RedCloseSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        GenOneRobot robot = new GenOneRobot(hardwareMap, telemetry);

        waitForStart();

        robot.drive.encoderDrive(750, driveStyle.BACKWARD,0.575);

        sleep(sleepTime);

        robot.drive.encoderDrive(1700,driveStyle.STRAFE_RIGHT,0.675);


    }

}
