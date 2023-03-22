package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.Arm;
import frc.robot.math.Vec2d;

@Deprecated
public class moveArm extends CommandBase {
    Arm arm ;
    Vec2d position;

   public moveArm(Arm arm, Vec2d position) {
   this.arm = arm;
   this.position = position;
   }

   @Override
   public void execute(){
    this.arm.move(this.position);
   }

   @Override
   public boolean isFinished(){
    this.execute();
    return true;
   }

   @Override
   public void end(boolean interupted){
    System.out.println("arm moved");
   }


}