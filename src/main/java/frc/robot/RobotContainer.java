// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveCommand;

import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.commands.climber_commands.ClimbManagerCommand;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// ++ comment so I can rebase

// ++ = max
// :) = mason

//make the climb able to go back if it gets stuck
// also make sure user can't open gripper when lock engaged
//grabber locking: 180 degrees is locking, 150 is unlocked or not I guess zain got that wrong
//todo for grabber lock: in the case of an emergency and as a failsafe, if the robot detects something go wrong (motor controller disconnected, motor encoder reading dangerous values, etc) lock engages automatically and climber ability disables

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  // :) this is a timer object, using in climber
  public static DriverStation m_driverStation;

  // ++ JOYSTICK STUFF ========================================
  // ++ we make a JoyUtil object instead of an XboxController object; JoyUtil inherits XboxController
  public static JoyUtil primaryController = new JoyUtil( Constants.Joysticks.primaryControllerID );
  public static JoyUtil secondaryController = new JoyUtil( Constants.Joysticks.secondaryControllerID );



  // ++ SUBSYSTEMS AND COMMANDS ========================================
  // subsystems
  private final DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
  private final ClimberSubsystem m_ClimberSubsystem = new ClimberSubsystem();
  // commands
  private final DriveCommand m_DriveCommand = new DriveCommand(m_DriveSubsystem, primaryController);
  private final ClimbManagerCommand m_ClimbCommand = new ClimbManagerCommand(m_ClimberSubsystem, secondaryController);
  // ++ =================================================

 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // ++ command stuff
    m_DriveSubsystem.setDefaultCommand(m_DriveCommand);
    m_ClimberSubsystem.setDefaultCommand(m_ClimbCommand);

    // Configure the button bindings
    configureButtonBindings();

    SmartDashboard.putNumber("spin P", 0);
    SmartDashboard.putNumber("spin I", 0);
    SmartDashboard.putNumber("spin FF", 0);
    SmartDashboard.putNumber("spin D", 0);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null; 
  }

  public ClimberSubsystem getClimberSubsystem(){
    return m_ClimberSubsystem;
  }

}
