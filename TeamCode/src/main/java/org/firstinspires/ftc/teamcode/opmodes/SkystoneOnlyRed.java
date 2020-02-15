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

@Autonomous (name = "SkystoneOnlyRed", group = "auto")
public class SkystoneOnlyRed extends LinearOpModeCamera
{
    @Override
    public void runOpMode()
    {
        int sleepTime = 150;

        GenTwoRobot robot = new GenTwoRobot(this);


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

        robot.drive.encoderDrive(1200, driveStyle.FORWARD, 0.75);

        sleep(sleepTime);

        robot.drive.OrientationDrive(80, 0.5, robot.drive.imu);

        sleep(sleepTime);

        switch(position)
        {
            case RIGHT:
            {
                robot.drive.encoderDrive(1150, driveStyle.BACKWARD, 0.75);
                break;
            }

            case CENTER:
            {
                robot.drive.encoderDrive(600, driveStyle.BACKWARD, 0.75);
                break;
            }

            case LEFT:
            {
                robot.drive.encoderDrive(200, driveStyle.BACKWARD, 0.75);
                break;
            }

        }

        sleep(sleepTime);

        robot.drive.encoderDrive(350, driveStyle.STRAFE_RIGHT, 0.4);

        // Grab Skystone
        robot.blockMover.auto_arm_down();

        sleep(1000);

        robot.drive.encoderDrive(1000, driveStyle.STRAFE_LEFT, 0.75);

        sleep(sleepTime);

        switch(position)
        {
            case CENTER:
            {
                double encoderReadingLB = robot.drive.motorRB.getCurrentPosition();
                double target = (encoderReadingLB - 2425);

                robot.drive.motorRF.setPower(robot.drive.teleOpDrive(0, 0.95, 0)[0]);
                robot.drive.motorRB.setPower(robot.drive.teleOpDrive(0, 0.95, 0)[1]);
                robot.drive.motorLB.setPower(robot.drive.teleOpDrive(0, 1.0, 0)[2]);
                robot.drive.motorLF.setPower(robot.drive.teleOpDrive(0, 1.0, 0)[3]);

                while (robot.drive.motorRB.getCurrentPosition() >= target && opModeIsActive())
                {
                    telemetry.addData("Current Position", robot.drive.motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                //Stops all the motors
                robot.drive.motorRF.setPower(robot.drive.teleOpDrive(0, 0, 0)[0]);
                robot.drive.motorRB.setPower(robot.drive.teleOpDrive(0, 0, 0)[1]);
                robot.drive.motorLB.setPower(robot.drive.teleOpDrive(0, 0, 0)[2]);
                robot.drive.motorLF.setPower(robot.drive.teleOpDrive(0, 0, 0)[3]);
                //robot.drive.encoderDrive(4950, driveStyle.BACKWARD, 1.0);
                break;
            }
            case RIGHT:
            {
                double encoderReadingLB = robot.drive.motorRB.getCurrentPosition();
                double target = (encoderReadingLB - 1500);

                robot.drive.motorRF.setPower(robot.drive.teleOpDrive(0, 0.95, 0)[0]);
                robot.drive.motorRB.setPower(robot.drive.teleOpDrive(0, 0.95, 0)[1]);
                robot.drive.motorLB.setPower(robot.drive.teleOpDrive(0, 1.0, 0)[2]);
                robot.drive.motorLF.setPower(robot.drive.teleOpDrive(0, 1.0, 0)[3]);

                while (robot.drive.motorRB.getCurrentPosition() >= target && opModeIsActive())
                {
                    telemetry.addData("Current Position", robot.drive.motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                //Stops all the motors
                robot.drive.motorRF.setPower(robot.drive.teleOpDrive(0, 0, 0)[0]);
                robot.drive.motorRB.setPower(robot.drive.teleOpDrive(0, 0, 0)[1]);
                robot.drive.motorLB.setPower(robot.drive.teleOpDrive(0, 0, 0)[2]);
                robot.drive.motorLF.setPower(robot.drive.teleOpDrive(0, 0, 0)[3]);
                //robot.drive.encoderDrive(4500, driveStyle.BACKWARD, 1.0);
                break;
            }

            case LEFT:
            {
                double encoderReadingLB = robot.drive.motorRB.getCurrentPosition();
                double target = (encoderReadingLB - 2675);

                robot.drive.motorRF.setPower(robot.drive.teleOpDrive(0, 0.95, 0)[0]);
                robot.drive.motorRB.setPower(robot.drive.teleOpDrive(0, 0.95, 0)[1]);
                robot.drive.motorLB.setPower(robot.drive.teleOpDrive(0, 1.0, 0)[2]);
                robot.drive.motorLF.setPower(robot.drive.teleOpDrive(0, 1.0, 0)[3]);

                while (robot.drive.motorRB.getCurrentPosition() >= target && opModeIsActive())
                {
                    telemetry.addData("Current Position", robot.drive.motorRB.getCurrentPosition());
                    telemetry.addData("Target Position", target);
                    telemetry.update();
                }
                //Stops all the motors
                robot.drive.motorRF.setPower(robot.drive.teleOpDrive(0, 0, 0)[0]);
                robot.drive.motorRB.setPower(robot.drive.teleOpDrive(0, 0, 0)[1]);
                robot.drive.motorLB.setPower(robot.drive.teleOpDrive(0, 0, 0)[2]);
                robot.drive.motorLF.setPower(robot.drive.teleOpDrive(0, 0, 0)[3]);
                //robot.drive.encoderDrive(5650, driveStyle.BACKWARD, 1.0);
                break;
            }
        }
    }
}
