package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.deltacamera.CameraBox;
import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.RGBAverage;
import org.firstinspires.ftc.teamcode.deltacamera.SkystoneCameraEval;
import org.firstinspires.ftc.teamcode.deltacamera.SkystonePositions;
import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.GenTwoBlockMover;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name="GenTwoAutoRed",group = "Auto")
public class GenTwoAutoRed extends LinearOpModeCamera
{

    public void runOpMode()
    {
        int sleepTime = 150;

        GenTwoRobot robot = new GenTwoRobot(hardwareMap, telemetry);


        CameraUtil cameraUtil = new CameraUtil(this);

        SkystoneCameraEval skystoneCameraEval = new SkystoneCameraEval();

        SkystonePositions position = SkystonePositions.NOT_FOUND;

        cameraUtil.setCameraDownsampling(1);

        cameraUtil.startCamera();

        waitForStart();

        Bitmap image = cameraUtil.takePicture();

        CameraBox box1 = cameraUtil.drawBox(345, 64, 1128, 984, image, new RGBAverage(255, 0, 0)); // Left skystone

        CameraBox box2 = cameraUtil.drawBox(613, 356, 1128, 984, image, new RGBAverage(0, 0, 255)); // Center skystone

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


        robot.drive.encoderDrive(1200, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(80, 0.5, robot.drive.imu);

        sleep(sleepTime);

        switch(position)
        {
            case CENTER:
            {
                robot.drive.encoderDrive(900, driveStyle.BACKWARD, 0.75);
            }

            case LEFT:
            {
                robot.drive.encoderDrive(500, driveStyle.BACKWARD, 0.75);
            }

            case RIGHT:
            {

            }
        }


        sleep(sleepTime);

        robot.drive.OrientationDrive(79, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.blockMover.moveArm(300, 1.0, 5, "Moving arm to collect position", telemetry);

        sleep(sleepTime);

        robot.blockMover.intake_In();

        sleep(500);

        /*robot.drive.encoderDrive(600, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);*/

        robot.drive.encoderDrive(1100, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.encoderDrive(300, driveStyle.FORWARD, 0.2);

        sleep(1000);

        robot.blockMover.intake_Stop();

        sleep(sleepTime);

        robot.blockMover.openGrabber();

        sleep(sleepTime);

        robot.blockMover.moveArm(0, 0.5, 5, "Moving arm down", telemetry);

        sleep(sleepTime);

        robot.blockMover.closeGrabber();

        sleep(sleepTime);

        robot.drive.encoderDrive(850, driveStyle.STRAFE_LEFT, 0.85);

        sleep(sleepTime);

        robot.drive.OrientationDrive(80, 0.75, robot.drive.imu);

        sleep(sleepTime);

        switch(position)
        {
            case CENTER:
            {
                robot.drive.encoderDrive(4500, driveStyle.BACKWARD, 0.75);
            }

            case LEFT:
            {
                robot.drive.encoderDrive(5000, driveStyle.BACKWARD, 0.75);
            }
        }

        sleep(sleepTime);

        robot.drive.encoderDrive(750, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);

        robot.drive.encoderDrive(2100, driveStyle.STRAFE_LEFT, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(0, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.blockMover.foundationUp();

        sleep(sleepTime);

        robot.drive.OrientationDrive(80, 0.75, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(800, driveStyle.STRAFE_RIGHT, 0.85);

        sleep(sleepTime);

        robot.drive.timeDrive(1000, 0.3, driveStyle.BACKWARD);

        sleep(sleepTime);

        robot.blockMover.moveArm(GenTwoBlockMover.DELIVER_POSITION, 0.5, 5, "Moving to place Skystone", telemetry);

        sleep(sleepTime);

        robot.blockMover.openGrabber();

        sleep(1000);

        robot.blockMover.moveArm(GenTwoBlockMover.TRAVEL_POSITION, 0.5, 5 , "Moving arm to travel position", telemetry);

        sleep(sleepTime);

        robot.drive.timeDrive(2000, 0.5, driveStyle.FORWARD);


    }

}
