// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */

  private CANSparkMax jointOne;
  private CANSparkMax jointTwo;
  private AbsoluteEncoder jointOneEncoder;
  private AbsoluteEncoder jointTwoEncoder;


  private double lengthOne = 31;
  private double lengthTwo = 27;
  private double startAngleOne;
  private double startAngleTwo;

  
  public Arm() {
    jointOne = new CANSparkMax(0, MotorType.kBrushless);
    jointTwo = new CANSparkMax(1, MotorType.kBrushless);

    jointOneEncoder = jointOne.getAbsoluteEncoder(Type.kDutyCycle);
    jointTwoEncoder = jointTwo.getAbsoluteEncoder(Type.kDutyCycle);
    startAngleOne = jointOneEncoder.getPosition();
    startAngleTwo = jointTwoEncoder.getPosition();
  }

  public void goTo(double x, double y) {

    double angleTwo = 
      -((Math.acos(Math.pow(x, 2) + Math.pow(y, 2)
      - Math.pow(lengthOne, 2) - Math.pow(lengthTwo, 2)))
      / (2 * lengthOne * lengthTwo));
    
    double angleOne =
      (Math.atan(y / x)
      + Math.atan((lengthTwo * Math.sin(angleTwo))
      / (lengthOne + (lengthTwo * Math.cos(angleTwo)))));

    if (getPosOne() < angleOne) {
      jointOne.set(0.5);
    }
    else {
      jointOne.set(0);
    }

    if (getPosTwo() < angleTwo) {
      jointTwo.set(0.5);
    }
    else {
      jointTwo.set(0);
    }
  }

  public void stop() {
    jointOne.set(0);
    jointTwo.set(0);
  }

  public double getPosOne() {
    return jointOneEncoder.getPosition() * (60/7);
  }

  public double getPosTwo() {
    return jointTwoEncoder.getPosition() * (60/7);
  }
  public double getXComp() {
    return lengthOne * Math.cos(jointOneEncoder.getPosition()) + lengthTwo * Math.cos(jointTwoEncoder.getPosition());
  }

  public double getYComp() {
    return lengthOne * Math.sin(jointOneEncoder.getPosition()) + lengthTwo * Math.sin(jointTwoEncoder.getPosition());
  }
}