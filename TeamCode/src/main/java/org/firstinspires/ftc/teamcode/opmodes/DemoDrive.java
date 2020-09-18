package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;

@TeleOp(name="DemoDrive", group= "Demo")
public class DemoDrive extends LinearOpMode
{
    private double speed = 0.55;

    @Override
    public void runOpMode()
    {
        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();

        while (opModeIsActive())
        {
            //sets motor power according to joystick input
            robot.drive.motorRF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
            robot.drive.motorRB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
            robot.drive.motorLB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
            robot.drive.motorLF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);
        }
    }
}
