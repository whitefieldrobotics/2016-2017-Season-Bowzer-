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

@Autonomous(name = "Blue Auto", group = "Bowzer")
//@Disabled
public class BowzerBlueAuto extends LinearOpMode {

    HardwareBowzer robot = new HardwareBowzer(); // use the class created to define a Pushbot's hardware

    ColorSensor colorSensor;    // Hardware Device Object
    ModernRoboticsI2cRangeSensor rangeSensor;
    ModernRoboticsI2cGyro gyro;   // Hardware Device Object
    int heading = 0;              // Gyro integrated heading

    @Override
    public void runOpMode() throws InterruptedException {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        colorSensor = hardwareMap.colorSensor.get("color sensor");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range sensor");
        gyro = (ModernRoboticsI2cGyro) hardwareMap.gyroSensor.get("gyro sensor");

        gyro.calibrate();

        while (gyro.isCalibrating()) {
            telemetry.addData("Gyro Calibrating", "yes");
            telemetry.update();
            Thread.sleep(50);
            idle();
        }

        // wait for the start button to be pressed.
        waitForStart();

        robot.init(hardwareMap);
        double loop = 0;
        heading = gyro.getHeading();
        robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {

            // Turn on Ball Pitching mechanism
            robot.ballpitchermotor.setPower(.85);
            robot.conveyerMotor.setPower(-1);
            robot.sweepermotor.setPower(1);
            sleep(3000);

            // Turn Ball Pitching mechanism off
            robot.ballpitchermotor.setPower(0);
            robot.conveyerMotor.setPower(0);
            robot.sweepermotor.setPower(0);

            // Go forward 700 encoder ticks
            int encoderRight = robot.motorRight.getCurrentPosition();
            while (encoderRight < 700){
                encoderRight = robot.motorRight.getCurrentPosition();
                robot.motorLeft.setPower(.6);
                robot.motorRight.setPower(.6);
                telemetry.addData("in going forward loop:", gyro.getHeading());
                telemetry.update();
                sleep(50);
            }
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            sleep(50);

            if (heading == 359) {
                robot.motorRight.setPower(-.8);
                robot.motorLeft.setPower(.8);
                sleep(100);
            }

            if (heading == 358) {
                robot.motorRight.setPower(-.8);
                robot.motorLeft.setPower(.8);
                sleep(100);
            }

            while(heading < 25 ){
                heading = gyro.getHeading();
                robot.motorRight.setPower(-.8);
                robot.motorLeft.setPower(.8);
                telemetry.addData("in going forward loop:",gyro);
                idle();
            }

            while(heading < 35){
                idle();
                heading = gyro.getHeading();
                robot.motorRight.setPower(-.5);
                robot.motorLeft.setPower(.5);
                telemetry.addData("Turning towards beacon", "yes");
                telemetry.addData("gyro value", gyro.getHeading());
            }
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            // Go forward until near beacon
            int range = rangeSensor.rawUltrasonic();
            while (range > 55){
                range = rangeSensor.rawUltrasonic();
                robot.motorLeft.setPower(.6);
                robot.motorRight.setPower(.6);
                telemetry.addData("Heading for Beacon", rangeSensor.rawUltrasonic());
                telemetry.update();
                idle();
            }
            while (rangeSensor.cmUltrasonic() > 45){
                sleep(10);
                robot.motorLeft.setPower(.4);
                robot.motorRight.setPower(.4);
                telemetry.addData("Heading for Beacon slower", rangeSensor.rawUltrasonic());
                telemetry.update();
            }
            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            // Turn to face beacon
            while(heading < 78){
                idle();
                heading = gyro.getHeading();
                robot.motorRight.setPower(-.7);
                robot.motorLeft.setPower(.7);
            }
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);


            // Go forward to get color sensor close to the beacon
            while (rangeSensor.rawUltrasonic() > 13 ) {
                idle();
                robot.motorRight.setPower(.3);
                robot.motorLeft.setPower(.3);
            }
            sleep(50);
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);


            // Read the beacon and decide which button to press
            colorSensor.enableLed(false);
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
            if (colorSensor.blue() > 5){
                robot.motorLeft.setPower(.8);
                sleep(1000);
            }else{
                if (colorSensor.red() > 4);
                robot.motorRight.setPower(.8);
                sleep(1000);
            }
            sleep(50);
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);

            // Back up from beacon
            while (rangeSensor.rawUltrasonic() > 14 ) {
                idle();
                robot.motorRight.setPower(-.5);
                robot.motorLeft.setPower(-.5);
            }

            // Turn 90 degrees left
            while (heading != 0){
                idle();
                heading = gyro.getHeading();
                robot.motorRight.setPower(.7);
                robot.motorLeft.setPower(-.7);
            }
            sleep(50);
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);

            // Start going towards the beacon
            while (rangeSensor.rawUltrasonic() > 80){
                idle();
                robot.motorRight.setPower(.6);
                robot.motorLeft.setPower(.6);
            }

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            // Turn to face beacon
            while(heading <= 80){
                idle();
                heading = gyro.getHeading();
                robot.motorRight.setPower(-.7);
                robot.motorLeft.setPower(.7);
            }
            sleep(50);
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);

            // Go forward to get color sensor close to the beacon
            while (rangeSensor.rawUltrasonic() > 13) {
                idle();
                robot.motorRight.setPower(.3);
                robot.motorLeft.setPower(.3);
            }
            sleep(50);
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);

            // Read the beacon and decide which button to press
            colorSensor.enableLed(false);
            Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
            if (colorSensor.blue() > 5){
                robot.motorLeft.setPower(.8);
                sleep(1000);
            }else{
                if (colorSensor.red() > 4);
                robot.motorRight.setPower(.8);
                sleep(1000);
            }
            sleep(50);
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);

            idle();
            break;


        }
    }
}
