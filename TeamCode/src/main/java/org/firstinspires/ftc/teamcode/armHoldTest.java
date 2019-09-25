package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp (name = "armHoldTest", group = "")
public class armHoldTest extends LinearOpMode
{
    Robot robot = new Robot(hardwareMap);


    int targetPosition;

    boolean dDpadLeftState = false;
    boolean dPadRightState = false;
    boolean dPadUpState = false;
    boolean dPadDownState = false;

    double maxMotorPower = 0.5;

    @Override
    public void runOpMode()
    {
        robot.init();

        robot.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        targetPosition = robot.arm.getCurrentPosition();

        robot.arm.setTargetPosition(targetPosition);

        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


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
                robot.arm.setTargetPosition(targetPosition);
            }

            if(gamepad1.a)
            {
                robot.grabber.raiseArm();
            }

            if(gamepad1.b)
            {
                robot.grabber.lowerArm();
            }

            robot.arm.setPower(maxMotorPower);

            telemetry.addData("Current Target", robot.arm.getTargetPosition());
            telemetry.addData("Next Target", targetPosition);
            telemetry.addData("Current Position", robot.arm.getCurrentPosition());
            telemetry.addData("Motor Power", robot.arm.getPower());
            telemetry.addData("Max Motor Power", maxMotorPower);
            telemetry.addData("Grabber pos", robot.grabber.getServo().getPosition());
            telemetry.update();
        }
    }
}
