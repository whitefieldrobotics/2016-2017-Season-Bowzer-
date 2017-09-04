package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@TeleOp(name="Bowzer TeleOP", group="Bowzer")
//@Disabled
public class BowzerTeleOP extends OpMode{

    ModernRoboticsI2cRangeSensor rangeSensor;
    HardwareBowzer robot = new HardwareBowzer(); // use the class created to define a Bowzer's hardware

    @Override
    public void init() {
        robot.init(hardwareMap);

        telemetry.addData("Say", "Hello Alex");
        updateTelemetry(telemetry);
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range sensor");
    }
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }
    @Override
    public void loop() {
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        robot.motorLeft.setPower(left);
        robot.motorRight.setPower(right);

        // spins the conveyor belt system to load particles into the ball pitcher.
        if (gamepad1.a) {
            robot.conveyerMotor.setPower(-1);
        } else {
            robot.conveyerMotor.setPower(0);
        }

        //controls the linear slide system.
        if (gamepad1.right_bumper) {
            robot.linearmotor.setPower(1);
        } else {
            if (gamepad1.left_bumper) {
                robot.linearmotor.setPower(-1);
            } else {
                robot.linearmotor.setPower(0);
            }
        }

        // starts and stops the ball pitcher system.
        if (gamepad1.dpad_up) {
            robot.ballpitchermotor.setPower(.40);
        } else {
            if (gamepad1.dpad_down) {
                robot.ballpitchermotor.setPower(0);
            }
        }

        // reverses the sweeper and conveyor system if you have the wrong color particle.
        if (gamepad1.dpad_right) {
            robot.sweepermotor.setPower(-1);
            robot.conveyerMotor.setPower(1);
        } else {
            robot.sweepermotor.setPower(1);
        }

        telemetry.addData("right encoder:", robot.motorRight.getCurrentPosition());
        telemetry.addData("left encoder:", robot.motorLeft.getCurrentPosition());

        telemetry.addData("Range sensor", rangeSensor.cmUltrasonic());


        telemetry.addData("right motor power:", robot.motorRight.getPower());
        telemetry.addData("left motor power:", robot.motorLeft.getPower());
        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
