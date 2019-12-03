package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.components.GenOneBlockMover;

/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="GenOneTeleOp", group = "")
public class GenOneTeleOp extends LinearOpMode
{
    private double speed = 0.5;

    //MyDcMotor shooter = null;

    public void runOpMode()
    {
        GenOneRobot robot = new GenOneRobot(hardwareMap, telemetry);

        robot.blockMover.initGrabber();


        robot.blockMover.grabber_servo.setPosition(GenOneBlockMover.GRABBER_INIT);

        waitForStart();
        while (opModeIsActive())
        {

            //sets motor power according to joystick input
            robot.drive.motorRF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
            robot.drive.motorRB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
            robot.drive.motorLB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
            robot.drive.motorLF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            robot.blockMover.armControl(gamepad2);


            if(gamepad1.a)
            {
                speed = 1.0;
            }

            if(gamepad1.b)
            {
                speed = 0.5;
            }

            if(gamepad2.a)
            {
                robot.blockMover.openGrabber();
            }

            if(gamepad2.b)
            {
                robot.blockMover.closeGrabber();
            }

            //Sends data back to driver station
            telemetry.addData("Motor RF Power", robot.drive.motorRF.getPower());
            telemetry.addData("motor LF power", robot.drive.motorLF.getPower());
            telemetry.addData("Motor RB power", robot.drive.motorRB.getPower());
            telemetry.addData("Motor LB power", robot.drive.motorLB.getPower());
            telemetry.addData("MotorLB Encoder", robot.drive.motorLB.getCurrentPosition());

            telemetry.addData("Arm Position", robot.blockMover.blockArm.getCurrentPosition());
            telemetry.addData("Arm Target Position", robot.blockMover.blockArm.getTargetPosition());
            telemetry.addData("Arm Power", robot.blockMover.blockArm.getPower());

            telemetry.addData("Arm Power Variable", robot.blockMover.armPower);

            telemetry.addData("Grabber Position", robot.blockMover.grabber_servo.getPosition());

            telemetry.addData("speed", speed);

    }

    }

}
