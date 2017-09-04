package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;


@Autonomous(name="Computer Vision Test", group="Dev")  // @Autonomous(...) is the other common choice
//@Disabled
public class BowzerVuforiaBlueAuto extends LinearOpMode {
    HardwareBowzer robot = new HardwareBowzer();
    ColorSensor colorSensor;    // Hardware Device Object


    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("color sensor");
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        params.vuforiaLicenseKey = "ARXYTA3/////AAAAGUG5gdoOFUSSrZl2BfIbZgwCV+73vgiMY1Gdkqe9zPzLWISzJqaqxH1i2POM/CUX5F5P14W7Jw+yKadO/DCxys5xJPm0PmNI7bhaRFv2pF8HhniR9jjWeOoeMQ6CNHp/KwZnjo+2rxQniZSavk0U6HSieJyXgDvzhL7ClrLXcVoQj5j2UEnkGeBswKd5shky5nfM1//QkZkqvp0GoNQoKR9z4Wp+7Ev0sTwipEoTE3gMI5VySCe4Py8TBRsjohDT26BbkAmQjC+nwB91Pg6DYYQyI7sh15eENtaGD+DwHMs2J8fpW+NdydKi30p7IL0ia29QfstNlS47FGQ6ZOhZJjJpk2y113NIMDmJhqvrn5R7";
        params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;

        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
        Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

        //get the picture files for each beacon
        VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
        beacons.get(0).setName("Wheels");
        beacons.get(1).setName("Tools");
        beacons.get(2).setName("Lego");
        beacons.get(3).setName("Gears");

        VuforiaTrackableDefaultListener wheels = (VuforiaTrackableDefaultListener) beacons.get(0).getListener();

        waitForStart();
        robot.init(hardwareMap);
        beacons.activate();

        //shooting particles code goes here

        //set up variables
        int encoderRbefore = robot.motorRight.getCurrentPosition();
        int encoderLbefore = robot.motorLeft.getCurrentPosition();

        int encoderRight = robot.motorRight.getCurrentPosition();
        int encoderLeft = robot.motorLeft.getCurrentPosition();

        //move away from the wall
        while (300 + encoderLbefore < encoderLeft){
            encoderLeft = robot.motorLeft.getCurrentPosition();
            robot.motorLeft.setPower(.3);
            robot.motorRight.setPower(.3);
            idle();
        }

        sleep(50);

        //turn robot so the camera can see the beacon
        encoderLbefore = robot.motorLeft.getCurrentPosition();
        while (400 + encoderLbefore < encoderLeft){
            encoderLeft = robot.motorLeft.getCurrentPosition();
            robot.motorLeft.setPower(.3);
            robot.motorRight.setPower(-.3);
            idle();
        }

        //get closer to the beacon to begin tracking
        encoderLbefore = robot.motorLeft.getCurrentPosition();
        while (500 + encoderLbefore < encoderLeft){
            encoderLeft = robot.motorLeft.getCurrentPosition();
            robot.motorLeft.setPower(.3);
            robot.motorRight.setPower(.3);
            idle();
        }

        //Analyze Beacon here (maybe won't be used)

        VectorF angle = anglesFromTarget(wheels);
        VectorF trans = navOffWall(wheels.getPose().getTranslation(), Math.toDegrees(angle.get(0)) - 90, new VectorF(500, 0, 0));

        //using the camera decide which way to turn to start lining up with the beacon
        if (trans.get(0) > 0){
            robot.motorLeft.setPower(.3);
            robot.motorRight.setPower(-.3);
        }else{
            robot.motorLeft.setPower(-.3);
            robot.motorRight.setPower(.3);
        }

        do{
            if(wheels.getPose() != null){
                trans = navOffWall(wheels.getPose().getTranslation(), Math.toDegrees(angle.get(0)) - 90, new VectorF(500, 0, 0));
            }
            idle();
        } while (opModeIsActive() && Math.abs(trans.get(0)) > 30);

        robot.motorLeft.setPower(0);
        robot.motorRight.setPower(0);

        //make the robot go forward using the camera
        robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.motorLeft.setTargetPosition((int) (robot.motorLeft.getCurrentPosition() + ((Math.hypot(trans.get(0), trans.get(2)) + 150) / 101.6 * 1120)));
        robot.motorRight.setTargetPosition((int) (robot.motorRight.getCurrentPosition() + ((Math.hypot(trans.get(0), trans.get(2)) + 150) / 101.6 * 1120)));

        robot.motorLeft.setPower(.3);
        robot.motorRight.setPower(.3);

        while (opModeIsActive() && robot.motorLeft.isBusy() && robot.motorRight.isBusy()){
            idle();
        }

        robot.motorLeft.setPower(0);
        robot.motorRight.setPower(0);

        robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //turn to face the beacon using the camera
        while (opModeIsActive() && (wheels.getPose() == null || Math.abs(wheels.getPose().getTranslation().get(0)) > 10)){
            if (wheels.getPose() != null){
                if (wheels.getPose().getTranslation().get(0) > 0){
                    robot.motorLeft.setPower(-.3);
                    robot.motorRight.setPower(.3);
                }else{
                    robot.motorLeft.setPower(.3);
                    robot.motorRight.setPower(.3);
                }
            } else {
                robot.motorLeft.setPower(-.3);
                robot.motorRight.setPower(.3);
            }
        }

        robot.motorLeft.setPower(0);
        robot.motorRight.setPower(0);

    }
    public VectorF navOffWall(VectorF trans, double robotAngle, VectorF offWall){ return new VectorF((float) (trans.get(0) - offWall.get(0) * Math.sin(Math.toRadians(robotAngle)) - offWall.get(2) * Math.cos(Math.toRadians(robotAngle))), trans.get(1), (float) (trans.get(2) + offWall.get(0) * Math.cos(Math.toRadians(robotAngle)) - offWall.get(2) * Math.sin(Math.toRadians(robotAngle))));
    }

    public VectorF anglesFromTarget(VuforiaTrackableDefaultListener image){ float [] data = image.getRawPose().getData(); float [] [] rotation = {{data[0], data[1]}, {data[4], data[5], data[6]}, {data[8], data[9], data[10]}}; double thetaX = Math.atan2(rotation[2][1], rotation[2][2]); double thetaY = Math.atan2(-rotation[2][0], Math.sqrt(rotation[2][1] * rotation[2][1] + rotation[2][2] * rotation[2][2])); double thetaZ = Math.atan2(rotation[1][0], rotation[0][0]); return new VectorF((float)thetaX, (float)thetaY, (float)thetaZ);
    }
}
