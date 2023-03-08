package frc.robot.components;

import javax.imageio.plugins.tiff.TIFFDirectory;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.math.Vec2d;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import frc.robot.math.Constants;

public class Arm {
    WPI_TalonFX extender;
    WPI_TalonFX m1;
    WPI_TalonFX m2;

    public void setupFalcon(WPI_TalonFX talon) {
        
    int PID_ID = 0;

    // Miscellaneous settings.
    talon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, PID_ID, Constants.MS_DELAY);
    talon.setSensorPhase(true);
    talon.setInverted(false);
    talon.configNominalOutputForward(0d, Constants.MS_DELAY);
    talon.configNominalOutputReverse(0d, Constants.MS_DELAY);
    talon.configPeakOutputForward(1d, Constants.MS_DELAY);
    talon.configPeakOutputReverse(-1d, Constants.MS_DELAY);
    talon.configAllowableClosedloopError(0, PID_ID, Constants.MS_DELAY);

    // PID tune the steering motor.
    talon.selectProfileSlot(PID_ID, 0);
    talon.config_kF(PID_ID, Constants.PID_SETTINGS[0], Constants.MS_DELAY);
    talon.config_kP(PID_ID, Constants.PID_SETTINGS[1], Constants.MS_DELAY);
    talon.config_kI(PID_ID, Constants.PID_SETTINGS[2], Constants.MS_DELAY);
    talon.config_kD(PID_ID, Constants.PID_SETTINGS[3], Constants.MS_DELAY);
    System.out.println("done config");

    talon.configMotionAcceleration(512*9);
    talon.configMotionCruiseVelocity(15*9*2048/360);
    talon.configMotionSCurveStrength(5);
    talon.configNeutralDeadband(0.01);
    }

    public Arm (int extenderID, int m1ID, int m2ID){
        this.extender = new WPI_TalonFX(extenderID);
        this.m1 = new WPI_TalonFX(m1ID);
        this.m2 = new WPI_TalonFX(m2ID);
    

    }

    public void reset() {
        this.m1.setInverted(TalonFXInvertType.Clockwise);
        this.m2.setInverted(TalonFXInvertType.OpposeMaster);
        this.setupFalcon(m1);
        this.setupFalcon(m2);
        this.setupFalcon(extender);
    }

    public void move(XboxController ctrlr) {

    }
    
    public void move(Vec2d vec) {
        this.setRotation(vec.getAngle());
        this.setLength(vec.getLength());
    }

    public void setRotation(Double angle){
        this.m2.follow(m1);
        this.m1.set(ControlMode.MotionMagic, angle * 9 * Constants.RAD_TO_DEG);
    }

    public void setLength(Double dist) {
        if ((dist < 0)||(dist > 37)){
            return;
        }
        this.extender.set(ControlMode.Position, dist * 0.75 * Math.PI * 2048 * 9);
    }
    
}
