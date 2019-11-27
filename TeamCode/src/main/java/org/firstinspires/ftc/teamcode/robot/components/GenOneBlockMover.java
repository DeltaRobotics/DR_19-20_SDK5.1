package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// This is the class that will control all the functions for the Generation One Block mover
public class GenOneBlockMover
{

    // Servo for the grabber
    public Servo grabber_servo;

    // Motor that controls the arm
    public DcMotor blockArm;

    // Current arm power
    public double armPower = 0;

    // Powers for the different states that the arm can be in (going up, down, or not moving)
    private static final double DOWN_POWER = 1.0;
    private static final double UP_POWER = 1.0;
    private static final double HOLD_POWER = 1.0;

    // Preset arm encoder positions
    private static final int POSITION_DELTA = 20; // How much the target will change each iteration of the code
    public static final int PLACE_POSITION = 3500; // Target encoder to place/collect skystone
    //private static final int TRAVEL_POSITION = 3000;
    public static final int HOME_POSITION = 500; // Target encoder to home the arm

    // Preset grabber servo positions (positions are in variable names)
    public static final double GRABBER_OPEN = 0.25;
    public static final double GRABBER_INIT = 0.25;
    public static final double GRABBER_CLOSE = 0.8;

    // Constructor/Init - This will be created when the opmode is started
    public GenOneBlockMover(HardwareMap hardwareMap) // The hardware map comes from LinearOpMode
    {
        // Sets the config name for the grabber
        grabber_servo = hardwareMap.servo.get("grabber");

        // Sets the config name for the block arm motor
        blockArm = hardwareMap.dcMotor.get("blockArm");

        // Sets the ZeroPowerBehavior for the block arm motor to BRAKE
        blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Sets the block arm motor mode to STOP_AND_RESET_ENCODER
        blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Sets the initial target position
        blockArm.setTargetPosition(blockArm.getCurrentPosition());

        // Sets the block arm to the RUN_TO_POSITION varible
        blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Sets the power of the block arm to full power
        blockArm.setPower(1.0);

        // Sets the grabbing servo to it's init position
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

}
