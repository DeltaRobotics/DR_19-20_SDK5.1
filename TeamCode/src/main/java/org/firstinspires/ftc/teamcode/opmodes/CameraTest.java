package org.firstinspires.ftc.teamcode.opmodes;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.deltacamera.CameraUtil;
import org.firstinspires.ftc.teamcode.deltacamera.RGBAverage;

import for_camera_opmodes.LinearOpModeCamera;

@Autonomous(name = "CameraTest", group = "")
public class CameraTest extends LinearOpModeCamera
{


    @Override
    public void runOpMode()
    {
        CameraUtil cameraUtil = new CameraUtil(this);

        RGBAverage average = null;

        if(cameraUtil.camera.isCameraAvailable())
        {
            cameraUtil.setCameraDownsampling(1);

            cameraUtil.startCamera();


            waitForStart();

            if(cameraUtil.camera.imageReady()) {

                Bitmap image = cameraUtil.takePicture();

                cameraUtil.drawBox(100, 30, 200, 30, image);

                cameraUtil.saveImage(image);

                average = cameraUtil.boxAnalysis(100, 30, 200, 30, image);
            }

            cameraUtil.stopCamera();

            telemetry.addData("red", average.red);
            telemetry.addData("green", average.green);
            telemetry.addData("blue", average.blue);
            telemetry.update();
            sleep(5000);
        }

    }
}
