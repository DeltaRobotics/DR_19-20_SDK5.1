package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name = "RedFarWall", group = "Auto")
public class RedFarWall extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        int sleepTime = 500;

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        robot.drive.encoderDrive(100, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000,driveStyle.BACKWARD,0.4);

        sleep(sleepTime);
    }
}