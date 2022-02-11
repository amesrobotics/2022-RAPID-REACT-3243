// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.Constants; 

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


// ++ comment so I can rebase

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {


  // ++ JOYSTICK STUFF ========================================
  // ++ we make a JoyUtil object instead of an XboxController object; JoyUtil inherits XboxController
  public static JoyUtil primaryController = new JoyUtil( Constants.Joysticks.primaryControllerID );
  public static JoyUtil secondaryController = new JoyUtil( Constants.Joysticks.secondaryControllerID );



  // ++ SUBSYSTEMS AND COMMANDS ========================================
  // subsystems
  private final LimelightSubsystem m_LimelightSubsystem = new LimelightSubsystem();
  private final DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
  // commands
  private final DriveCommand m_DriveCommand = new DriveCommand(m_DriveSubsystem, primaryController);
  // ++ =================================================

 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // ++ command stuff
    m_DriveSubsystem.setDefaultCommand(m_DriveCommand);


    // Configure the button bindings
    configureButtonBindings();
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
}
