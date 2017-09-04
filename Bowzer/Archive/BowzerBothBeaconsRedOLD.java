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

@Autonomous(name = "Go to Both Beacons Red", group = "Bowzer")
//@Disabled
public class BowzerBothBeaconsRed extends LinearOpMode {

    ModernRoboticsI2cRangeSensor rangeSensor;


    HardwareBowzer robot = new HardwareBowzer(); // use the class created to define a Pushbot's hardware


    ColorSensor colorSensor;    // Hardware Device Object
    OpticalDistanceSensor odsSensor;  // Hardware Device Object

    @Override
    public void runOpMode() throws InterruptedException {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        colorSensor = hardwareMap.colorSensor.get("color sensor");
        odsSensor = hardwareMap.opticalDistanceSensor.get("ods");
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range sensor");
        // wait for the start button to be pressed.
        waitForStart();

        robot.init(hardwareMap);

        while (opModeIsActive()) {
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            idle();

            int leftencoderPos = robot.motorLeft.getCurrentPosition();
            int rightencoderPos = robot.motorRight.getCurrentPosition();

            int leftencoder = robot.motorLeft.getCurrentPosition();
            int rightencoder = robot.motorRight.getCurrentPosition();

            waitForStart();

            robot.motorRight.setTargetPosition(950);
            robot.motorLeft.setTargetPosition(950);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(.6);
            robot.motorLeft.setPower(.6);

            sleep(1000);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            ////////////////////First Turn///////////////////////////////
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setTargetPosition(745);
            robot.motorLeft.setTargetPosition(-745);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(.6);
            robot.motorLeft.setPower(-.6);
            sleep(1000);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            ////////////////////////////////////////////////////////
            ///////////////////////////Go Forward a lot////////////////////////
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setTargetPosition(4540);
            robot.motorLeft.setTargetPosition(4540);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(.8);
            robot.motorLeft.setPower(.8);
            sleep(3100);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            ////////////////////////////////////////////////////////

            ///////////////////////////TURN////////////////////////
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setTargetPosition(-770);
            robot.motorLeft.setTargetPosition(770);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(-9);
            robot.motorLeft.setPower(9);
            sleep(900);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            ////////////////////////////////////////////////////////

            //goes forward until it sees the line.
            while(odsSensor.getRawLightDetected() < 2 ){
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }

            sleep(50);

            //turns to face beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-1250);
            robot.motorRight.setTargetPosition(1250);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.25);
            robot.motorRight.setPower(.25);

            sleep(1100);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);


            //gets close the beacon using the ultrasonic sensor.
            while (rangeSensor.cmUltrasonic() > 13){
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            int beacon = 0;

            //decides what color the beacon is.
            if (colorSensor.red() > 1){
                robot.motorLeft.setPower(.3);
                sleep(300);
                robot.motorRight.setPower(.20);
                sleep(300);

            }else if (colorSensor.blue() > 1) {
                robot.motorRight.setPower(.3);
                sleep(300);
                robot.motorLeft.setPower(.20);
                sleep(300);
                beacon = 2;
            }
            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(200);

            //the robot backs up from the beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-350);
            robot.motorRight.setTargetPosition(-350);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.45);
            robot.motorRight.setPower(-.45);

            sleep(500);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(.0);

            if (beacon == 2){
                robot.motorLeft.setPower(.45);
                robot.motorRight.setPower(-.45);
                sleep(200);
            }

            //turns to face beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(1100);
            robot.motorRight.setTargetPosition(-1100);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(.25);
            robot.motorRight.setPower(-.25);

            sleep(1500);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            /////////////////////////////////////////////////////////////////////////////////////////

            //goes forward until it sees the line.
            while(odsSensor.getRawLightDetected() < 2 ){
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }

            sleep(50);

            //turns to face beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-1250);
            robot.motorRight.setTargetPosition(1250);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.25);
            robot.motorRight.setPower(.25);

            sleep(1100);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);


            //gets close the beacon using the ultrasonic sensor.
            while (rangeSensor.cmUltrasonic() > 13){
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            beacon = 0;

            //decides what color the beacon is.
            if (colorSensor.red() > 1){
                robot.motorLeft.setPower(.35);
                sleep(300);
                robot.motorRight.setPower(.20);
                sleep(100);
                beacon = 1;
            }else if (colorSensor.blue() > 1) {
                robot.motorRight.setPower(.35);
                sleep(300);
                robot.motorLeft.setPower(.20);
                sleep(100);
                beacon = 2;
            }else {
                beacon = 3;
            }
            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(200);

            //if no color is seen the robot stops.
            if (beacon == 3) {
                telemetry.addData("Cant find beacon", "sorry");
                telemetry.update();
                sleep(3000);
                break;
            }

            //the robot backs up from the beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-350);
            robot.motorRight.setTargetPosition(-350);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.45);
            robot.motorRight.setPower(-.45);

            sleep(600);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(.0);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            //turns to face beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-1250);
            robot.motorRight.setTargetPosition(-250);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.25);
            robot.motorRight.setPower(.25);

            sleep(1100);


            idle();
            break;

        }
    }
}