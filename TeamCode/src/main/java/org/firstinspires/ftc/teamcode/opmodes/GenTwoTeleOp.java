package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;

/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="GenTwoTeleOp",group = "")
public class GenTwoTeleOp extends LinearOpMode
{
    private double speed = 0.5;

    public void runOpMode()
    {
        GenTwoRobot robot = new GenTwoRobot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive())
        {

            //sets motor power according to joystick input
            robot.drive.motorRF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
            robot.drive.motorRB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
            robot.drive.motorLB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
            robot.drive.motorLF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            robot.blockMover.armControl(gamepad2, gamepad1, robot.drive, speed);
            //robot.blockMover.blockArm.setPower(gamepad2.left_stick_y);


            if (gamepad1.dpad_up) {
                speed = 1.0;
            }

            if (gamepad1.dpad_down) {
                speed = 0.5;
            }

            if (gamepad2.a)
            {
                robot.blockMover.openGrabber();
            }

            if (gamepad2.b)
            {
                robot.blockMover.closeGrabber();
            }

            if(gamepad2.x)
            {
                robot.blockMover.foundationDown();
            }

            if(gamepad2.y)
            {
                robot.blockMover.foundationUp();
            }

            if (gamepad1.a)
            {
                robot.blockMover.intake_In();
            }

            if (gamepad1.y)
            {
                robot.blockMover.intake_Out();
            }

            if (gamepad1.b)
            {
                robot.blockMover.intake_Stop();
            }

            telemetry.addData("Lift Position", robot.blockMover.lift.getCurrentPosition());
            telemetry.addData("Arm Position", robot.blockMover.blockArm.getCurrentPosition());
            telemetry.addData("Arm Target Position", robot.blockMover.blockArm.getTargetPosition());
            telemetry.addData("Arm Power", robot.blockMover.blockArm.getPower());
            telemetry.addData("Arm isBusy()", robot.blockMover.blockArm.isBusy());

            telemetry.addData("Arm Power Variable", robot.blockMover.armPower);

            telemetry.addData("Grabber Position", robot.blockMover.grabber_servo.getPosition());
            telemetry.addData("Foundation mover position", robot.blockMover.foundation_mover.getPosition());

            telemetry.addData("speed", speed);


            //  telemetry.addData("shooter", shooter.getCurrentPosition());
            telemetry.update();
        }
    }
}
