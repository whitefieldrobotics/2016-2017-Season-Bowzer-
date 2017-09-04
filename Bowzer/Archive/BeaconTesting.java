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

@Autonomous(name = "Beacon Testing", group = "Bowzer")
//@Disabled
public class BeaconTesting extends LinearOpMode {

    HardwareBowzer robot = new HardwareBowzer(); // use the class created to define a Pushbot's hardware

    ColorSensor colorSensor;    // Hardware Device Object
    ModernRoboticsI2cRangeSensor rangeSensor;
    ModernRoboticsI2cGyro gyro;   // Hardware Device Object
    int heading = 0;              // Gyro integrated heading

    @Override
    public void runOpMode() throws InterruptedException {

        // hsvValues is an array thbat will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
        // bLedOn represents the state of the LED.

        // get a reference to our ColorSensor object.
        colorSensor = hardwareMap.colorSensor.get("color sensor");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range sensor");

        // get a reference to a Modern Robotics GyroSensor object.
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro sensor");

        gyro.calibrate();

        while (gyro.isCalibrating()) {
            Thread.sleep(50);
            idle();
        }

        // wait for the start button to be pressed.
        waitForStart();

        colorSensor.enableLed(false);

        robot.init(hardwareMap);

        double loop = 0;

        heading = gyro.getHeading();

        int encoderLeft = robot.motorLeft.getCurrentPosition();

        while (opModeIsActive()) {

            sleep(50);

            while (rangeSensor.rawUltrasonic() > 24) {
                idle();
                robot.motorRight.setPower(.5);
                robot.motorLeft.setPower(.5);
                telemetry.addData("curent pos", rangeSensor.rawUltrasonic());
                telemetry.update();
            }
            while (rangeSensor.rawUltrasonic() > 14) {
                idle();
                robot.motorRight.setPower(.2);
                robot.motorLeft.setPower(.2);
                telemetry.addData("curent pos", rangeSensor.rawUltrasonic());
                telemetry.update();

            }
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            sleep(50);

            colorSensor.enableLed(false);

            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);

            if (colorSensor.blue() > 6){
                robot.motorLeft.setPower(.8);
                sleep(1000);
            }else{
                if (colorSensor.red() > 5);
                robot.motorRight.setPower(.8);
                sleep(1000);
            }

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            idle();
            break;

        }
    }
}
