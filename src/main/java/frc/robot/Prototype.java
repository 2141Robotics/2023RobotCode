package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.components.SwerveDrive;
import frc.robot.math.Vec2d;

public class Prototype {
    private final WPI_TalonFX arm;

    public Prototype(){
        this.arm = new WPI_TalonFX(0);
    }

    public void run(XboxController controller, XboxController controller2, SwerveDrive sd){
        if (controller.getAButton() || controller2.getAButton()) {
            sd.moveDistance(new Vec2d(10000000,0));
        }
    }
}
