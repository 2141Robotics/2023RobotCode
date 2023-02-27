package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

@Deprecated
public class delay extends CommandBase {
    double time ;
    double timer = 0;

   public delay(double time) {
    this.time = time;
   }

   @Override
   public void execute(){
    timer += 0.05;
   }

   @Override
   public boolean isFinished(){
    return timer >= time;
   }

   @Override
   public void end(boolean interupted){
    System.out.println("timer finished: " + time);
   }


}