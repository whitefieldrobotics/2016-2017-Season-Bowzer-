package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

@Autonomous(name = "Go to Beacon", group = "Bowzer")
//@Disabled
public class GoToBeacon extends LinearOpMode {

    HardwareBowzer robot = new HardwareBowzer(); // use the class created to define a Pushbot's hardware

    ColorSensor colorSensor;    // Hardware Device Object
    ModernRoboticsI2cRangeSensor rangeSensor;
    OpticalDistanceSensor odsSensor;  // Hardware Device Object

    @Override
    public void runOpMode() throws InterruptedException {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        colorSensor = hardwareMap.colorSensor.get("color sensor");

        odsSensor = hardwareMap.opticalDistanceSensor.get("ods sensor");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range sensor");
        // wait for the start button to be pressed.
        waitForStart();

        robot.init(hardwareMap);

        while (opModeIsActive()) {

            idle();

            int leftencoderPos = robot.motorLeft.getCurrentPosition();
            int rightencoderPos = robot.motorRight.getCurrentPosition();

            int leftencoder = robot.motorLeft.getCurrentPosition();
            int rightencoder = robot.motorRight.getCurrentPosition();

            waitForStart();

            while (300 + rightencoderPos > rightencoder) {
                rightencoder = robot.motorRight.getCurrentPosition();
                robot.motorRight.setPower(.4);
                robot.motorLeft.setPower(.4);
                sleep(50);
            }

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);

            leftencoderPos = robot.motorLeft.getCurrentPosition();
            while (515 + leftencoderPos > leftencoder) {
                leftencoder = robot.motorLeft.getCurrentPosition();
                robot.motorRight.setPower(-.35);
                robot.motorLeft.setPower(.35);
                sleep(50);
            }

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            sleep(50);

            while (odsSensor.getRawLightDetected() < 1.5){
                robot.motorRight.setPower(.3);
                robot.motorLeft.setPower(.3);
                sleep(50);
            }
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            leftencoderPos = robot.motorLeft.getCurrentPosition();
            while (300 - leftencoderPos < leftencoder){
                leftencoder = robot.motorLeft.getCurrentPosition();
                robot.motorRight.setPower(-.35);
                robot.motorLeft.setPower(-.35);
            }

            idle();
            break;
        }
    }
}
