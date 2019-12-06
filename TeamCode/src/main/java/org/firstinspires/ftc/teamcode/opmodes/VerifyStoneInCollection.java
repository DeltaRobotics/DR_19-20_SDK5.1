package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp (name = "VerifyStoneInCollection", group = "")
public class VerifyStoneInCollection extends LinearOpMode
{
    OpticalDistanceSensor ods;

    @Override
    public void runOpMode()
    {
        ods = hardwareMap.opticalDistanceSensor.get("ods");

        ods.enableLed(true);

        waitForStart();

        while(opModeIsActive())
        {
            telemetry.addData("ODS Value", ods.getRawLightDetected());
            telemetry.update();
        }
    }
}
