// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Pneumatics extends SubsystemBase {
  /** Creates a new Pneumatics. */

  DoubleSolenoid doublePneumatics;
  Compressor compressor;

  
  public Pneumatics() {

    doublePneumatics = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);

    compressor = new Compressor(PneumaticsModuleType.REVPH);

    compressor.enableAnalog(90, 120);

    doublePneumatics.set(Value.kOff);

  }

  public void off() {
    doublePneumatics.set(Value.kOff);
  }

  public void forward() {
    doublePneumatics.set(Value.kForward);
  }

  public void reverse() {
    doublePneumatics.set(Value.kReverse);
  }

  public void start() {
    doublePneumatics.toggle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
