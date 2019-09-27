package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.robot.Robot;

@TeleOp (name = "armHoldTest", group = "")
public class armHoldTest extends LinearOpMode
{
    Robot robot = new Robot(hardwareMap);

    DcMotor arm;

    int targetPosition;

    boolean dDpadLeftState = false;
    boolean dPadRightState = false;
    boolean dPadUpState = false;
    boolean dPadDownState = false;

    double maxMotorPower = 0.5;

    @Override
    public void runOpMode()
    {
        arm = hardwareMap.dcMotor.get("arm");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        targetPosition = arm.getCurrentPosition();

        arm.setTargetPosition(targetPosition);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);



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
                arm.setTargetPosition(targetPosition);
            }

            if(gamepad1.a)
            {
                robot.blockMover.grabber.raiseArm();
            }

            if(gamepad1.b)
            {
                robot.blockMover.grabber.lowerArm();
            }

            arm.setPower(maxMotorPower);

            telemetry.addData("Current Target", arm.getTargetPosition());
            telemetry.addData("Next Target", targetPosition);
            telemetry.addData("Current Position", arm.getCurrentPosition());
            telemetry.addData("Motor Power", arm.getPower());
            telemetry.addData("Max Motor Power", maxMotorPower);
            telemetry.addData("FirstMeetBlockMover pos", robot.blockMover.grabber.getServo().getPosition());
            telemetry.update();
        }
    }
}
