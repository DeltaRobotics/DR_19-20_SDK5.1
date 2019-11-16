package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.deltacamera.CameraBox;
import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.RGBAverage;
import org.firstinspires.ftc.teamcode.deltacamera.SkystoneCameraEval;
import org.firstinspires.ftc.teamcode.deltacamera.SkystonePositions;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.FirstMeetBlockMover;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name = "ThirdMeetAutoRed", group = "")
public class ThirdMeetAutoRed extends LinearOpModeCamera
{
    public void runOpMode()
    {

        int sleepTime = 250;

        Robot robot = new Robot(hardwareMap);


        CameraUtil cameraUtil = new CameraUtil(this);

        SkystoneCameraEval skystoneCameraEval = new SkystoneCameraEval();

        SkystonePositions position = SkystonePositions.NOT_FOUND;

        cameraUtil.setCameraDownsampling(1);

        cameraUtil.startCamera();

        waitForStart();

        Bitmap image = cameraUtil.takePicture();


        CameraBox box1 = cameraUtil.drawBox(543, 296, 911, 784, image, new RGBAverage(255, 0, 0)); // Left skystone

        CameraBox box2 = cameraUtil.drawBox(815, 593, 919, 784, image, new RGBAverage(0, 0, 255)); // Center skystone

        cameraUtil.saveImage(image);

        box1 = cameraUtil.getBoxAverage(box1);
        box2 = cameraUtil.getBoxAverage(box2);


        cameraUtil.stopCamera();

        // First arg needs to be the left skystone box
        // Second arg needs to center skystone box
        position = skystoneCameraEval.getSkystonePosition(box1, box2);


        telemetry.addData("Position", position);

        telemetry.update();
        sleep(1000);

        // Strafes away from wall
        robot.drive.encoderDrive(900, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        // Positions robot parallel with the skystone
        switch(position)
        {
            case LEFT:
            {
                robot.drive.encoderDrive(575,driveStyle.BACKWARD,0.4);
                break;
            }

            case CENTER:
            {
                robot.drive.encoderDrive(175,driveStyle.BACKWARD,0.4);
                break;
            }

            case RIGHT:
            {
                robot.drive.encoderDrive(575,driveStyle.BACKWARD,0.4);
                break;
            }
        }

        // Moves arm down to grab skystone
        robot.blockMover.moveArm(4200, 1.0, 100, "Moving to grab skystone", telemetry);

        sleep(sleepTime);

        // Drives arm into skystone
        robot.drive.encoderDrive(175, driveStyle.STRAFE_RIGHT, 0.4);

        sleep(sleepTime);

        // Grabs skystone
        robot.blockMover.closeGrabber();

        sleep(1000);

        // Moves arm up
        robot.blockMover.moveArm(3550, 0.4, 100, "Moving skystone up", telemetry);

        sleep(1000);

        // Rotates perpendicular with skybridge
        robot.drive.OrientationDrive(85, 0.25, robot.drive.imu);

        // Moves away from quarry (skystone group)
        robot.drive.encoderDrive(100, driveStyle.FORWARD, 0.4);

        // Lowers arm so it fits under skybridge
        robot.blockMover.moveArm(3700, 0.65, 50, "Lowering arm slightly", telemetry);


        sleep(sleepTime);

        // Strafes to foundation
        switch(position)
        {
            case RIGHT:
            {
                robot.drive.encoderDrive(4800, driveStyle.STRAFE_LEFT,0.8);
                break;
            }

            case CENTER:
            {
                robot.drive.encoderDrive(5500, driveStyle.STRAFE_LEFT,0.8);
                break;
            }

            case LEFT:
            {
                robot.drive.encoderDrive(4800, driveStyle.STRAFE_LEFT,0.8);
                break;
            }
        }

        sleep(sleepTime);

        // Rotates robot parallel with foundation
        robot.drive.OrientationDrive(0, 0.25, robot.drive.imu);

        sleep(sleepTime);


        // Strafes into/close to the foundation
        //robot.drive.encoderDrive(200, driveStyle.STRAFE_RIGHT, 0.8);

        //sleep(sleepTime);

        // Drops skystone
        robot.blockMover.openGrabber();

        sleep(sleepTime);

        robot.blockMover.moveArm(3650, 1.0, 100, "wiggling", telemetry);

        sleep(sleepTime);

        // Moves away from foundation
        robot.drive.encoderDrive(1250, driveStyle.FORWARD, 0.8);

        sleep(sleepTime);

        // Rotates perpendicular to skybridge
        robot.drive.OrientationDrive(-85, 0.25, robot.drive.imu);

        sleep(sleepTime);

        // Moves away from foundation
        robot.drive.encoderDrive(150, driveStyle.BACKWARD, 0.8);

        sleep(sleepTime);

        robot.blockMover.moveArm(0, 1.0, 100, "Arm going to home position", telemetry);

        sleep(sleepTime);

        // Strafes back to park
        robot.drive.encoderDrive(1500, driveStyle.STRAFE_LEFT,0.8);

        sleep(sleepTime);

        /*
        // Rotates parallel to quarry
        robot.drive.OrientationDrive(0, 0.25,robot.drive.imu);

        sleep(sleepTime);

        // Moves arm down to grab stone
        robot.blockMover.moveArm(4200, 1.0, 100, "Moving to grab skystone", telemetry);

        sleep(sleepTime);

        // Strafes into stone
        robot.drive.encoderDrive(175, driveStyle.STRAFE_RIGHT, 0.4);

        sleep(sleepTime);

        // Picks up stone
        robot.blockMover.closeGrabber();

        sleep(1000);

        // Raises arm
        robot.blockMover.moveArm(3550, 0.4, 100, "Moving skystone up", telemetry);

        sleep(1000);

        // Rotates robot perpendicular to skybridge
        robot.drive.OrientationDrive(85, 0.25, robot.drive.imu);

        // Moves away from quarry
        robot.drive.encoderDrive(200, driveStyle.FORWARD, 0.4);

        // Lowers the arm so it doesn't hit the skybridge
        robot.blockMover.moveArm(3700, 0.65, 50, "Lowering arm slightly", telemetry);


        sleep(sleepTime);

        // Strafes to foundation
        switch(position)
        {
            case RIGHT:
            {
                robot.drive.encoderDrive(4800, driveStyle.STRAFE_LEFT,0.8);
                break;
            }

            case CENTER:
            {
                robot.drive.encoderDrive(5250, driveStyle.STRAFE_LEFT,0.8);
                break;
            }

            case LEFT:
            {
                robot.drive.encoderDrive(4800, driveStyle.STRAFE_LEFT,0.8);
                break;
            }
        }

        sleep(sleepTime);


        // Rotates robot parallel to foundation
        robot.drive.OrientationDrive(0, 0.25, robot.drive.imu);

        sleep(sleepTime);

        // Strafes into/close to foundation
        robot.drive.encoderDrive(200, driveStyle.STRAFE_RIGHT, 0.8);

        sleep(sleepTime);

        // Drops stone
        robot.blockMover.openGrabber();

        sleep(sleepTime);

        // Rotates robot perpendicular to
        robot.drive.OrientationDrive(90, 0.25, robot.drive.imu);

        sleep(sleepTime);

        // Moves robot away from foundation
        robot.drive.encoderDrive(500, driveStyle.FORWARD, 0.8);

        sleep(sleepTime);


        sleep(sleepTime);

        robot.drive.encoderDrive(1600, driveStyle.STRAFE_LEFT, 0.8);

        sleep(sleepTime);


        robot.drive.encoderDrive(2200, driveStyle.FORWARD, 0.6);

        sleep(sleepTime);
        */


    }
}
