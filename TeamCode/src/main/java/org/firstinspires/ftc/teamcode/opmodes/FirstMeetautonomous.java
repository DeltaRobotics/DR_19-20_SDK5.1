package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Disabled
public class FirstMeetautonomous extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        Robot robot = new Robot(hardwareMap);

        robot.blockMover.blockArm = hardwareMap.dcMotor.get("blockArm");

        robot.blockMover.blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        robot.blockMover.blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.blockMover.blockArm.setTargetPosition(robot.blockMover.blockArm.getCurrentPosition());

        robot.blockMover.blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.blockMover.blockArm.setPower(1.0);

        robot.blockMover.grabber_servo.setPosition(0.45);

        waitForStart();

        //put camera stuff here -->

        robot.drive.encoderDrive(600, driveStyle.STRAFE_RIGHT,0.5);

        sleep(sleepTime);//wait for next action

        robot.blockMover.blockArm.setTargetPosition(4000);

        sleep(5000);

        robot.drive.encoderDrive(250, driveStyle.STRAFE_RIGHT,0.3);//

        robot.blockMover.grabber_servo.setPosition(0.23);

        sleep(sleepTime);//wait for next action

        robot.blockMover.blockArm.setTargetPosition(200);

        robot.drive.encoderDrive(400,driveStyle.STRAFE_LEFT,0.5);

        sleep(sleepTime);//wait for next action

        robot.drive.encoderDriveInches(10,driveStyle.BACKWARD, 0.4);//Drive to foundation

        sleep(10000);//wait for next action
//
//        //Arm Movement to place Skystone on foundation here -->
//
//        //sleep(sleepTime);//wait for next action
//
//        robot.drive.encoderDriveInches(90,driveStyle.FORWARD, 0.4);//drive to get second Skystone
//
//        sleep(sleepTime);//wait for next action
//
//        robot.drive.encoderDrive(750,driveStyle.STRAFE_RIGHT,0.5);
//
//        //put arm movement to grab Skystone here -->
//
//        sleep(sleepTime);//wait for next action
//
//        robot.drive.encoderDrive(750,driveStyle.STRAFE_LEFT,0.5);
//
//        sleep(sleepTime);//wait for next action
//
//        robot.drive.encoderDriveInches(90,driveStyle.BACKWARD, 0.4);//go back to foundation
//
//        sleep(sleepTime);//wait for  xt action
//
//        //Put arm movement to place Skystone on foundation here -->
//
//        //sleep(sleepTime);//wait for next action
//
//        robot.drive.encoderDrive(1250,driveStyle.STRAFE_LEFT, 0.5);//Move to wall for preparation to park
//
//        sleep(sleepTime);//wait for next action
//
//        robot.drive.encoderDriveInches(50,driveStyle.FORWARD, 0.4);//move to park under bridge



    }

}
