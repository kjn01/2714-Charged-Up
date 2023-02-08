// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Claw extends SubsystemBase {
  /** Creates a new Pneumatics. */

  DoubleSolenoid leftSolenoid;
  DoubleSolenoid rightSolenoid;
  Compressor leftCompressor;
  Compressor rightCompressor;

  CANSparkMax wheels;

  
  public Claw() {

    leftSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.ClawConstants.kLeftSolenoidForward, Constants.ClawConstants.kLeftSolenoidReverse);
    rightSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.ClawConstants.kRightSolenoidForward, Constants.ClawConstants.kRightSolenoidReverse);

    leftCompressor = new Compressor(PneumaticsModuleType.REVPH);
    rightCompressor = new Compressor(PneumaticsModuleType.REVPH);

    wheels = new CANSparkMax(Constants.ClawConstants.kWheelMotor, MotorType.kBrushless);

    leftCompressor.enableAnalog(90, 120);
    rightCompressor.enableAnalog(90, 120);

    leftSolenoid.set(Value.kOff);
    rightSolenoid.set(Value.kOff);

  }

  public void off() {
    leftSolenoid.set(Value.kOff);
    rightSolenoid.set(Value.kOff);
  }

  public void forward() {
    leftSolenoid.set(Value.kForward);
    rightSolenoid.set(Value.kForward);
  }

  public void reverse() {
    leftSolenoid.set(Value.kReverse);
    rightSolenoid.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
