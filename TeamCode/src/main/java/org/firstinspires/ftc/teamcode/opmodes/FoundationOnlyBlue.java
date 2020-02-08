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

        robot.drive.encoderDrive(1700, driveStyle.STRAFE_RIGHT, 0.75);

        robot.drive.encoderDrive(300, driveStyle.STRAFE_RIGHT, 0.45);

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

        //robot.drive.encoderDrive(350, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.timeDrive(1700, 0.4, driveStyle.BACKWARD);

        sleep(sleepTime);

        robot.drive.timeDrive(1500, 0.5, driveStyle.STRAFE_RIGHT);

        sleep(sleepTime);

        robot.drive.encoderDrive(100, driveStyle.STRAFE_LEFT, 0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2250, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.timeDrive(500, 0.35, driveStyle.STRAFE_RIGHT);

        sleep(sleepTime);
    }



}
