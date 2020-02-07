package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous (name = "FoundationOnlyBlue", group = "auto")
public class FoundationOnlyBlue extends LinearOpMode
{
    @Override
    public void runOpMode() {
        GenTwoRobot robot = new GenTwoRobot(this);

        int sleepTime = 250;

        waitForStart();

        robot.drive.encoderDrive(50, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(525, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000, driveStyle.STRAFE_RIGHT, 0.75);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);


        robot.drive.OrientationDrive(25, 0.4, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(2100, driveStyle.STRAFE_LEFT, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(94, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.blockMover.foundationUp();

        sleep(sleepTime);

        robot.drive.encoderDrive(100, driveStyle.STRAFE_LEFT, 0.75);

        robot.drive.OrientationDrive(170, 0.75, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(350, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.timeDrive(1700, 0.4, driveStyle.FORWARD);

        sleep(sleepTime);

        robot.drive.timeDrive(1000, 0.75, driveStyle.STRAFE_LEFT);

        sleep(sleepTime);

        robot.drive.encoderDrive(100, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2600, driveStyle.BACKWARD, 0.75);

        sleep(sleepTime);

        robot.drive.timeDrive(400, 0.5, driveStyle.STRAFE_LEFT);

        sleep(sleepTime);
    }



}
