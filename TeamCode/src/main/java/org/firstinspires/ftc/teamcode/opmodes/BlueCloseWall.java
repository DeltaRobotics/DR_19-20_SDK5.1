package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name = "BlueCloseWall", group = "Auto")
public class BlueCloseWall extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        int sleepTime = 500;

        GenOneRobot robot = new GenOneRobot(hardwareMap, telemetry);

        waitForStart();

        robot.drive.encoderDrive(750, driveStyle.FORWARD,0.575);

        sleep(sleepTime);
    }
}
