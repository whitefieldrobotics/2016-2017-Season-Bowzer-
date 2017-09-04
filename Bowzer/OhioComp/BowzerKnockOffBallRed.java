package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Knock off ball Red", group = "Bowzer")
//@Disabled
public class BowzerKnockOffBallRed extends LinearOpMode {

    // 2016 Whitefield Robotics

    HardwareBowzer robot = new HardwareBowzer();

    @Override
    public void runOpMode() throws InterruptedException {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        robot.init(hardwareMap);

        // wait for the start button to be pressed.
        waitForStart();

        while (opModeIsActive()) {

            sleep(5000);

            // Turn on Ball Pitching mechanism
            robot.ballpitchermotor.setPower(.40);
            robot.conveyerMotor.setPower(-1);
            robot.sweepermotor.setPower(1);
            sleep(3000);

            // Turn Ball Pitching mechanism off
            robot.ballpitchermotor.setPower(0);
            robot.conveyerMotor.setPower(0);
            robot.sweepermotor.setPower(0);

            sleep(50);

            int Right = robot.motorRight.getCurrentPosition();
            while (Right < 2200){
                Right = robot.motorRight.getCurrentPosition();
                robot.motorLeft.setPower(.6);
                robot.motorRight.setPower(.6);
                telemetry.addData("current pos", Right);
                telemetry.update();
                idle();
            }

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);

            robot.motorLeft.setPower(-1);
            robot.motorRight.setPower(1);

            sleep(800);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);

            robot.motorLeft.setPower(1);
            robot.motorRight.setPower(1);
            sleep(1000);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);

            robot.motorLeft.setPower(-1);
            robot.motorRight.setPower(1);

            sleep(800);

            robot.motorLeft.setPower(-8);
            robot.motorRight.setPower(-8);

            sleep(1200);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            idle();
            break;
        }
    }
}
