package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.WPI_CANCoder;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.math.Vec2d;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.math.Constants;

public class Arm {
    WPI_TalonFX extender;
    WPI_TalonFX m1;
    WPI_TalonFX m2;
    Claw claw = new Claw();
    WPI_CANCoder canCoder;
    Vec2d pos_GROUND = new Vec2d(55, -4);
    Vec2d pos_MID = new Vec2d(55,28.5);
    Vec2d pos_HIGH = new Vec2d(55,38.5);
    Vec2d pos_COLLECT = new Vec2d(55,25.5);
    Vec2d OFFSET = new Vec2d(-12, 15.5);
    Vec2d VERTICAL = new Vec2d(0, 44);
    Vec2d CURRENT_POS = new Vec2d(0,44);

    public void setupFalcon(WPI_TalonFX talon, int PID_ID) {

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

    public Arm (int extenderID, int m1ID, int m2ID, int canCoderID){
        this.extender = new WPI_TalonFX(extenderID);
        this.m1 = new WPI_TalonFX(m1ID);
        this.m2 = new WPI_TalonFX(m2ID);
        this.canCoder = new WPI_CANCoder(canCoderID);
    }

    public void moveAngleMotor(ControlMode mode, double value){
        this.m1.set(mode, value);
        this.m2.set(mode, -value);
    }

    public void reset() {
        this.m1.setInverted(TalonFXInvertType.Clockwise);
        this.m2.setInverted(TalonFXInvertType.CounterClockwise);
        this.setupFalcon(m1, 1);
        this.setupFalcon(m2, 2);
        this.setupFalcon(extender, 3);
        this.m1.configRemoteFeedbackFilter(this.canCoder.getDeviceID(), RemoteSensorSource.CANCoder, 0);
        this.m1.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
        this.canCoder.configFactoryDefault();
        this.canCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
    
    }

    public void move(XboxController ctrlr, XboxController ctrlr2) {
        if (ctrlr.getYButton() || ctrlr2.getYButton()) {
            this.CURRENT_POS = this.pos_HIGH;
        } else if (ctrlr.getXButton() || ctrlr2.getXButton()) {
            this.CURRENT_POS = this.pos_MID;
        } else if (ctrlr.getBButton() || ctrlr2.getBButton()) {
            this.CURRENT_POS = this.pos_COLLECT;
        } else if (ctrlr.getAButton() || ctrlr2.getAButton()) {
            this.CURRENT_POS = this.pos_GROUND;
        }

        if (ctrlr.getLeftBumper() || ctrlr2.getLeftBumper()) {
            this.claw.open();
        } else if (ctrlr.getRightBumper() || ctrlr2.getRightBumper()) {
            this.claw.close();
        }

        //POV

        double angleSpeed = 0.25;
        double extensionSpeed = 0.5;

        if (ctrlr.getPOV() == 0 || ctrlr2.getPOV() == 0) {
            moveAngleMotor(ControlMode.PercentOutput, -angleSpeed);
        }
        else if(ctrlr.getPOV() == 45 || ctrlr2.getPOV() == 45){
            this.extender.set(extensionSpeed);
            moveAngleMotor(ControlMode.PercentOutput, -angleSpeed);
        }
        else if(ctrlr.getPOV() == 90 || ctrlr2.getPOV() == 90){
            this.extender.set(extensionSpeed);
        }
        else if(ctrlr.getPOV() == 135 || ctrlr2.getPOV() == 135){
            this.extender.set(extensionSpeed);
            moveAngleMotor(ControlMode.PercentOutput, angleSpeed);
        }
        else if (ctrlr.getPOV() == 180 || ctrlr2.getPOV() == 180) {
            moveAngleMotor(ControlMode.PercentOutput, angleSpeed);
        }
        else if (ctrlr.getPOV() == 225 || ctrlr2.getPOV() == 225) {
            moveAngleMotor(ControlMode.PercentOutput, angleSpeed);
            this.extender.set(-0.1);
        }        
        else if(ctrlr.getPOV() == 270 || ctrlr2.getPOV() == 270){
            this.extender.set(-extensionSpeed);
        }
        else if(ctrlr.getPOV() == 315 || ctrlr2.getPOV() == 315){
            this.extender.set(-extensionSpeed);
            moveAngleMotor(ControlMode.PercentOutput, -angleSpeed);
        }


        else {
            moveAngleMotor(ControlMode.PercentOutput, 0);
            this.extender.set(0);
        }

        //POV
    }

    public void move(Vec2d vec) {

        this.setRotation(vec.getAngle());
        this.setLength(vec.getLength());

    }

    public void setRotation(Double angle){
        this.moveAngleMotor(ControlMode.MotionMagic, angle * 9 * Constants.RAD_TO_DEG);
    }

    public void setLength(Double dist) {
        if ((dist < 0)||(dist > 37)){
            return;
        }
        this.extender.set(ControlMode.Position, dist * 0.75 * Math.PI * 2048 * 9);
    }
    
}
