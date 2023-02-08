// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */

  private CANSparkMax topMotor;
  private CANSparkMax bottomMotor;
  private CANSparkMax pivot;
  private DoubleSolenoid bar;

  public Intake() {
    topMotor = new CANSparkMax(Constants.IntakeConstants.kTopMotor, MotorType.kBrushless);
    bottomMotor = new CANSparkMax(Constants.IntakeConstants.kBottomMotor, MotorType.kBrushless);
    pivot = new CANSparkMax(Constants.IntakeConstants.kPivotMotor, MotorType.kBrushless);

    bar = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.IntakeConstants.kSolenoidForward, Constants.IntakeConstants.kSolenoidReverse);

    pivot.setSoftLimit(SoftLimitDirection.kForward, 5 /*max end*/);
    pivot.setSoftLimit(SoftLimitDirection.kReverse, 0 /*max start*/);

    topMotor.set(0);
    bottomMotor.set(0);
    pivot.set(0);
    pivotDown();

  }

  public void input() {
    // Run input until max (cone) current reached
    // Runs continuously for blocks
    if (topMotor.getOutputCurrent() < 5 /*temp val*/) {
        topMotor.set(-0.5);
        bottomMotor.set(0.5);
    }
    else {
        // Move pivot up and push cone through into claw
        stop();
        pivotUp();
        pivotDown();
    }
  }

  public void output() {
    topMotor.set(0.5);
    bottomMotor.set(-0.5);
  }

  public void stop() {
    topMotor.set(0);
    bottomMotor.set(0);
  }

  public void pivotUp() {
    pivot.set(0.5);
  }

  public void pivotDown() {
    pivot.set(-0.5);
  }

  public void pivotStop() {
    pivot.set(0);
  }

  
}
