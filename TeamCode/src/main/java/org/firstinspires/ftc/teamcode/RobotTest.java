package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "RobotTest", group = "")
public class RobotTest extends LinearOpMode
{
    public void runOpMode()
    {
        DcMotor motor = null;

        motor = hardwareMap.dcMotor.get("motor");

        waitForStart();

        motor.setPower(1.0);
        sleep(5000);
        motor.setPower(0.0);
    }

}
