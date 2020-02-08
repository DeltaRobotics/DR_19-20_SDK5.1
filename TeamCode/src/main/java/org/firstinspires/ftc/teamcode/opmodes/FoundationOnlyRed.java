package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous (name = "FoundationOnlyRed", group = "auto")
public class FoundationOnlyRed extends LinearOpMode
{
    @Override
    public void runOpMode() {
        GenTwoRobot robot = new GenTwoRobot(this);

        int sleepTime = 250;

        waitForStart();

        robot.drive.encoderDrive(50, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(525, driveStyle.BACKWARD, 0.75);

        sleep(sleepTime);

        robot.drive.encoderDrive(1900, driveStyle.STRAFE_RIGHT, 0.75);

        robot.drive.encoderDrive(300, driveStyle.STRAFE_RIGHT, 0.45);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);


        robot.drive.OrientationDrive(-20, 0.4, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000, driveStyle.STRAFE_LEFT, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(-85, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.blockMover.foundationUp();

        sleep(sleepTime);

        robot.drive.encoderDrive(100, driveStyle.STRAFE_LEFT, 0.75);

        robot.drive.OrientationDrive(-8, 0.75, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(350, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.timeDrive(1700, 0.4, driveStyle.BACKWARD);

        sleep(sleepTime);

        robot.drive.timeDrive(1350, 0.55, driveStyle.STRAFE_LEFT);

        sleep(sleepTime);

        robot.drive.encoderDrive(200, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2800, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.timeDrive(850, 0.35, driveStyle.STRAFE_LEFT);

        sleep(sleepTime);
    }



}
