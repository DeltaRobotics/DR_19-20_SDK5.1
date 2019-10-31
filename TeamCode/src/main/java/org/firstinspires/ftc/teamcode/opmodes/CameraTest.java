package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.deltacamera.CameraBox;
import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.SkystoneCameraEval;
import org.firstinspires.ftc.teamcode.deltacamera.SkystonePositions;

import for_camera_opmodes.LinearOpModeCamera;

@TeleOp(name = "CameraTest", group = "")
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


        CameraBox box1 = cameraUtil.drawBox(595, 261, 897, 729, image);

        CameraBox box2 = cameraUtil.drawBox(863, 595, 897, 729, image);

        cameraUtil.saveImage(image);

        box1 = cameraUtil.getBoxAverage(box1);
        box2 = cameraUtil.getBoxAverage(box2);


        cameraUtil.stopCamera();

        if(skystoneCameraEval.skystoneAnalysis(box1.average))
        {
            telemetry.addData("Position", SkystonePositions.LEFT);
        }
        else if(skystoneCameraEval.skystoneAnalysis(box2.average))
        {
            telemetry.addData("Position", SkystonePositions.CENTER);
        }
        else
        {
            telemetry.addData("Position", SkystonePositions.RIGHT);
        }

        telemetry.addData("box1 red", box1.average.red);
        telemetry.addData("box1 green", box1.average.green);
        telemetry.addData("box1 blue", box1.average.blue);

        telemetry.addData("box2 red", box2.average.red);
        telemetry.addData("box2 green", box2.average.green);
        telemetry.addData("box2 blue", box2.average.blue);


        telemetry.update();
        sleep(15000);
        }

    }

