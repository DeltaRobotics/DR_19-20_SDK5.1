package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;

/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="DemoBot",group = "")
public class DemoBot extends LinearOpMode
{
    double zScale = 1.0;
    double speed = 1.0;

    //MyDcMotor shooter = null;

    public void runOpMode()
    {
        Robot robot = new Robot(hardwareMap);

        //Maps motor objects to name in robot configuration
       // shooter = hardwareMap.dcMotor.get("shooter");

        //shooter.setZeroPowerBehavior(MyDcMotor.ZeroPowerBehavior.BRAKE);


        //int target = 1680 + shooter.getCurrentPosition();

        robot.blockMover.blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        robot.blockMover.blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.blockMover.blockArm.setTargetPosition(0);

        robot.blockMover.blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.blockMover.blockArm.setPower(1.0);




        waitForStart();
        while (opModeIsActive())
        {
            /*
            robot.motorRF.setPower(-gamepad1.right_stick_y);
            robot.motorRB.setPower(-gamepad1.right_stick_y);
            robot.motorLF.setPower(gamepad1.left_stick_y);
            robot.motorLB.setPower(gamepad1.left_stick_y);
            */

            //sets motor power according to joystick input
            robot.drive.motorRF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
            robot.drive.motorRB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
            robot.drive.motorLB.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
            robot.drive.motorLF.setPower(speed * robot.drive.teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            robot.blockMover.armControl(gamepad2);

            //Sends data back to driver station
            telemetry.addData("Motor RF Power", robot.drive.motorRF.getPower());
            telemetry.addData("motor LF power", robot.drive.motorLF.getPower());
            telemetry.addData("Motor RB power", robot.drive.motorRB.getPower());
            telemetry.addData("Motor LB power", robot.drive.motorLB.getPower());
            telemetry.addData("MotorLB Encoder", robot.drive.motorLB.getCurrentPosition());

            telemetry.addData("Arm Position", robot.blockMover.blockArm.getCurrentPosition());
            telemetry.addData("Arm Target Position", robot.blockMover.blockArm.getTargetPosition());
            telemetry.addData("Arm Power", robot.blockMover.blockArm.getPower());

          //  telemetry.addData("shooter", shooter.getCurrentPosition());
            telemetry.update();

/*
            if(gamepad1.dpad_left)
            {
                shooter.setPower(0.75);
                while(shooter.getCurrentPosition() < target)
                {
                    sleep(10);
                }
                shooter.setPower(0);
                target += 1680;
            }

 */
    }

    }

}
