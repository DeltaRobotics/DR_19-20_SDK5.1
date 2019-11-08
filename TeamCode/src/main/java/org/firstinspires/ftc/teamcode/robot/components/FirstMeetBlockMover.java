package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FirstMeetBlockMover
{

    public Servo grabber_servo;

    public DcMotor blockArm;

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
    public FirstMeetBlockMover(HardwareMap hardwareMap)
    {
        grabber_servo = hardwareMap.servo.get("grabber");

        blockArm = hardwareMap.dcMotor.get("blockArm");

        blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        blockArm.setTargetPosition(blockArm.getCurrentPosition());

        blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        blockArm.setPower(1.0);

        grabber_servo.setPosition(GRABBER_INIT);

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



}
