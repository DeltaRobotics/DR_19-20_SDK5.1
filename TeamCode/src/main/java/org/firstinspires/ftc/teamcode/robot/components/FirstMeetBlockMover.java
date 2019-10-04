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

    private static final double DOWN_POWER = 0.2;
    private static final double UP_POWER = 0.6;
    private static final double HOLD_POWER = 1.0;

    // Constructor/Init
    public FirstMeetBlockMover(HardwareMap hardwareMap)
    {
        grabber_servo = hardwareMap.servo.get("grabber");

        // FirstMeetBlockMover ServoArm class
        grabber = new ServoArm(grabber_servo, 0.25, 0.0, 0.25);

        blockArm = hardwareMap.dcMotor.get("blockArm");




    }


    public void armControl(Gamepad gamepad)
    {

        if(gamepad.left_stick_y == 0)
        {
            blockArm.setPower(HOLD_POWER);
            blockArm.setTargetPosition(blockArm.getCurrentPosition());
        }
        else if(gamepad.left_stick_y > 0)
        {
            blockArm.setPower(UP_POWER);
            blockArm.setTargetPosition(blockArm.getCurrentPosition() - 1);
        }
        else if(gamepad.left_stick_y < 0)
        {
            blockArm.setPower(DOWN_POWER);
            blockArm.setTargetPosition(blockArm.getCurrentPosition() + 1);
        }

    }



}
