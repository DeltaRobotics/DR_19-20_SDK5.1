package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.deltacamera.CameraBox;
import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.SkystoneCameraEval;
import org.firstinspires.ftc.teamcode.deltacamera.SkystonePositions;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name = "CameraTest", group = "")
public class CameraTest extends LinearOpModeCamera
{


    @Override
    public void runOpMode()
    {
        CameraUtil cameraUtil = new CameraUtil(this);

        SkystoneCameraEval skystoneCameraEval = new SkystoneCameraEval();

        cameraUtil.setCameraDownsampling(1);

        cameraUtil.startCamera();


        waitForStart();


        Bitmap image = cameraUtil.takePicture();

        CameraBox box1 = cameraUtil.drawBox(100, 30, 200, 30, image);

        CameraBox box2 = cameraUtil.drawBox(500, 300, 500, 10, image);

        cameraUtil.saveImage(image);

        box1 = cameraUtil.getBoxAverage(box1);
        box2 = cameraUtil.getBoxAverage(box2);


        cameraUtil.stopCamera();

        SkystonePositions box1Eval = skystoneCameraEval.skystoneAnalysis(box1.average, 10, SkystonePositions.LEFT);
        SkystonePositions box2Eval = skystoneCameraEval.skystoneAnalysis(box2.average, 10, SkystonePositions.CENTER);

        telemetry.addData("box1 red", box1.average.red);
        telemetry.addData("box1 green", box1.average.green);
        telemetry.addData("box1 blue", box1.average.blue);

        telemetry.addData("box2 red", box2.average.red);
        telemetry.addData("box2 green", box2.average.green);
        telemetry.addData("box2 blue", box2.average.blue);

        telemetry.addData("box1 Eval", box1Eval);
        telemetry.addData("box2 Eval", box2Eval);


        telemetry.update();
        sleep(5000);
        }

    }

