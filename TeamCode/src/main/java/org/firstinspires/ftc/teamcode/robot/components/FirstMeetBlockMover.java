package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FirstMeetBlockMover
{

    private Servo grabber_servo;

    public DcMotor blockArm;

    public ServoArm grabber;

    public double armPower = 0;

    private static final double DOWN_POWER = 0.2;
    private static final double UP_POWER = 0.8;
    private static final double HOLD_POWER = 1.0;

    // Constructor/Init
    public FirstMeetBlockMover(HardwareMap hardwareMap)
    {
        grabber_servo = hardwareMap.servo.get("grabber");

        // FirstMeetBlockMover ServoArm class
        grabber = new ServoArm(grabber_servo, 0.25, 0.0, 0.25);

        blockArm = hardwareMap.dcMotor.get("blockArm");

        blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        blockArm.setTargetPosition(0);

        blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        blockArm.setPower(1.0);

    }


    public void armControl(Gamepad gamepad)
    {

        if(gamepad.left_stick_y == 0)
        {
            blockArm.setTargetPosition(blockArm.getCurrentPosition());
            armPower = HOLD_POWER;
        }
        else if(gamepad.left_stick_y < 0)
        {
            blockArm.setTargetPosition(blockArm.getCurrentPosition() - 20);
            armPower = UP_POWER;
        }
        else if(gamepad.left_stick_y > 0)
        {
            blockArm.setTargetPosition(blockArm.getCurrentPosition() + 20);
            armPower = DOWN_POWER;
        }

        blockArm.setPower(armPower);

    }



}
