// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climber_commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.JoyUtil;

public class SpinClimberCommand extends CommandBase {
  private static ClimberSubsystem m_ClimberSubsystem;
  private static JoyUtil joystick;
  public double goalRevolution;
  
  
  /** Creates a new SpinClimberCommand. */
  public SpinClimberCommand(JoyUtil joy, ClimberSubsystem subsystem, double revolutions) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ClimberSubsystem = subsystem;
    addRequirements(m_ClimberSubsystem);
    goalRevolution = revolutions; //*(230.4/360.0)
    joystick = joy;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ClimberSubsystem.isRunningClimbCommand = true;
    m_ClimberSubsystem.actuateClimber(goalRevolution);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(goalRevolution-m_ClimberSubsystem.encoderClimberAngle)<2.5 || (joystick.getXButton() && m_ClimberSubsystem.currentClimberStep>0) || (joystick.getBButton() && m_ClimberSubsystem.currentClimberStep<0)) { // error room for end command
      if ( (joystick.getXButton() && m_ClimberSubsystem.currentClimberStep>0) || (joystick.getBButton() && m_ClimberSubsystem.currentClimberStep<0)){
        m_ClimberSubsystem.isClimberStepStopped = true;
      }
      m_ClimberSubsystem.isRunningClimbCommand = false;
      return true;
    } else {
      return false;
    }
  }
}
