package org.firstinspires.ftc.teamcode.robot;
        import com.qualcomm.robotcore.hardware.HardwareMap;

        import org.firstinspires.ftc.teamcode.robot.components.FirstMeetBlockMover;
        import org.firstinspires.ftc.teamcode.robot.components.FirstMeetMecanum;


public class Robot
{

    public FirstMeetMecanum drive;
    public FirstMeetBlockMover blockMover;

    /*
        Component Class names will be very specific
        The object names will be general and won't change
    */

    // Constructor
    public Robot(HardwareMap hardwareMap)
    {
        // Drive class for first meet with mecanum wheels
        drive = new FirstMeetMecanum(hardwareMap);

        // Block mover class for first meet
        //blockMover = new FirstMeetBlockMover(hardwareMap);
    }



}
