// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class DriveTrain {
        // ++ these are the channels for the drivetran motors
        // CAN
        public static final int frontLeftID = 4;
        public static final int frontRightID = 1;
        public static final int backLeftID = 3;
        public static final int backRightID = 2;
        // ++ There are the positions of the mecanum wheels in meters
        // public static final Translation2d frontLeftMeters = new Translation2d(-0.254,0.257175);
        // public static final Translation2d frontRightMeters = new Translation2d(0.254,0.257175);
        // public static final Translation2d backLeftMeters = new Translation2d(-0.254,-0.257175);
        // public static final Translation2d backRightMeters = new Translation2d(0.254,-0.257175);
        public static final Translation2d frontLeftMeters = new Translation2d(0.257175,0.254);
        public static final Translation2d frontRightMeters = new Translation2d(0.257175,-0.254);
        public static final Translation2d backLeftMeters = new Translation2d(-0.257175,0.254);
        public static final Translation2d backRightMeters = new Translation2d(-0.257175,-0.254);
        // ++ Radius of the wheels in meters
        public static final double wheelRadius = 0.0762;
        // ++ PID values for teleop mecanum drive
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
    }

    
    public static final class Joysticks {
        // ++ controller IDs 
        public static final int primaryControllerID =0;
        public static final int secondaryControllerID = 1; 

        // ++ joystick axis IDs 
        public static final int LeftJoystickX = 2;
        public static final int LeftJoystickY = 1;
        public static final int RightJoystickX = 3;
        public static final int RightJoystickY = 4;

        // ++ joystick button IDs
        public static final int A = 2;
        public static final int B = 3;
        public static final int X = 1;
        public static final int Y = 4;
        public static final int Start = 10;
        public static final int Back = 9;
        public static final int LeftBumper = 5;
        public static final int RightBumper = 6;
        public static final int LeftTrigger = 7;
        public static final int RightTrigger = 8;
        public static final int DpadXaxis = 5;
        public static final int DpadYaxis = 6;

        // ++ other joystick constants
        public static final double deadZoneSize = 0.05;

        // ++ joystick curve constants
        public static final int firstPower = 5;
        public static final int secondPower = 3; 
        public static double aCoeff = 0.1;
        public static double bCoeff = (1.0 - aCoeff); 

    }

}
