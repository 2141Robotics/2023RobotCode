package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.SwerveDrive;
import frc.robot.math.Constants;
import frc.robot.math.Vec2d;


public class move extends CommandBase {
   private final SwerveDrive sd;
   private final Vec2d vec;

   public move(SwerveDrive sd, Vec2d vec) {
      this.sd = sd;
      this.vec = vec;
      //this.sd.resetDistance();
      System.out.println("start");
   }

   @Override
   public void initialize() {
      this.sd.resetDistance();
   }

   @Override
   public void execute(){
    this.sd.moveDistance(vec);
    System.out.println("execute");
   }

   @Override
   public boolean isFinished(){
   return Math.abs(sd.averageDist() - vec.getLength()) <= Constants.DIST_PRECISION;
   }

   @Override
   public void end(boolean interupted){
   
    this.sd.resetDistance();
    this.sd.stop();
    System.out.println("ended move");

   }


}
