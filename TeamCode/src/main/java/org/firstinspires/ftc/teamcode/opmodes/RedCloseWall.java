package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name = "RedCloseWall", group = "Auto")
public class RedCloseWall extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        int sleepTime = 500;

        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();

        robot.drive.encoderDrive(750, driveStyle.BACKWARD,0.4);

        sleep(sleepTime);
    }
}
