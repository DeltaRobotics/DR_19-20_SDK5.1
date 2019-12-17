package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="BlueFarSky",group = "Auto")
public class BlueFarSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        GenOneRobot robot = new GenOneRobot(hardwareMap, telemetry);

        waitForStart();

        robot.drive.encoderDrive(1500, driveStyle.STRAFE_RIGHT,0.675);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000,driveStyle.FORWARD,0.575);

    }

}
