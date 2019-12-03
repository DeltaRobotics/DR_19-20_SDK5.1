package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GenTwoBlockMover
{

    public Servo grabber_servo;

    public DcMotor blockArm;

    public Servo intake_left;

    public Servo intake_right;

    public DcMotor lift_right;

    public DcMotor lift_left;


    public double armPower = 0;

    private static final double DOWN_POWER = 1.0;
    private static final double UP_POWER = 1.0;
    private static final double HOLD_POWER = 1.0;
    private static final int POSITION_DELTA = 20;
    public static final int PLACE_POSITION = 3500;
    //private static final int TRAVEL_POSITION = 3000;
    public static final int HOME_POSITION = 500;

    public static final double GRABBER_OPEN = 0.25;
    public static final double GRABBER_INIT = 0.25;
    public static final double GRABBER_CLOSE = 0.8;

    // Constructor/Init
    public GenTwoBlockMover(HardwareMap hardwareMap)
    {
        grabber_servo = hardwareMap.servo.get("grabber");

        intake_left = hardwareMap.servo.get("intakeL");

        intake_right = hardwareMap.servo.get("intakeR");

        blockArm = hardwareMap.dcMotor.get("blockArm");

        lift_left = hardwareMap.dcMotor.get("lift_left");

        lift_right = hardwareMap.dcMotor.get("lift_right");

        lift_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lift_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        blockArm.setTargetPosition(blockArm.getCurrentPosition());

        blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        blockArm.setPower(1.0);

        grabber_servo.setPosition(GRABBER_INIT);

        intake_left.setPosition(.5);

        intake_right.setPosition(.5);

    }


    public void armControl(Gamepad gamepad)
    {

        if(gamepad.left_stick_y == 0)
        {
            armPower = HOLD_POWER;
        }
        else if(gamepad.left_stick_y < 0)
        {
            blockArm.setTargetPosition(blockArm.getTargetPosition() - POSITION_DELTA);
            armPower = UP_POWER;
        }
        else if(gamepad.left_stick_y > 0)
        {
            blockArm.setTargetPosition(blockArm.getTargetPosition() + POSITION_DELTA);
            armPower = DOWN_POWER;
        }

        if(gamepad.right_trigger > 0.5)
        {
            blockArm.setTargetPosition(PLACE_POSITION);
        }

        /*if(gamepad.x)
        {
            blockArm.setTargetPosition(TRAVEL_POSITION);
        }

         */

        if(gamepad.left_trigger > 0.5)
        {
            blockArm.setTargetPosition(HOME_POSITION);
        }

        blockArm.setPower(armPower);

        // Lift control
        lift_left.setPower(gamepad.right_stick_y);
        lift_right.setPower(gamepad.right_stick_y);

    }

    public void moveArm(int target, double power, int tolerance, String message, Telemetry telemetry)
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
    }

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
