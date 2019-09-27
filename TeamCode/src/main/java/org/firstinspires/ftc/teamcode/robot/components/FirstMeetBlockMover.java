package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FirstMeetBlockMover
{

    Servo grabber_servo;

    public ServoArm grabber;


    public FirstMeetBlockMover(HardwareMap hardwareMap)
    {
        grabber_servo = hardwareMap.servo.get("grabber");
        // FirstMeetBlockMover ServoArm class
        grabber = new ServoArm(grabber_servo, 0.25, 0.0, 0.25);
    }



}
