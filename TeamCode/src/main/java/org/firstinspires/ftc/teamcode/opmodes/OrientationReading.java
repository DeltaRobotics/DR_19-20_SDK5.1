package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.robot.GenTwoRobot;

@TeleOp (name = "OrientationReading", group = "")
public class OrientationReading extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        Orientation angles;

        GenTwoRobot robot = new GenTwoRobot(this);

        waitForStart();

        while(opModeIsActive())
        {
            angles = robot.drive.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            telemetry.addData("Current Orientation", AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
            telemetry.update();
        }
    }
}
