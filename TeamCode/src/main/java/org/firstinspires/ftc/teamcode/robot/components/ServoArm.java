package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoArm
{
    // Local variables
    private Servo servo;
    private double initPos;
    private double minPos;
    private double maxPos;

    // Constructor
    public ServoArm(Servo servo, double initPos, double minPos, double maxPos)
    {
        this.servo = servo;
        this.initPos = initPos;
        this.minPos = minPos;
        this.maxPos = maxPos;
    }

    // Raises arm
    public void raiseArm()
    {
        servo.setPosition(maxPos);
    }

    // Lowers arm
    public void lowerArm()
    {
        servo.setPosition(minPos);
    }

    public void init()
    {
        servo.setPosition(initPos);
    }


    // Getters
    public double getInitPos()
    {
        return initPos;
    }

    public double getMaxPos()
    {
        return maxPos;
    }

    public double getMinPos()
    {
        return minPos;
    }

    public Servo getServo()
    {
        return servo;
    }
}
