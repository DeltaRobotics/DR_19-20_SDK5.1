package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="RedCloseSky",group = "Auto")
public class RedCloseSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        Robot robot = new Robot(hardwareMap);

        waitForStart();

        robot.drive.encoderDrive(750, driveStyle.BACKWARD,0.4);

        sleep(sleepTime);

        robot.drive.encoderDrive(1700,driveStyle.STRAFE_RIGHT,0.5);


    }

}
