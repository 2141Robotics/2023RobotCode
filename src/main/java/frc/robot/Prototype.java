package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;

public class Prototype {
    private final WPI_TalonFX arm;

    public Prototype(){
        this.arm = new WPI_TalonFX(0);
    }

    public void run(XboxController controller){
        arm.set(ControlMode.PercentOutput, controller.getRightY());
    }
}
