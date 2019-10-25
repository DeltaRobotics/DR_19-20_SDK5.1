package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="AutonomousFarBlue",group = "")
public class AutonomousFarBlue extends LinearOpMode
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

        robot.blockMover.grabber_servo.setPosition(robot.blockMover.GRABBER_INIT);

        robot.drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        robot.drive.encoderDrive(1500, driveStyle.STRAFE_RIGHT,0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000,driveStyle.FORWARD,0.4);

    }

}
