package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="BlueFarSky",group = "Auto")
public class BlueFarSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        robot.drive.encoderDrive(1500, driveStyle.STRAFE_RIGHT,0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000,driveStyle.FORWARD,0.4);

    }

}
