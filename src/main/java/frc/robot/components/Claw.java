package frc.robot.components;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Claw {
    DoubleSolenoid s1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    DoubleSolenoid s2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    
    public void open(){
        s1.set(Value.kForward);
        s2.set(Value.kReverse);
    }
    public void close() {
        s1.set(Value.kReverse);
        s2.set(Value.kForward);
    }
}
