package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.GenOneRobot;
import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="BlueCloseSky",group = "Auto")
public class BlueCloseSky extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();

        robot.drive.encoderDrive(750, driveStyle.FORWARD,0.4);

        sleep(sleepTime);

        robot.drive.encoderDrive(1500,driveStyle.STRAFE_RIGHT,0.5);


    }

}
