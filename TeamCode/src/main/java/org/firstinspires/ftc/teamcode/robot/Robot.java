package org.firstinspires.ftc.teamcode.robot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.components.Drive;
import org.firstinspires.ftc.teamcode.robot.components.ServoArm;


public class Robot
{
    HardwareMap hardwareMap;

    // Drive motors
    public DcMotor motorRF;
    public DcMotor motorRB;
    public DcMotor motorLF;
    public DcMotor motorLB;

    public DcMotor arm;

    //Grabber servo
    public Servo grabber_servo;

    // Drive class
    public Drive drive = new Drive(motorRF, motorRB, motorLF, motorLB);

    // Grabber ServoArm class
    public ServoArm grabber = new ServoArm(grabber_servo, 0.25, 0.0, 0.25);

    public Robot(HardwareMap hardwareMap)
    {
        this.hardwareMap = hardwareMap;
    }

    public void init()
    {
        motorRF = hardwareMap.dcMotor.get("motorRF");
        motorRB = hardwareMap.dcMotor.get("motorRB");
        motorLF = hardwareMap.dcMotor.get("motorLF");
        motorLB = hardwareMap.dcMotor.get("motorLB");

        //Setting motors to brake when stopped
        motorRF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        grabber_servo = hardwareMap.servo.get("grabber");

        grabber.init();

        arm = hardwareMap.dcMotor.get("arm");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
