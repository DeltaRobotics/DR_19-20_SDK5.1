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

        robot.drive.encoderDrive(900, driveStyle.STRAFE_RIGHT, 0.5);

        sleep(sleepTime);

        switch(position)
        {
            case LEFT:
            {
                break;
            }

            case CENTER:
            {
                robot.drive.encoderDrive(175,driveStyle.BACKWARD,0.4);
                break;
            }

            case RIGHT:
            {
                robot.drive.encoderDrive(550,driveStyle.BACKWARD,0.4);
                break;
            }
        }

        robot.blockMover.blockArm.setTargetPosition(4100);

        while(robot.blockMover.blockArm.getCurrentPosition() < robot.blockMover.blockArm.getTargetPosition() - 100)
        {
            telemetry.addData("Arm Status:", "Moving to grab block");
            telemetry.addData("Current Position", robot.blockMover.blockArm.getCurrentPosition());
            telemetry.update();
        }


        sleep(sleepTime);

        robot.drive.encoderDrive(100, driveStyle.STRAFE_RIGHT, 0.4);

        sleep(sleepTime);

        robot.blockMover.closeGrabber();

        sleep(1000);

        robot.blockMover.blockArm.setTargetPosition(FirstMeetBlockMover.PLACE_POSITION);

        while(robot.blockMover.blockArm.getCurrentPosition() > robot.blockMover.blockArm.getTargetPosition() + 100)
        {
            telemetry.addData("Arm Status:", "Moving to grab block");
            telemetry.addData("Current Position", robot.blockMover.blockArm.getCurrentPosition());
            telemetry.update();
        }

        sleep(5000);

    }
}
