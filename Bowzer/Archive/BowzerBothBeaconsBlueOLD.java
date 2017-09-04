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

@Autonomous(name = "Go to Both Beacons Blue", group = "Bowzer")
//@Disabled
public class BowzerBothBeaconsBlue extends LinearOpMode {

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

            // Turn on Ball Pitching mechanism
            robot.ballpitchermotor.setPower(.38);
            robot.conveyerMotor.setPower(-1);
            robot.sweepermotor.setPower(-1);
            sleep(3000);

            // Turn Ball Pitching mechanism off
            robot.ballpitchermotor.setPower(0);
            robot.conveyerMotor.setPower(0);
            robot.sweepermotor.setPower(0);

            idle();

            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(50);

            robot.motorRight.setTargetPosition(950);
            robot.motorLeft.setTargetPosition(950);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(.5);
            robot.motorLeft.setPower(.5);

            sleep(1000);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            sleep(50);
            ////////////////////First Turn///////////////////////////////
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setTargetPosition(-745);
            robot.motorLeft.setTargetPosition(745);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(-.5);
            robot.motorLeft.setPower(.5);
            sleep(1600);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
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

            robot.motorRight.setTargetPosition(750);
            robot.motorLeft.setTargetPosition(-750);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(7);
            robot.motorLeft.setPower(-7);
            sleep(2000);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            ////////////////////////////////////////////////////////

            //goes forward until it sees the line.
            while (odsSensor.getRawLightDetected() < 2) {
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }


            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            sleep(50);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //turns to face beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(1020);
            robot.motorRight.setTargetPosition(-1020);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(.25);
            robot.motorRight.setPower(-.25);

            sleep(1500);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);


            //gets close the beacon using the ultrasonic sensor.
            while (rangeSensor.cmUltrasonic() > 11) {
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            int beacon = 0;

            //decides what color the beacon is.
            if (colorSensor.blue() > 1) {
                robot.motorLeft.setPower(.3);
                sleep(300);
                robot.motorRight.setPower(.20);
                sleep(300);
                beacon = 1;

            } else if (colorSensor.red() > 1) {
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

            robot.motorLeft.setTargetPosition(-500);
            robot.motorRight.setTargetPosition(-500);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.45);
            robot.motorRight.setPower(-.45);

            sleep(800);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(.0);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            sleep(50);


            if (beacon == 1) {
                robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //turns to face second beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-920);
            robot.motorRight.setTargetPosition(920);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.25);
            robot.motorRight.setPower(.25);

            sleep(2500);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

            if (beacon == 2) {
                robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //turns to face second beacon
                robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                robot.motorLeft.setTargetPosition(-1100);
                robot.motorRight.setTargetPosition(1100);

                robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                robot.motorLeft.setPower(-.25);
                robot.motorRight.setPower(.25);

                sleep(2300);

                robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            }

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

            robot.motorLeft.setTargetPosition(1100);
            robot.motorRight.setTargetPosition(-1100);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(.25);
            robot.motorRight.setPower(-.25);

            sleep(2100);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(50);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            sleep(50);

            //gets close the beacon using the ultrasonic sensor.
            while (rangeSensor.cmUltrasonic() > 11){
                robot.motorLeft.setPower(.25);
                robot.motorRight.setPower(.25);
                idle();
            }

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            beacon = 0;

            //decides what color the beacon is.
            if (colorSensor.blue() > 1){
                robot.motorLeft.setPower(.35);
                sleep(300);
                robot.motorRight.setPower(.20);
                sleep(300);
                beacon = 1;
            }else if (colorSensor.red() > 1) {
                robot.motorRight.setPower(.35);
                sleep(300);
                robot.motorLeft.setPower(.20);
                sleep(300);
                beacon = 2;
            }else {
                beacon = 3;
            }
            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            sleep(200);

            //if no color is seen the robot stops.
            if (beacon == 3){
                telemetry.addData("Cant find beacon", "sorry");
                telemetry.update();
                sleep(3000);
                break;
            }

            //after button is presses the robot rams into the beacon to get perpendicular with the wall.
            if (beacon == 1){
                robot.motorRight.setPower(.35);
                sleep(400);
                robot.motorLeft.setPower(.25);
                sleep(300);
                robot.motorLeft.setPower(0);
                robot.motorRight.setPower(0);
            }
            if (beacon == 2){
                robot.motorLeft.setPower(.35);
                sleep(400);
                robot.motorRight.setPower(.25);
                sleep(300);
                robot.motorLeft.setPower(0);
                robot.motorRight.setPower(0);
            }

            //the robot backs up from the beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(-450);
            robot.motorRight.setTargetPosition(-450);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(-.45);
            robot.motorRight.setPower(-.45);

            sleep(1600);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);

            //turns to face beacon
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorLeft.setTargetPosition(1250);
            robot.motorRight.setTargetPosition(-1250);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorLeft.setPower(.25);
            robot.motorRight.setPower(-.25);

            sleep(1300);

            robot.motorLeft.setPower(0);
            robot.motorRight.setPower(0);


            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            /////////////////////////////////////////////////////////////////////

            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setTargetPosition(-300);
            robot.motorLeft.setTargetPosition(300);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(-.5);
            robot.motorLeft.setPower(.5);
            sleep(1600);
//////////////////////////////////////////////////////////////////////
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);
            ///////////////////////////Go Forward a lot////////////////////////
            robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setTargetPosition(5040);
            robot.motorLeft.setTargetPosition(5040);

            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorRight.setPower(.8);
            robot.motorLeft.setPower(.8);
            sleep(3100);

            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            break;

        }
    }
}