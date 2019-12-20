package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MecanumDriveTrain extends LinearOpMode
{

    private static final double WHEEL_CIRCUMFERENCE_INCHES = 9.276;

    private static final int COUNTS_PER_ROTATION = 560;

    private static final double INCHES_TO_COUNTS = COUNTS_PER_ROTATION / WHEEL_CIRCUMFERENCE_INCHES;



    public DcMotor motorRF;
    public DcMotor motorRB;
    public DcMotor motorLF;
    public DcMotor motorLB;

    public static final double X_DEAD_BAND = 0.1;
    public static final double Y_DEAD_BAND = 0.1;

    public double driveSpeed = 1.0;

    public BNO055IMU imu;

    public Telemetry telemetry;

   /*Argument Breakdown:
     dirX - Represents left joystick X value
     dirY - Represents left joystick Y value
     pivot - Represents right joystick X value
    */

   public MecanumDriveTrain(HardwareMap hardwareMap, Telemetry telemetry)
   {

       motorRF = hardwareMap.dcMotor.get("motorRF");
       motorRB = hardwareMap.dcMotor.get("motorRB");
       motorLF = hardwareMap.dcMotor.get("motorLF");
       motorLB = hardwareMap.dcMotor.get("motorLB");

       this.telemetry = telemetry;

       setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       // IMU Init
       BNO055IMU.Parameters parametersIMU = new BNO055IMU.Parameters(); //Declares parameters object forIMU
       parametersIMU.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Sets the unit in which we measure orientation in degrees
       parametersIMU.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC; //Sets acceleration unit in meters per second ??
       parametersIMU.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode, sets what file the IMU ueses
       parametersIMU.loggingEnabled = true; //Sets wether logging in enable
       parametersIMU.loggingTag = "IMU"; //Sets logging tag
       parametersIMU.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator(); //Sets acceleration integration algorithm
       parametersIMU.temperatureUnit = BNO055IMU.TempUnit.CELSIUS; //Sets units for temperature readings
       imu = hardwareMap.get(BNO055IMU.class, "imu"); //Inits IMU
       imu.initialize(parametersIMU); //Init IMU parameters (set above)

   }

   private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior)
   {
       motorRF.setZeroPowerBehavior(behavior);
       motorLF.setZeroPowerBehavior(behavior);
       motorLB.setZeroPowerBehavior(behavior);
       motorRB.setZeroPowerBehavior(behavior);
   }


   public double[] teleOpDrive(double dirX, double dirY, double pivot)
    {
        // Dead-band for driving straight (accounts for slight movement in the X direction)
        if(dirX >= -X_DEAD_BAND && dirX <= X_DEAD_BAND)
        {
            dirX = 0;
        }

        // Dead-band for driving straight (accounts for slight movement in the Y direction)
        if(dirY >= -Y_DEAD_BAND && dirY <= Y_DEAD_BAND)
        {
            dirY = 0;
        }

        //Array is used to store motors so they can be easily accessed in the method call based on the return value
        double[] motorPowers = new double[4];
        motorPowers[0] = -(dirY + dirX) - pivot;//robot.motorRF.setPower(speed*((-gamepad1.left_stick_y - gamepad1.left_stick_x) - (zScale * gamepad1.right_stick_x)));
        motorPowers[1] = (dirX - dirY) - pivot;//robot.motorRB.setPower(speed*(-(-gamepad1.left_stick_x + gamepad1.left_stick_y) - (zScale * gamepad1.right_stick_x)));
        motorPowers[2] = (dirY + dirX) - pivot;//robot.motorLB.setPower(speed*((gamepad1.left_stick_y + gamepad1.left_stick_x) - (zScale * gamepad1.right_stick_x)));
        motorPowers[3] = (-dirX + dirY) - pivot;//robot.motorLF.setPower(speed*((-gamepad1.left_stick_x + gamepad1.left_stick_y)) - (zScale * gamepad1.right_stick_x));

        //References
            //motorPowers[0] = motorRF
            //motorPowers[1] = motorRB
            //motorPowers[2] = motorLB
            //motorPowers[3] = motorLF

        // Left side is reversed -1 power = forward
        // Right side 1 power = forward

        return motorPowers;
    }

    public void driveControl(Gamepad gamepad1)
    {
        if (gamepad1.dpad_up)
        {
            driveSpeed = 1.0;
        }

        if (gamepad1.dpad_down)
        {
            driveSpeed = 0.5;
        }

        //sets motor power according to joystick input
        motorRF.setPower(driveSpeed * teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[0]);
        motorRB.setPower(driveSpeed * teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[1]);
        motorLB.setPower(driveSpeed * teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[2]);
        motorLF.setPower(driveSpeed * teleOpDrive(gamepad1.right_stick_x, gamepad1.right_stick_y, gamepad1.left_stick_x)[3]);
    }

    //Method that drives the robot via encoder target values and what the current encoder value of the motors are

    /*
    Argument Breakdown:
    encoderDelta - Desired total change of the starting encoder value. How far the robot will go via encoder count readings
    driveStyle - Desired direction the robot will drive. Uses enumeration declared at the top of the class
    motorPower - Desired motor power the firstMeetMecanum motors will run at
    motors - Array that contains the firstMeetMecanum motors. This is passed in so we can use the motors from an outside class (OpMode) in this class
     */
    public boolean encoderDriveInches(double distanceInches, driveStyle drive, double motorPower)
    {


        double encoderDelta = distanceInches * INCHES_TO_COUNTS;

        //Comments in FORWARD also apply for all the other cases in this method

        //Switch statement used to handle which driveStyle enumeration was selected
        switch(drive)
        {
            //If desired firstMeetMecanum direction was forward
            case FORWARD:
            {
                //Declares a sets a variable for the starting encoder value on a specific motor
                double encoderReadingLB = motorRB.getCurrentPosition();
                //Calculates desired encoder value by adding/subtracting the reading taken above by the desired encoder delta
                double target = (encoderReadingLB + encoderDelta);

                //Method declaration that will set the correct motor powers to move the robot the desired direction (based on which case you are in) with desired motor power
                forward(motorPower);

                /*
                Loop that haults the code from progressing till the desired encoder count is met.
                This desired encoder value could either be positive or negative, so the appropriate logic is applied.
                */
                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;


            }

            case BACKWARD:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB - encoderDelta);
                backward(motorPower);

                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case STRAFE_LEFT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                strafeLeft(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case STRAFE_RIGHT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                strafeRight(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case FORWARD_LEFT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB - encoderDelta);
                forwardLeft(motorPower);
                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case FORWARD_RIGHT:
            {
                double encoderReadingRB = motorRF.getCurrentPosition();
                double target = (encoderReadingRB + encoderDelta);
                forwardRight(motorPower);

                while (motorRF.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case BACKWARD_LEFT:
            {
                double encoderReadingRB = motorRF.getCurrentPosition();
                double target = (encoderDelta - encoderReadingRB);
                backwardLeft(motorPower);

                while (motorRF.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case BACKWARD_RIGHT:
            {
                double encoderReadingLB =motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                backwardRight(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case PIVOT_LEFT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                pivotLeft(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case PIVOT_RIGHT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderDelta - encoderReadingLB);
                pivotRight(motorPower);

                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }


        }

        //Stops all the motors
        stopMotors();

       //Return value to see if the method was successfully executed
       return true;
    }

    public boolean encoderDrive(double encoderDelta, driveStyle drive, double motorPower)
    {

        //Comments in FORWARD also apply for all the other cases in this method

        //Switch statement used to handle which driveStyle enumeration was selected
        switch(drive)
        {
            //If desired firstMeetMecanum direction was forward
            case FORWARD:
            {
                //Declares a sets a variable for the starting encoder value on a specific motor
                double encoderReadingLB = motorRB.getCurrentPosition();
                //Calculates desired encoder value by adding/subtracting the reading taken above by the desired encoder delta
                double target = (encoderReadingLB + encoderDelta);

                //Method declaration that will set the correct motor powers to move the robot the desired direction (based on which case you are in) with desired motor power
                forward(motorPower);

                /*
                Loop that haults the code from progressing till the desired encoder count is met.
                This desired encoder value could either be positive or negative, so the appropriate logic is applied.
                */
                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;


            }

            case BACKWARD:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB - encoderDelta);
                backward(motorPower);

                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case STRAFE_LEFT:
            {
                //double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (motorRB.getCurrentPosition() - encoderDelta);
                strafeLeft(motorPower);

                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case STRAFE_RIGHT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                strafeRight(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case FORWARD_LEFT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB - encoderDelta);
                forwardLeft(motorPower);
                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case FORWARD_RIGHT:
            {
                double encoderReadingRB = motorRF.getCurrentPosition();
                double target = (encoderReadingRB + encoderDelta);
                forwardRight(motorPower);

                while (motorRF.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case BACKWARD_LEFT:
            {
                double encoderReadingRB = motorRF.getCurrentPosition();
                double target = (encoderDelta - encoderReadingRB);
                backwardLeft(motorPower);

                while (motorRF.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case BACKWARD_RIGHT:
            {
                double encoderReadingLB =motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                backwardRight(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case PIVOT_LEFT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderReadingLB + encoderDelta);
                pivotLeft(motorPower);

                while (motorRB.getCurrentPosition() <= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }

            case PIVOT_RIGHT:
            {
                double encoderReadingLB = motorRB.getCurrentPosition();
                double target = (encoderDelta - encoderReadingLB);
                pivotRight(motorPower);

                while (motorRB.getCurrentPosition() >= target)
                {
                    telemetry.addData("Current Position", motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                break;
            }


        }

        //Stops all the motors
        stopMotors();

        //Return value to see if the method was successfully executed
        return true;
    }


    public void timeDrive(long time, double motorPower, driveStyle drive)
    {
        switch(drive)
        {
            case FORWARD:
                {
                    forward(motorPower);

                    sleep(time);

                    break;
                }

            case BACKWARD:
            {
                backward(motorPower);

                sleep(time);

                break;

            }

            case STRAFE_LEFT:
            {
                strafeLeft(motorPower);

                sleep(time);

                break;
            }

            case STRAFE_RIGHT:
            {
                strafeRight(motorPower);

                sleep(time);

                break;
            }

            case FORWARD_LEFT:
            {
                forwardLeft(motorPower);

                sleep(time);

                break;
            }

            case FORWARD_RIGHT:
            {
                forwardRight(motorPower);


                sleep(time);

                break;
            }

            case BACKWARD_LEFT:
            {
                backwardLeft(motorPower);

                sleep(time);

                break;
            }

            case BACKWARD_RIGHT:
            {
                backwardRight(motorPower);

                sleep(time);

                break;
            }

            case PIVOT_LEFT:
            {
                pivotLeft(motorPower);




                sleep(time);

                break;
            }

            case PIVOT_RIGHT:
            {
                pivotRight(motorPower);

                sleep(time);

                break;
            }
        }


        //Stops all the motors
        stopMotors();

    }
    public void OrientationDrive(double TargetOr, double motorPower, BNO055IMU imu) {
        Orientation angles;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double PivotDeg;
        PivotDeg = (TargetOr - AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));

        if (PivotDeg > 0)
        {
            pivotLeft(motorPower);

            while (TargetOr > AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle))
            {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("Orientation Target", TargetOr);
                telemetry.addData("Current Orientation", AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
                telemetry.update();
            }
            //Stops all the motors
            stopMotors();
        }
        else
        {
            pivotRight(motorPower);

            while (TargetOr < AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle))
            {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                telemetry.addData("Orientation Target", TargetOr);
                telemetry.addData("Current Orientation", AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
                telemetry.update();
            }

            //Stops all the motors
            stopMotors();

        }
    }


    private void forward(double motorPower)
    {
        motorRF.setPower(teleOpDrive(0, -motorPower, 0)[0]);
        motorRB.setPower(teleOpDrive(0, -motorPower, 0)[1]);
        motorLB.setPower(teleOpDrive(0, -motorPower, 0)[2]);
        motorLF.setPower(teleOpDrive(0, -motorPower, 0)[3]);
    }
    private void backward(double motorPower)
    {
        motorRF.setPower(teleOpDrive(0, motorPower, 0)[0]);
        motorRB.setPower(teleOpDrive(0, motorPower, 0)[1]);
        motorLB.setPower(teleOpDrive(0, motorPower, 0)[2]);
        motorLF.setPower(teleOpDrive(0, motorPower, 0)[3]);
    }
    private void strafeLeft(double motorPower)
    {
        motorRF.setPower(teleOpDrive(-motorPower, 0, 0)[0]);
        motorRB.setPower(teleOpDrive(-motorPower, 0, 0)[1]);
        motorLB.setPower(teleOpDrive(-motorPower, 0, 0)[2]);
        motorLF.setPower(teleOpDrive(-motorPower, 0, 0)[3]);
    }
    private void strafeRight(double motorPower)
    {
        motorRF.setPower(teleOpDrive(motorPower, 0, 0)[0]);
        motorRB.setPower(teleOpDrive(motorPower, 0, 0)[1]);
        motorLB.setPower(teleOpDrive(motorPower, 0, 0)[2]);
        motorLF.setPower(teleOpDrive(motorPower, 0, 0)[3]);
    }
    private void forwardLeft(double motorPower)
    {
        motorRF.setPower(teleOpDrive(-motorPower, -motorPower, 0)[0]);
        motorRB.setPower(teleOpDrive(-motorPower, -motorPower, 0)[1]);
        motorLB.setPower(teleOpDrive(-motorPower, -motorPower, 0)[2]);
        motorLF.setPower(teleOpDrive(-motorPower, -motorPower, 0)[3]);
    }
    private void forwardRight(double motorPower)
    {
        motorRF.setPower(teleOpDrive(motorPower, -motorPower, 0)[0]);
        motorRB.setPower(teleOpDrive(motorPower, -motorPower, 0)[1]);
        motorLB.setPower(teleOpDrive(motorPower, -motorPower, 0)[2]);
        motorLF.setPower(teleOpDrive(motorPower, -motorPower, 0)[3]);
    }
    private void backwardLeft(double motorPower)
    {
       motorRF.setPower(teleOpDrive(-motorPower, motorPower, 0)[0]);
       motorRB.setPower(teleOpDrive(-motorPower, motorPower, 0)[1]);
       motorLB.setPower(teleOpDrive(-motorPower, motorPower, 0)[2]);
       motorLF.setPower(teleOpDrive(-motorPower, motorPower, 0)[3]);
    }
    private void backwardRight(double motorPower)
    {
       motorRF.setPower(teleOpDrive(motorPower, motorPower, 0)[0]);
       motorRB.setPower(teleOpDrive(motorPower, motorPower, 0)[1]);
       motorLB.setPower(teleOpDrive(motorPower, motorPower, 0)[2]);
       motorLF.setPower(teleOpDrive(motorPower, motorPower, 0)[3]);
    }
    private void pivotLeft(double motorPower)
    {
        motorRF.setPower(teleOpDrive(0, 0, -motorPower)[0]);
        motorRB.setPower(teleOpDrive(0, 0, -motorPower)[1]);
        motorLB.setPower(teleOpDrive(0, 0, -motorPower)[2]);
        motorLF.setPower(teleOpDrive(0, 0, -motorPower)[3]);

    }
    private void pivotRight(double motorPower)
    {
        motorRF.setPower(teleOpDrive(0, 0, motorPower)[0]);
        motorRB.setPower(teleOpDrive(0, 0, motorPower)[1]);
        motorLB.setPower(teleOpDrive(0, 0, motorPower)[2]);
        motorLF.setPower(teleOpDrive(0, 0, motorPower)[3]);
    }

    private void stopMotors()
    {
        //Stops all the motors
        motorRF.setPower(teleOpDrive(0, 0, 0)[0]);
        motorRB.setPower(teleOpDrive(0, 0, 0)[1]);
        motorLB.setPower(teleOpDrive(0, 0, 0)[2]);
        motorLF.setPower(teleOpDrive(0, 0, 0)[3]);
    }

    @Override
    public void runOpMode()
    {}
}
