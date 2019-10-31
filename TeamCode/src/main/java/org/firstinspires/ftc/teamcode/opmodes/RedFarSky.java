package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="RedFarSky",group = "Auto")
public class RedFarSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        robot.drive.encoderDrive(1700,driveStyle.STRAFE_RIGHT,0.5);

        sleep(sleepTime);

        robot.drive.encoderDrive(2000, driveStyle.BACKWARD,0.4);

    }

}
