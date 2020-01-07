package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.deltacamera.CameraBox;
import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.RGBAverage;
import org.firstinspires.ftc.teamcode.deltacamera.SkystoneCameraEval;
import org.firstinspires.ftc.teamcode.deltacamera.SkystonePositions;
import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous (name = "GenTwoAutoBlue", group = "Auto")
public class GenTwoAutoBlue extends LinearOpModeCamera
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

        CameraBox box1 = cameraUtil.drawBox(533, 259, 1128, 986, image, new RGBAverage(255, 0, 0)); // Left skystone

        CameraBox box2 = cameraUtil.drawBox(228, 1, 1128, 986, image, new RGBAverage(0, 0, 255)); // Center skystone

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

        telemetry.update();

        sleep(1000);

        robot.drive.encoderDrive(1200, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(65, 0.5, robot.drive.imu);

        sleep(sleepTime);

        switch(position)
        {
            case LEFT:
            {
                robot.drive.encoderDrive(50, driveStyle.BACKWARD, 0.75);
                break;
            }

            case RIGHT:
            {
                break;
            }

            case CENTER:
            {
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

        robot.drive.OrientationDrive(88, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.drive.encoderDrive(850, driveStyle.STRAFE_LEFT, 0.85);

        sleep(sleepTime);

        switch(position)
        {
            case LEFT:
            {
                robot.drive.encoderDrive(4500, driveStyle.FORWARD, 0.75);
                break;
            }

            case RIGHT:
            {
                break;
            }

            case CENTER:
            {
                break;
            }
        }

        sleep(sleepTime);

        robot.drive.encoderDrive(850, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        robot.blockMover.foundationDown();

        sleep(sleepTime + 500);

        robot.drive.encoderDrive(850, driveStyle.STRAFE_LEFT, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(165, 0.5, robot.drive.imu);

        sleep(sleepTime);

        robot.blockMover.foundationUp();

        sleep(sleepTime);

    }
}
