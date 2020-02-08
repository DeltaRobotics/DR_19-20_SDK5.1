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

    private double FOUNDATION_SPEED = 0.55;

    private boolean foundationState =  false;

    private boolean rightBumperState = false;

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


            robot.blockMover.armControl(robot.drive, speed);
            //robot.blockMover.blockArm.setPower(gamepad2.left_stick_y);


            if(gamepad1.left_bumper)
            {
                speed = FOUNDATION_SPEED;
            }
            else if (gamepad1.left_trigger > 0.2)
            {

                speed = 1.0;
            }
            else
            {
                speed = 0.4;
            }

            if(gamepad1.right_bumper && !rightBumperState)
            {
                rightBumperState = true;

                if(foundationState)
                {
                    foundationState = false;
                }
                else
                {
                    foundationState = true;
                }
            }
            else if(!gamepad1.right_bumper)
            {
                rightBumperState = false;
            }



            if (gamepad2.a)
            {
                robot.blockMover.openGrabber();
            }

            if (gamepad2.b)
            {
                robot.blockMover.closeGrabber();
            }

            if (gamepad1.right_trigger > 0.2)
            {
                robot.blockMover.intake_In();
            }
            else
            {

                robot.blockMover.intake_Stop();

            }

            if (gamepad1.dpad_left)
            {
                robot.blockMover.intake_Out();
            }


            if (gamepad2.dpad_left)
            {

                robot.blockMover.capstone_on();
            }

            if (gamepad2.dpad_right)
            {
                robot.blockMover.capstone_off();
            }

            if(foundationState)
            {
                robot.blockMover.foundationDown();
            }
            else
            {
                robot.blockMover.foundationUp();
            }

            if(gamepad1.dpad_down)
            {
                robot.blockMover.push_stone();
            }
            else
            {
                robot.blockMover.stone_push_home();
            }



            telemetry.addData("Lift Level", robot.blockMover.lift_level);

            telemetry.addData("Lift Position", robot.blockMover.lift_left.getCurrentPosition());
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
