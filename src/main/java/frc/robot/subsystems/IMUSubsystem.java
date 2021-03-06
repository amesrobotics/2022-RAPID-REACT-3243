// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

public class IMUSubsystem extends SubsystemBase {

  // ~~ IMU object - gyro and accelerometer
  private static final AHRS imu = new AHRS();

  // ++ LEAVE THESE AS PUBLIC; there was some weird error with a Null Pointer Exception
  public SimpleWidget wYaw, wRoll, wPitch, wXVelocity, wYVelocity, wZVelocity, wXPos, wYPos, wZPos;
  public NetworkTableEntry tYaw, tRoll, tPitch, tXVelocity, tYVelocity, tZVelocity, tXPos, tYPos, tZPos;

  // ++ Offset that the getYaw() function adds to the yaw
  private static double yawOffset;

  
  /** Creates a new IMUSubsystem. */
  public IMUSubsystem() {
    yawOffset = 0.0;
  }


      
  public static double getYaw() {
    double angle = imu.getAngle();
    double yaw = (((angle + 180) % 360) - 180);
    return yaw;
    // return 0.0;
  }

  public static double getPitch() {
    return imu.getPitch();
  }

  public static double getRoll() {
    return imu.getRoll();
  }

  public static double getXVelocity() {
    return imu.getVelocityX();
  }

  public static double getYVelocity() {
    return imu.getVelocityY();
  }

  public static double getZVelocity() {
    return imu.getVelocityZ();
  }

  public static double getXPosition() {
    return imu.getDisplacementX();
  }

  public static double getYPosition() {
    return imu.getDisplacementY();
  }

  public static double getZPosition() {
    return imu.getDisplacementZ();
  }

  public static Rotation2d getGyroRotation() {
    double angle = getYaw();
    angle *= (Math.PI / 180);
    Rotation2d rotation = new Rotation2d(angle);
    return rotation;
  }

  public static void setYaw(double angle) {
    imu.reset();
    imu.setAngleAdjustment(-angle);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // tYaw.setDouble(imu.getYaw());
    // tPitch.setDouble(imu.getPitch());
    // tRoll.setDouble(imu.getRoll());
    // tXVelocity.setDouble(imu.getVelocityX());
    // tYVelocity.setDouble(imu.getVelocityY());
    
    // tZVelocity.setDouble(imu.getVelocityZ());
    // tXPos.setDouble(imu.getDisplacementX());
    // tYPos.setDouble(imu.getDisplacementY());
    // tZPos.setDouble(imu.getDisplacementZ());
  }
}
