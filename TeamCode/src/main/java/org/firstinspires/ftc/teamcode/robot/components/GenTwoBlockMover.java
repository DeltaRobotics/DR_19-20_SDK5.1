package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GenTwoBlockMover
{

    public Servo capstone;

    public  Servo foundation_mover;

    public Servo grabber_servo;

    public DcMotor blockArm;

    public Servo intake_left;

    public Servo intake_right;

    public DcMotor lift_left;

    public DcMotor lift_right;

    public double armPower = 0;

    public int lift_level = 0;

    public boolean dpad_up_state = false;

    public static final double DEFAULT_ARM_POWER = 0.3;
    public static final double HOLD_POWER = 1.0; // Should ALWAYS be different from the other arm power variables
    public static final double PRESET_POSITION_POWER = 0.3;
    public static final int POSITION_DELTA = 15;
    public static final int GRAB_POSITION = 0;
    public static final int TRAVEL_POSITION = 250;
    public static final int DELIVER_POSITION = 950;


    public static final double GRABBER_OPEN = 0.9;
    public static final double GRABBER_INIT = 0.9;
    public static final double GRABBER_CLOSE = 0.45;
    public static final double FOUNDATION_MOVER_OPEN = 0.5;
    public static final double FOUNDATION_MOVER_CLOSE = 0.1;
    public static final double CAPSTONE_OPEN = 0.1;
    public static final double CAPSTONE_CLOSE = 0.99;

    public static final int LIFT_MAX_POSITION = -7000;

    public static final int LIFT_MIN_POSITION = -200;

    public static final int[] LIFT_LEVEL_POSITIONS = new int[]{0, 400, 800, 1200, 1600};

    public LinearOpMode linearOpMode;

    // Constructor/Init
    public GenTwoBlockMover(LinearOpMode linearOpMode)
    {
        capstone = linearOpMode.hardwareMap.servo.get("capstone");

        foundation_mover = linearOpMode.hardwareMap.servo.get("foundationMover");

        grabber_servo = linearOpMode.hardwareMap.servo.get("grabber");

        intake_left = linearOpMode.hardwareMap.servo.get("intakeL");

        intake_right = linearOpMode.hardwareMap.servo.get("intakeR");

        blockArm = linearOpMode.hardwareMap.dcMotor.get("blockArm");

        lift_left = linearOpMode.hardwareMap.dcMotor.get("lift_left");

        lift_right = linearOpMode.hardwareMap.dcMotor.get("lift_right");

        lift_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        lift_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lift_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        blockArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        blockArm.setTargetPosition(blockArm.getCurrentPosition());

        blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        blockArm.setPower(1.0);

        grabber_servo.setPosition(GRABBER_INIT);

        foundation_mover.setPosition(FOUNDATION_MOVER_OPEN);

        capstone.setPosition(CAPSTONE_OPEN);

        this.linearOpMode = linearOpMode;

        intake_Stop();

    }


    public void armControl(MecanumDriveTrain drive, double speed)
    {

        if(linearOpMode.gamepad2.dpad_up && !dpad_up_state && lift_level < LIFT_LEVEL_POSITIONS.length)
        {
            dpad_up_state = true;

            lift_level += 1;

        }
        else if(!linearOpMode.gamepad2.dpad_up)
        {
            dpad_up_state = false;
        }

        if(linearOpMode.gamepad2.back)
        {
            closeGrabber();

            if(blockArm.getCurrentPosition() < TRAVEL_POSITION)
            {
                moveArm(TRAVEL_POSITION, 0.3, 5, "Moving arm to travel", linearOpMode.telemetry);
            }

            if((lift_left.getCurrentPosition() > LIFT_LEVEL_POSITIONS[lift_level]))
                {
                lift_left.setPower(-0.75);
                lift_right.setPower(-0.75);

                while(lift_left.getCurrentPosition() > (LIFT_LEVEL_POSITIONS[lift_level]) && !linearOpMode.gamepad2.dpad_down && lift_left.getCurrentPosition() > LIFT_MAX_POSITION && lift_left.getCurrentPosition() < LIFT_MIN_POSITION)
                {

                    if (linearOpMode.gamepad1.left_trigger > 0.2)
                    {

                        speed = 1.0;
                    }
                    else
                    {

                        speed = 0.4;

                    }

                    //sets motor power according to joystick input
                    drive.motorRF.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[0]);
                    drive.motorRB.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[1]);
                    drive.motorLB.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[2]);
                    drive.motorLF.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[3]);

                    //robot.blockMover.blockArm.setPower(gamepad2.left_stick_y);


                    if (linearOpMode.gamepad1.left_trigger > 0.2)
                    {

                        speed = 1.0;
                    }
                    else
                    {

                        speed = 0.4;

                    }

                    if (linearOpMode.gamepad2.a)
                    {
                        openGrabber();
                    }

                    if (linearOpMode.gamepad2.b)
                    {
                        closeGrabber();
                    }

                    if(linearOpMode.gamepad1.left_bumper)
                    {
                        foundationDown();
                    }

                    if(linearOpMode.gamepad1.right_bumper)
                    {
                        foundationUp();
                    }

                    if (linearOpMode.gamepad1.right_trigger > 0.2)
                    {
                        intake_In();
                    }
                    else
                    {

                        intake_Stop();

                    }

                    if (linearOpMode.gamepad1.y)
                    {
                        intake_Out();
                    }


                    if (linearOpMode.gamepad2.dpad_left)
                    {
                        capstone_on();
                    }

                    if (linearOpMode.gamepad2.dpad_right)
                    {
                        capstone_off();
                    }

                    linearOpMode.telemetry.addData("Moving to lift level " + lift_level + ". At position: ", lift_left.getCurrentPosition());
                    linearOpMode.telemetry.update();
                }
            }

                lift_left.setPower(0);
                lift_right.setPower(0);

                lift_level = 0;

        }

        if(linearOpMode.gamepad1.dpad_down)
        {
            lift_level = 0;
        }




        if (linearOpMode.gamepad2.left_stick_y == 0 && armPower != HOLD_POWER && !blockArm.isBusy())
        {
            armPower = HOLD_POWER;
        }
        else if (linearOpMode.gamepad2.left_stick_y < 0)
        {
            blockArm.setTargetPosition(blockArm.getTargetPosition() - POSITION_DELTA);
            armPower = DEFAULT_ARM_POWER;
        } else if (linearOpMode.gamepad2.left_stick_y > 0)
        {
            blockArm.setTargetPosition(blockArm.getTargetPosition() + POSITION_DELTA);
            armPower = DEFAULT_ARM_POWER;
        }

        if (linearOpMode.gamepad2.right_trigger > 0.5)
        {
            openGrabber();

            armPower = PRESET_POSITION_POWER;
            blockArm.setTargetPosition(GRAB_POSITION);
        }

        if (linearOpMode.gamepad2.left_trigger > 0.5)
        {
            closeGrabber();

            armPower = PRESET_POSITION_POWER;
            blockArm.setTargetPosition(DELIVER_POSITION);
        }

        if (linearOpMode.gamepad2.right_bumper)
        {
            armPower = PRESET_POSITION_POWER;

            openGrabber();

            blockArm.setTargetPosition(TRAVEL_POSITION);
            while (blockArm.isBusy() && !linearOpMode.gamepad2.dpad_down) {

                //sets motor power according to joystick input
               drive.motorRF.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[0]);
               drive.motorRB.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[1]);
               drive.motorLB.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[2]);
               drive.motorLF.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[3]);

                //robot.blockMover.blockArm.setPower(gamepad2.left_stick_y);


                if (linearOpMode.gamepad1.left_trigger > 0.2)
                {

                    speed = 1.0;
                }
                else
                {

                    speed = 0.4;

                }

                if (linearOpMode.gamepad2.a)
                {
                    openGrabber();
                }

                if (linearOpMode.gamepad2.b)
                {
                    closeGrabber();
                }

                if(linearOpMode.gamepad1.left_bumper)
                {
                    foundationDown();
                }

                if(linearOpMode.gamepad1.right_bumper)
                {
                    foundationUp();
                }

                if (linearOpMode.gamepad1.right_trigger > 0.2)
                {
                    intake_In();
                }
                else
                {

                    intake_Stop();

                }

                if (linearOpMode.gamepad1.y)
                {
                   intake_Out();
                }


                if (linearOpMode.gamepad2.dpad_left)
                {
                    capstone_on();
                }

                if (linearOpMode.gamepad2.dpad_right)
                {
                    capstone_off();
                }
            }

            lift_left.setPower(0.75);
            lift_right.setPower(0.75);
            while (lift_left.getCurrentPosition() < -50 && !linearOpMode.gamepad2.dpad_down)
            {

                if (linearOpMode.gamepad1.left_trigger > 0.2)
                {

                    speed = 1.0;
                }
                else
                {

                    speed = 0.4;

                }

                if (linearOpMode.gamepad1.left_trigger > 0.2)
                {

                    speed = 1.0;
                }
                else
                {

                    speed = 0.4;

                }

                //sets motor power according to joystick input
                //sets motor power according to joystick input
                drive.motorRF.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[0]);
                drive.motorRB.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[1]);
                drive.motorLB.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[2]);
                drive.motorLF.setPower(speed *drive.teleOpDrive(linearOpMode.gamepad1.right_stick_x, linearOpMode.gamepad1.right_stick_y, linearOpMode.gamepad1.left_stick_x)[3]);


                if (linearOpMode.gamepad2.a)
                {
                    openGrabber();
                }

                if (linearOpMode.gamepad2.b)
                {
                    closeGrabber();
                }

                if(linearOpMode.gamepad1.left_bumper)
                {
                    foundationDown();
                }

                if(linearOpMode.gamepad1.right_bumper)
                {
                    foundationUp();
                }

                if (linearOpMode.gamepad1.right_trigger > 0.2)
                {
                    intake_In();
                }
                else
                {

                    intake_Stop();

                }

                if (linearOpMode.gamepad1.y)
                {
                    intake_Out();
                }


                if (linearOpMode.gamepad2.dpad_left)
                {
                    capstone_on();
                }

                if (linearOpMode.gamepad2.dpad_right)
                {
                    capstone_off();
                }
            }
            lift_left.setPower(0);
            lift_right.setPower(0);

        }

        blockArm.setPower(armPower);


        // Lift control
        if ((lift_left.getCurrentPosition() >= LIFT_MAX_POSITION || linearOpMode.gamepad2.right_stick_y > 0) && (lift_left.getCurrentPosition() <= LIFT_MIN_POSITION || linearOpMode.gamepad2.right_stick_y < 0))
        {
            lift_left.setPower(linearOpMode.gamepad2.right_stick_y);
            lift_right.setPower(linearOpMode.gamepad2.right_stick_y);
        }
        else
        {
            lift_left.setPower(0);
            lift_right.setPower(0);
        }

        if(linearOpMode.gamepad2.y)
        {
            blockArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            blockArm.setTargetPosition(0);

            blockArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }



    public void moveArm(int target, double power, int tolerance, String message, Telemetry telemetry)
    {
        int delta = blockArm.getTargetPosition() - target;

        blockArm.setPower(power);

        blockArm.setTargetPosition(target);

        if(delta < 0)
        {
            while(blockArm.getCurrentPosition() < blockArm.getTargetPosition() - tolerance && linearOpMode.opModeIsActive())
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
            while(blockArm.getCurrentPosition() > blockArm.getTargetPosition() + tolerance && linearOpMode.opModeIsActive())
            {
                telemetry.addData("Arm Movement", message);
                telemetry.addData("Current Position", blockArm.getCurrentPosition());
                telemetry.addData("Target Position", blockArm.getTargetPosition());
                telemetry.addData("Tolerance", tolerance);
                telemetry.update();
            }
        }
    }


    public void foundationUp()
    {
        foundation_mover.setPosition(FOUNDATION_MOVER_OPEN);
    }

    public  void foundationDown()
    {
        foundation_mover.setPosition(FOUNDATION_MOVER_CLOSE);
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

    public void intake_In()
    {
        intake_left.setPosition(.9);
        intake_right.setPosition(.04);
    }

    public void intake_Out()
    {
        intake_left.setPosition(.04);
        intake_right.setPosition(.9);
    }

    public void intake_Stop()
    {
        intake_left.setPosition(.5);
        intake_right.setPosition(.5);
    }

    public void capstone_on()
    {
        closeGrabber();

        linearOpMode.sleep(200);

        capstone.setPosition(CAPSTONE_CLOSE);
    }

    public  void capstone_off()
    {
        capstone.setPosition(CAPSTONE_OPEN);
    }
}
