package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous (name = "FoundationOnlyRedNoTurn", group = "auto")
public class FoundationOnlyRedNoTurn extends LinearOpMode {
    private int sleepTime = 250;

    @Override
    public void runOpMode() {
        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();

        robot.drive.encoderDrive(1900, driveStyle.STRAFE_RIGHT, 0.75);

        robot.drive.encoderDrive(300, driveStyle.STRAFE_RIGHT, 0.45);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);

        robot.drive.timeDrive(2500, 0.65, driveStyle.STRAFE_LEFT);

        sleep(sleepTime);

        robot.blockMover.foundationUp();

        sleep(sleepTime + 500);

        robot.drive.encoderDrive(2500, driveStyle.FORWARD, 0.75);
    }
}
