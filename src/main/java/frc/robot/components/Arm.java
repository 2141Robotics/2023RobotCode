package frc.robot.components;

import javax.imageio.plugins.tiff.TIFFDirectory;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.math.Vec2d;

public class Arm {
    WPI_TalonFX extender;
    WPI_TalonFX m1;
    WPI_TalonFX m2;

    public Arm (int extenderID, int m1ID, int m2ID){
        this.extender = new WPI_TalonFX(extenderID);
        this.m1 = new WPI_TalonFX(m1ID);
        this.m2 = new WPI_TalonFX(m2ID);
    }

    public void move(XboxController ctrlr) {

    }
    
    public void move(Vec2d vec) {

    }
    
}
