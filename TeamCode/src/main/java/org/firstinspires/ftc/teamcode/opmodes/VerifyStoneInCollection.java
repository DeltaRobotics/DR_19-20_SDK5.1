package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
@TeleOp (name = "VerifyStoneInCollection", group = "")
public class VerifyStoneInCollection extends LinearOpMode
{
    OpticalDistanceSensor ods;

    boolean stoneInCollection = false;

    int ods_target = 500;

    @Override
    public void runOpMode()
    {
        ods = hardwareMap.opticalDistanceSensor.get("ods");

        ods.enableLed(true);

        waitForStart();

        while(opModeIsActive())
        {
            if(!stoneInCollection && ods.getRawLightDetected() >= ods_target)
            {
                stoneInCollection = true;
            }
            else if(ods.getRawLightDetected() <= ods_target)
            {
                stoneInCollection = false;
            }

            telemetry.addData("ODS Value", ods.getRawLightDetected());
            telemetry.addData("Stone In Collection", stoneInCollection);
            telemetry.update();
        }
    }
}
