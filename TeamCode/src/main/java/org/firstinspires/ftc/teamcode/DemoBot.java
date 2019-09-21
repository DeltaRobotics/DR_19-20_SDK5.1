package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.components.Drive_MK2;

/**
 * Created by User on 4/19/2018.
 */
@TeleOp(name="DemoBot",group = "")
public class DemoBot extends LinearOpMode
{
    double zScale = 1.0;
    double speed = 1.0;

    //This sets the motorXX to MyDcMotor Objects
    DcMotor motorRF = null;
    DcMotor motorLF = null;
    DcMotor motorRB = null;
    DcMotor motorLB = null;
    //MyDcMotor shooter = null;

    Drive_MK2 drive = new Drive_MK2();

    public void runOpMode()
    {
        //Maps motor objects to name in robot configuration
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorLB = hardwareMap.dcMotor.get("motorLB");
       // shooter = hardwareMap.dcMotor.get("shooter");

        //Setting motors to brake when stopped
        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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
            motorRF.setPower(speed * drive.setPower(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
            motorRB.setPower(speed * drive.setPower(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
            motorLB.setPower(speed * drive.setPower(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
            motorLF.setPower(speed * drive.setPower(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);

            //Sends data back to driver station
            telemetry.addData("Motor RF Power", motorRF.getPower());
            telemetry.addData("motor LF power", motorLF.getPower());
            telemetry.addData("Motor RB power", motorRB.getPower());
            telemetry.addData("Motor LB power", motorLB.getPower());
            telemetry.addData("MotorLB Encoder", motorLB.getCurrentPosition());
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
