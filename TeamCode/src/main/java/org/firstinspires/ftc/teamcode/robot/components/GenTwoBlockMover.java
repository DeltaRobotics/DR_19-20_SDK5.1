package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class GenTwoBlockMover
{

    public Servo grabber_servo;

    public DcMotor blockArm;

    public Servo intake_left;

    public Servo intake_right;

    public DcMotor lift;

    public double armPower = 0;


    public static final double DOWN_POWER = 0.3; // Should never match hold power
    public static final double UP_POWER = 0.3; // Should never match hold power
    public static final double HOLD_POWER = 1.0; // Should ALWAYS be different from the other arm power variables
    public static final double PRESET_POSITION_POWER = 0.3;
    public static final int POSITION_DELTA = 15;
    public static final int COLLECT_POSITION = 50;
    public static final int TRAVEL_POSITION = 250;
    public static final int DELIVER_POSITION = 1080;


    public static final double GRABBER_OPEN = 0.9;
    public static final double GRABBER_INIT = 0.9;
    public static final double GRABBER_CLOSE = 0.57;

    public static final double ARM_MAX_POWER = 0.3;


    public static final int LIFT_MAX_POSITION = -2000;

    // Constructor/Init
    public GenTwoBlockMover(HardwareMap hardwareMap)
    {
        grabber_servo = hardwareMap.servo.get("grabber");

        intake_left = hardwareMap.servo.get("intakeL");

        intake_right = hardwareMap.servo.get("intakeR");

        blockArm = hardwareMap.dcMotor.get("blockArm");

        lift = hardwareMap.dcMotor.get("lift");

        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        blockArm.setTargetPosition(blockArm.getCurrentPosition());

        blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        blockArm.setPower(1.0);

        grabber_servo.setPosition(GRABBER_INIT);

        intake_Stop();

    }


    public void armControl(Gamepad gamepad2, Gamepad gamepad1, MecanumDriveTrain drive, double speed)
    {

        if(gamepad2.left_stick_y == 0 && armPower != HOLD_POWER && !blockArm.isBusy())
        {
            armPower = HOLD_POWER;
        }
        else if(gamepad2.left_stick_y < 0)
        {
            blockArm.setTargetPosition(blockArm.getTargetPosition() - POSITION_DELTA);
            armPower = UP_POWER;
        }
        else if(gamepad2.left_stick_y > 0)
        {
            blockArm.setTargetPosition(blockArm.getTargetPosition() + POSITION_DELTA);
            armPower = DOWN_POWER;
        }

        if(gamepad2.right_trigger > 0.5)
        {
            openGrabber();

            armPower = PRESET_POSITION_POWER;
            blockArm.setTargetPosition(COLLECT_POSITION);
        }

        if(gamepad2.left_trigger > 0.5)
        {
            closeGrabber();

            armPower = PRESET_POSITION_POWER;
            blockArm.setTargetPosition(DELIVER_POSITION);
        }

        if(gamepad2.right_bumper)
        {
            openGrabber();

            int target = lift.getTargetPosition() - 700;
            lift.setPower(-1.0);
            while(lift.getCurrentPosition() > target)
            {
                //sets motor power according to joystick input
                drive.motorRF.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
                drive.motorRB.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
                drive.motorLB.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
                drive.motorLF.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            }
            lift.setPower(0);

            armPower = PRESET_POSITION_POWER;
            blockArm.setTargetPosition(TRAVEL_POSITION);
            while(blockArm.isBusy())
            {
                //sets motor power according to joystick input
                drive.motorRF.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
                drive.motorRB.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
                drive.motorLB.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
                drive.motorLF.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            }

            lift.setPower(0.75);
            while(lift.getCurrentPosition() < -50)
            {
                //sets motor power according to joystick input
                drive.motorRF.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
                drive.motorRB.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
                drive.motorLB.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
                drive.motorLF.setPower(speed * drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            }
            lift.setPower(0);

        }

        blockArm.setPower(armPower);




        // Lift control
        if(lift.getCurrentPosition() >= LIFT_MAX_POSITION || gamepad1.right_stick_y > 0)
        {
            lift.setPower(gamepad1.right_stick_y*ARM_MAX_POWER);
        }
        else
        {
            lift.setPower(0);
        }
    }



    /*public void moveArm(int target, double power, int tolerance, String message, Telemetry telemetry)
    {
        int delta = blockArm.getTargetPosition() - target;

        blockArm.setPower(power);

        blockArm.setTargetPosition(target);

        if(delta < 0)
        {
            while(blockArm.getCurrentPosition() < blockArm.getTargetPosition() - tolerance)
            {
                telemetry.addData("Arm Movement", message);
                telemetry.addData("Current Position", blockArm.getCurrentPosition());
                telemetry.addData("Target Position", blockArm.getTargetPosition());
                telemetry.addData("Tolerance", tolerance);
                telemetry.update();
            }
        }
        else if(delta > 0)
        {
            while(blockArm.getCurrentPosition() > blockArm.getTargetPosition() + tolerance)
            {
                telemetry.addData("Arm Movement", message);
                telemetry.addData("Current Position", blockArm.getCurrentPosition());
                telemetry.addData("Target Position", blockArm.getTargetPosition());
                telemetry.addData("Tolerance", tolerance);
                telemetry.update();
            }
        }
    }*/


    public void openGrabber()
    {
        grabber_servo.setPosition(GRABBER_OPEN);
    }

    public void closeGrabber()
    {
        grabber_servo.setPosition(GRABBER_CLOSE);
    }

    public void initGrabber()
    {
        grabber_servo.setPosition(GRABBER_INIT);
    }

    public void intake_In()
    {
        intake_left.setPosition(.9);
        intake_right.setPosition(.04);
    }

    public void intake_Out()
    {
        intake_left.setPosition(.04);
        intake_right.setPosition(.9);
    }

    public void intake_Stop()
    {
        intake_left.setPosition(.5);
        intake_right.setPosition(.5);
    }
}
