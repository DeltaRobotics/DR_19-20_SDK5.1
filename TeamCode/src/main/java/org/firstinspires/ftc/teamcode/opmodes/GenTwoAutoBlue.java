package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.deltacamera.CameraBox;
import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.RGBAverage;
import org.firstinspires.ftc.teamcode.deltacamera.SkystoneCameraEval;
import org.firstinspires.ftc.teamcode.deltacamera.SkystonePositions;
import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.GenTwoBlockMover;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous (name = "GenTwoAutoBlue", group = "Auto")
public class GenTwoAutoBlue extends LinearOpModeCamera
{
    public void runOpMode()
    {
        int sleepTime = 150;

        Orientation angles;

        GenTwoRobot robot = new GenTwoRobot(hardwareMap, telemetry);


        CameraUtil cameraUtil = new CameraUtil(this);

        SkystoneCameraEval skystoneCameraEval = new SkystoneCameraEval();

        SkystonePositions position = SkystonePositions.NOT_FOUND;

        cameraUtil.setCameraDownsampling(1);

        cameraUtil.startCamera();

        waitForStart();

        robot.drive.encoderDrive(200, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.OrientationDrive(-70, 0.5, robot.drive.imu);

        sleep(500);

        Bitmap image = cameraUtil.takePicture();

        CameraBox box1 = cameraUtil.drawBox(585, 375, 1220, 1032, image, new RGBAverage(255, 0, 0)); // Left skystone

        CameraBox box2 = cameraUtil.drawBox(232, 16, 1214, 1029, image, new RGBAverage(0, 0, 255)); // Center skystone

        cameraUtil.saveImage(image);

        box1 = cameraUtil.getBoxAverage(box1);
        box2 = cameraUtil.getBoxAverage(box2);


        cameraUtil.stopCamera();

        // First arg needs to be the left skystone box
        // Second arg needs to center skystone box
        position = skystoneCameraEval.getSkystonePosition(box1, box2);

        // DUE TO HOW THE ROBOT IS PLACED ON THE BLUE SIDE, ONLY THE RIGHT AND CENTER SKYSTONES CAN BE ANALYZED
        // THUS, THIS DOES NOT WORK WITH THE EXISTING CODE (YOU USUALLY PASS IN THE CENTER AND LEFT SKYSTONES) SO IT IS REVERSED
        //THE CODE BELOW SWAPS THE TWO
        if(position == SkystonePositions.LEFT)
        {
            position = SkystonePositions.RIGHT;
        }
        else if(position == SkystonePositions.RIGHT)
        {
            position = SkystonePositions.LEFT;
        }

        telemetry.addData("Position", position);
        telemetry.addData("Left Average", box1.average.red - box1.average.blue);
        telemetry.addData("Center Average", box2.average.red - box2.average.blue);

        telemetry.update();

        sleep(500);

        robot.drive.OrientationDrive(-85, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(1050, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        switch(position)
        {
            case LEFT:
            {
                robot.drive.OrientationDrive(-30, 0.5, robot.drive.imu);

                sleep(sleepTime);

                robot.drive.encoderDrive(50, driveStyle.FORWARD, 0.75);
                break;
            }

            case CENTER:
            {
                robot.drive.OrientationDrive(-30, 0.5, robot.drive.imu);

                sleep(sleepTime);

                robot.drive.encoderDrive(200, driveStyle.BACKWARD, 0.75);
                break;
            }

            case RIGHT:
            {
                robot.drive.OrientationDrive(-15, 0.5, robot.drive.imu);

                sleep(sleepTime);

                robot.drive.encoderDrive(900, driveStyle.BACKWARD, 0.75);
                break;
            }
        }

        robot.blockMover.moveArm(300, 1.0, 5, "Moving arm to collect position", telemetry);

        sleep(sleepTime);

        robot.blockMover.intake_In();

        sleep(500);

        robot.drive.encoderDrive(1100, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.encoderDrive(600, driveStyle.FORWARD, 0.2);

        sleep(1000);

        robot.blockMover.intake_Stop();

        sleep(sleepTime);

        robot.blockMover.openGrabber();

        sleep(sleepTime);

        robot.blockMover.moveArm(0, 0.5, 5, "Moving arm down", telemetry);

        sleep(sleepTime);

        robot.blockMover.closeGrabber();

        sleep(sleepTime);

        robot.drive.encoderDrive(800, driveStyle.STRAFE_LEFT, 0.85);

        sleep(sleepTime);

        angles = robot.drive.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle) < -10)
        {
            robot.drive.OrientationDrive(-10, 0.5, robot.drive.imu);

            sleep(sleepTime);
        }


        switch(position)
        {
            case LEFT:
            {
                robot.drive.encoderDrive(4300, driveStyle.FORWARD, 1.0);
                break;
            }
            case CENTER:
            {
                robot.drive.encoderDrive(4575, driveStyle.FORWARD, 1.0);
                break;
            }

            case RIGHT:
            {
                robot.drive.encoderDrive(5275, driveStyle.FORWARD, 1.0);
                break;
            }

        }

        sleep(sleepTime);

        robot.drive.encoderDrive(800, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);

        robot.drive.OrientationDrive(20, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(1000, driveStyle.STRAFE_LEFT, 0.90);

        sleep(sleepTime);

        robot.drive.OrientationDrive(80, 0.75, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(200,driveStyle.FORWARD,0.5);

        robot.blockMover.foundationUp();

        sleep(sleepTime);

        robot.drive.encoderDrive(75, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(165, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.timeDrive(1000, 0.6, driveStyle.BACKWARD);

        sleep(sleepTime);

        sleep(sleepTime);

        robot.blockMover.moveArm(1100, 0.5, 5, "Moving to place Skystone", telemetry);

        sleep(sleepTime);

        robot.blockMover.openGrabber();

        sleep(1000);

        robot.blockMover.moveArm(GenTwoBlockMover.TRAVEL_POSITION, 0.5, 5 , "Moving arm to travel position", telemetry);

        //sleep(sleepTime);

        //robot.drive.encoderDrive(200, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.blockMover.moveArm(0, 0.5, 5, "Moving arm down", telemetry);

        robot.drive.timeDrive(1200, 0.8, driveStyle.FORWARD);

    }
}
