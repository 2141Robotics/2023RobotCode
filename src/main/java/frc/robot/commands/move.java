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
   }

   @Override
   public void execute(){
    this.sd.moveDistance(vec);
   }

   @Override
   public boolean isFinished(){
    return Math.abs(sd.averageDist() - vec.getLength()) <= Constants.DIST_PRECISION;
   }

   public void end(){
    this.sd.resetDistance();
   }


}
