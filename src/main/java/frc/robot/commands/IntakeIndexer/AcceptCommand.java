// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeIndexer;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeIndexerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class AcceptCommand extends CommandBase {

  private final IntakeIndexerSubsystem m_IntakeIndexerSubsystem;
  private Timer timer;

  boolean doLimelightCheck;

  double duration;

  /** Creates a new AcceptCommand. */
  public AcceptCommand(IntakeIndexerSubsystem subsystem, boolean useLimelightCheck, double commandDuration) {
    m_IntakeIndexerSubsystem = subsystem;
    doLimelightCheck = useLimelightCheck;
    duration = commandDuration;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_IntakeIndexerSubsystem);
    

    timer = new Timer(); 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();

    // if ( (doLimelightCheck && LimelightSubsystem.continueShooterRoutine) || ( !doLimelightCheck ) ) {
    //   m_IntakeIndexerSubsystem.setIntakeSpeed(Constants.IntakeIndexer.acceptSpeed);
    //   m_IntakeIndexerSubsystem.stepIndexer(Constants.IntakeIndexer.acceptRotations);
    // }

    if ( (doLimelightCheck && LimelightSubsystem.continueShooterRoutine ) || !doLimelightCheck) {
      m_IntakeIndexerSubsystem.setIntakeSpeed(Constants.IntakeIndexer.acceptSpeed);
      // m_IntakeIndexerSubsystem.stepIndexer(Constants.IntakeIndexer.acceptRotations);
      m_IntakeIndexerSubsystem.setIndexerSpeed(-500);
    }



  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {



  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // ++ in this method, we want to stop both the intake and indexer
    m_IntakeIndexerSubsystem.setIntakeSpeed(0.0);
    // m_IntakeIndexerSubsystem.stepIndexer(0.0);
    m_IntakeIndexerSubsystem.setIndexerSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    /* ++ immediately ends if the limelight subsytem should be used AND the robot isn't aligned (continueShooterRoutine is false). 
    * that ignores the limelight if the subsystem doesn't exist
    *
    * OR it ends when the indexer reaches its time duration
    *
    * 
    */
    return (
      ( doLimelightCheck && !LimelightSubsystem.continueShooterRoutine ) 
      || timer.get() >= duration
      );
  }
}