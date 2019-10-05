package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "DcMotorTest", group = "")
public class DcMotorTest extends LinearOpMode
{
    public void runOpMode()
    {

        DcMotor motor;

        boolean dPadUpState = false;
        boolean dPadDownState = false;

        double maxMotorPower = 0.5;

        motor = hardwareMap.dcMotor.get("motor");

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

                if(maxMotorPower > -1.0)
                {
                    maxMotorPower -= 0.05;
                }
            }
            else if(!gamepad1.dpad_down)
            {
                dPadDownState = false;
            }

            /*if(gamepad1.right_stick_y > maxMotorPower)
            {
                motor.setPower(maxMotorPower);
            }

            if(gamepad1.right_stick_y < -maxMotorPower)
            {
                motor.setPower(-maxMotorPower);
            }

            if(gamepad1.right_stick_y < maxMotorPower && gamepad1.right_stick_y > -maxMotorPower)
            {
                motor.setPower(gamepad1.left_stick_y);
            }*/

            motor.setPower(gamepad1.left_stick_y * maxMotorPower);

            telemetry.addData("Max Motor Power", maxMotorPower);
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Motor Position", motor.getCurrentPosition());
            telemetry.update();

        }
    }
}
