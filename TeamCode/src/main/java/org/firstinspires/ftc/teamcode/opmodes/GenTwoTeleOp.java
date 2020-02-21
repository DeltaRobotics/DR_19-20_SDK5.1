package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.GenTwoBlockMover;

/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="GenTwoTeleOp",group = "")
public class GenTwoTeleOp extends LinearOpMode
{
    public static double speed = 0.5;

    public static boolean foundationState =  false;

    public static boolean autoArmState = false;

    public static boolean rightBumperState = false;

    public static boolean leftBumperState = false;



    public void teleOpControl(LinearOpMode linearOpMode, GenTwoRobot robot)
    {


        //sets motor power according to joystick input
        robot.drive.motorRF.setPower(speed * robot.drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[0]);
        robot.drive.motorRB.setPower(speed * robot.drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[1]);
        robot.drive.motorLB.setPower(speed * robot.drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[2]);
        robot.drive.motorLF.setPower(speed * robot.drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[3]);


        if (linearOpMode.gamepad1.left_trigger > 0.2)
        {

            speed = 1.0;
        }
        else
        {
            speed = 0.47;
        }

        if(linearOpMode.gamepad1.right_bumper && !rightBumperState)
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
        else if(!linearOpMode.gamepad1.right_bumper)
        {
            rightBumperState = false;
        }



        if (linearOpMode.gamepad2.a)
        {
            robot.blockMover.openGrabber();
        }

        if (linearOpMode.gamepad2.b)
        {
            robot.blockMover.closeGrabber();
        }

        if (linearOpMode.gamepad1.right_trigger > 0.2)
        {
            if(robot.blockMover.capstone.getPosition() == GenTwoBlockMover.CAPSTONE_CLOSE)
            {
                robot.blockMover.capstone_off();
            }

            if(robot.blockMover.blockArm.getCurrentPosition() < GenTwoBlockMover.TRAVEL_POSITION)
            {
                robot.blockMover.blockArm.setTargetPosition(GenTwoBlockMover.TRAVEL_POSITION);
            }
            robot.blockMover.intake_In();
        }
        else
        {

            robot.blockMover.intake_Stop();

        }

        if (linearOpMode.gamepad1.dpad_left)
        {
            robot.blockMover.intake_Out();
        }


        if (linearOpMode.gamepad2.dpad_left)
        {

            robot.blockMover.capstone_on();
        }

        if (linearOpMode.gamepad2.dpad_right)
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

        if(linearOpMode.gamepad1.dpad_down)
        {
            robot.blockMover.push_stone();
        }
        else
        {
            robot.blockMover.stone_push_home();
        }

        if(linearOpMode.gamepad1.left_bumper && !leftBumperState)
        {
            leftBumperState = true;
            if (!autoArmState)
            {
                autoArmState = true;
                robot.blockMover.auto_arm_right_down();
            }
            else
            {
                autoArmState = false;
                robot.blockMover.auto_arm_right_home();
            }

        }
        else if(!linearOpMode.gamepad1.left_bumper)
        {
            leftBumperState = false;
        }




        linearOpMode.telemetry.addData("Lift Level", robot.blockMover.lift_level);
        linearOpMode.telemetry.addData("Lift Position", robot.blockMover.lift.getCurrentPosition());
        linearOpMode.telemetry.addData("Lift Target Position", robot.blockMover.lift.getTargetPosition());
        linearOpMode.telemetry.addData("Lift Power", robot.blockMover.lift.getPower());
        linearOpMode.telemetry.addData("Arm Position", robot.blockMover.blockArm.getCurrentPosition());
        linearOpMode.telemetry.addData("Arm Target Position", robot.blockMover.blockArm.getTargetPosition());
        linearOpMode.telemetry.addData("Arm Power", robot.blockMover.blockArm.getPower());
        linearOpMode.telemetry.addData("Arm isBusy()", robot.blockMover.blockArm.isBusy());
        linearOpMode.telemetry.addData("Grabber Position", robot.blockMover.grabber_servo.getPosition());
        linearOpMode.telemetry.addData("Foundation mover position", robot.blockMover.foundation_mover.getPosition());
        linearOpMode.telemetry.addData("speed", speed);
        linearOpMode.telemetry.update();
    }

    public void runOpMode()
    {

        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();
        while (opModeIsActive())
        {
            teleOpControl(this, robot);

            robot.blockMover.armControl(robot, speed);

        }
    }
}
