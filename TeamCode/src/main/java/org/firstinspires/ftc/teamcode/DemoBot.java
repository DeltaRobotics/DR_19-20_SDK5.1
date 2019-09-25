package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.Drive;
/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="DemoBot",group = "")
public class DemoBot extends LinearOpMode
{
    double zScale = 1.0;
    double speed = 1.0;

    Robot robot = new Robot(hardwareMap);
    //MyDcMotor shooter = null;

    public void runOpMode()
    {

        robot.init();
        //Maps motor objects to name in robot configuration
       // shooter = hardwareMap.dcMotor.get("shooter");

        //shooter.setZeroPowerBehavior(MyDcMotor.ZeroPowerBehavior.BRAKE);


        //int target = 1680 + shooter.getCurrentPosition();

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
            robot.motorRF.setPower(speed * robot.drive.mecanumAlgo(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
            robot.motorRB.setPower(speed * robot.drive.mecanumAlgo(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
            robot.motorLB.setPower(speed * robot.drive.mecanumAlgo(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
            robot.motorLF.setPower(speed * robot.drive.mecanumAlgo(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            //Sends data back to driver station
            telemetry.addData("Motor RF Power", robot.motorRF.getPower());
            telemetry.addData("motor LF power", robot.motorLF.getPower());
            telemetry.addData("Motor RB power", robot.motorRB.getPower());
            telemetry.addData("Motor LB power", robot.motorLB.getPower());
            telemetry.addData("MotorLB Encoder", robot.motorLB.getCurrentPosition());
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
