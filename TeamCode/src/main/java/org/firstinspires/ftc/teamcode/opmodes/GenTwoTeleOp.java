package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;

/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="GenTwoTeleOp",group = "")
public class GenTwoTeleOp extends LinearOpMode
{

    public void runOpMode()
    {
        GenTwoRobot robot = new GenTwoRobot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive())
        {

            robot.drive.driveControl(gamepad1);

            robot.blockMover.blockMoverControl(gamepad2, gamepad1, robot.drive);

            telemetry.addData("Lift Position", robot.blockMover.lift.getCurrentPosition());
            telemetry.addData("Arm Position", robot.blockMover.blockArm.getCurrentPosition());
            telemetry.addData("Arm Target Position", robot.blockMover.blockArm.getTargetPosition());
            telemetry.addData("Arm Power", robot.blockMover.blockArm.getPower());
            telemetry.addData("Arm isBusy()", robot.blockMover.blockArm.isBusy());

            telemetry.addData("Arm Power Variable", robot.blockMover.armPower);

            telemetry.addData("Grabber Position", robot.blockMover.grabber_servo.getPosition());

            telemetry.addData("speed", robot.drive.driveSpeed);


            //  telemetry.addData("shooter", shooter.getCurrentPosition());
            telemetry.update();
        }
    }
}
