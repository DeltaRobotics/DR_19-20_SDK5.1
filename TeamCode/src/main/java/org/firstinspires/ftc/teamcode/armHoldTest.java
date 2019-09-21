package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "armHoldTest", group = "")
public class armHoldTest extends LinearOpMode
{
    DcMotor motor;

    int targetPosition;

    boolean dDpadLeftState = false;
    boolean dPadRightState = false;
    boolean dPadUpState = false;
    boolean dPadDownState = false;

    double maxMotorPower = 0.5;

    @Override
    public void runOpMode()
    {
        motor = hardwareMap.dcMotor.get("motor");

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        targetPosition = motor.getCurrentPosition();

        motor.setTargetPosition(targetPosition);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        while(opModeIsActive())
        {
            if(gamepad1.dpad_up && !dPadUpState)
            {
                dPadUpState = true;

                if(maxMotorPower < 1.0)
                {
                    maxMotorPower += 0.05;
                }
            }
            else if (!gamepad1.dpad_up)
            {
                dPadUpState = false;
            }

            if(gamepad1.dpad_down && !dPadDownState)
            {
                dPadDownState = true;

                if(maxMotorPower > 0.0)
                {
                    maxMotorPower -= 0.05;
                }
            }
            else if(!gamepad1.dpad_down)
            {
                dPadDownState = false;
            }

            if(gamepad1.dpad_left && !dDpadLeftState)
            {
                dDpadLeftState = true;

                targetPosition += 5;
            }
            else if (!gamepad1.dpad_left)
            {
                dDpadLeftState = false;
            }

            if(gamepad1.dpad_right && !dPadRightState)
            {
                dPadRightState = true;

                targetPosition -= 5;
            }
            else if(!gamepad1.dpad_right)
            {
                dPadRightState = false;
            }

            if(gamepad1.a)
            {
                motor.setTargetPosition(targetPosition);
            }

            motor.setPower(maxMotorPower);

            telemetry.addData("Current Target", motor.getTargetPosition());
            telemetry.addData("Next Target", targetPosition);
            telemetry.addData("Current Position", motor.getCurrentPosition());
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Max Motor Power", maxMotorPower);
            telemetry.update();
        }
    }
}
