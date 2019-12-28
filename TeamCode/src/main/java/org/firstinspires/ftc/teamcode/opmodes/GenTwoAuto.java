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
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name="GenTwoAuto",group = "Auto")
public class GenTwoAuto extends LinearOpModeCamera
{

    public void runOpMode()
    {
        int sleepTime = 250;

        GenOneRobot robot = new GenOneRobot(hardwareMap, telemetry);


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



    }

}
