package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.components.driveStyle;

@Autonomous(name="FirstMeetautonomous",group = "")
public class FirstMeetautonomous extends LinearOpMode
{

    public void runOpMode()
    {
        int sleepTime = 500;

        Robot robot = new Robot(hardwareMap);

        waitForStart();


        robot.drive.encoderDrive(500, driveStyle.STRAFE_LEFT,0.7);//get into position for camera analysis

        sleep(sleepTime);//wait for next action

        //put camera stuff here -->

        sleep(sleepTime);//wait for next action

        //put Arm movement to grab Skystone here -->

        sleep(sleepTime);//wait for next action

        robot.drive.encoderDriveInches(80,driveStyle.FORWARD, 0.4);//Drive to foundation

        sleep(sleepTime);//wait for next action

        //Arm Movement to place Skystone on foundation here -->

        sleep(sleepTime);//wait for next action

        robot.drive.encoderDriveInches(90,driveStyle.BACKWARD, 0.4);//drive to get second Skystone

        sleep(sleepTime);//wait for next action

        //put arm movement to grab Skystone here -->

        sleep(sleepTime);//wait for next action

        robot.drive.encoderDriveInches(80,driveStyle.FORWARD, 0.4);//go back to foundation

        sleep(sleepTime);//wait for next action

        //Put arm movement to place Skystone on foundation here -->

        sleep(sleepTime);//wait for next action

        robot.drive.encoderDrive(500,driveStyle.STRAFE_RIGHT, 0.7);//Move to wall for preparation to park

        sleep(sleepTime);//wait for next action

        robot.drive.encoderDriveInches(50,driveStyle.BACKWARD, 0.4);//move to park under bridge

        sleep(sleepTime);//wait for next action


    }

}
