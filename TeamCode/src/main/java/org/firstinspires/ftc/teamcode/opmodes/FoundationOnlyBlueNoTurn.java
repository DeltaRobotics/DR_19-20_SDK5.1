package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous (name = "FoundationOnlyBlueNoTurn", group = "auto")
public class FoundationOnlyBlueNoTurn extends LinearOpMode
{
    private int sleepTime = 250;

    @Override
    public void runOpMode()
    {
        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();

        robot.drive.encoderDrive(50, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(525, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.encoderDrive(1900, driveStyle.STRAFE_RIGHT, 0.75);

        robot.drive.encoderDrive(300, driveStyle.STRAFE_RIGHT, 0.45);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);

        robot.drive.OrientationDrive(-3, 0.55, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.timeDrive(2000, 0.65, driveStyle.STRAFE_LEFT);

        sleep(sleepTime);

        robot.drive.OrientationDrive(1, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.timeDrive(800, 0.35, driveStyle.STRAFE_LEFT);

        robot.blockMover.foundationUp();

        sleep(sleepTime + 500);

        robot.drive.encoderDrive(2800, driveStyle.BACKWARD, 0.55);
    }
}
