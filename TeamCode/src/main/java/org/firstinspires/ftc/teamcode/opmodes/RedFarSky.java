package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="RedFarSky",group = "Auto")
public class RedFarSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        GenOneRobot robot = new GenOneRobot(hardwareMap, telemetry);

        waitForStart();

        robot.drive.encoderDrive(1700,driveStyle.STRAFE_RIGHT,0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000, driveStyle.BACKWARD,0.4);

    }

}
