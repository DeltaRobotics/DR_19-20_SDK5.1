package org.firstinspires.ftc.teamcode.testPrograms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "DcMotorTest", group = "")
public class motorTest extends LinearOpMode
{

    DcMotor motor;

    double flipperPower = 0.0;
    double maxFlipperPower = 0.20;

    boolean dPadUpState = false;
    boolean dPadDownState = false;

    @Override
    public void runOpMode()
    {
        motor = hardwareMap.dcMotor.get("motor");

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while(opModeIsActive())
        {
            flipperPower = -gamepad1.right_stick_y;

            if(flipperPower > maxFlipperPower)
            {
                flipperPower = maxFlipperPower;
            }

            if(flipperPower < -maxFlipperPower)
            {
                flipperPower = -maxFlipperPower;
            }

            if(gamepad1.dpad_up && !dPadUpState)
            {
                dPadUpState = true;

                if(maxFlipperPower < 1.0)
                {
                    maxFlipperPower += 0.05;
                }
            }
            else if (!gamepad1.dpad_up)
            {
                dPadUpState = false;
            }

            if(gamepad1.dpad_down && !dPadDownState)
            {
                dPadDownState = true;

                if(maxFlipperPower > 0.0)
                {
                    maxFlipperPower -= 0.05;
                }
            }
            else if(!gamepad1.dpad_down)
            {
                dPadDownState = false;
            }

            motor.setPower(flipperPower);

            telemetry.addData("Flipper Power", flipperPower);
            telemetry.addData("Max Flipper Power", maxFlipperPower);
            telemetry.update();
        }
    }
}
