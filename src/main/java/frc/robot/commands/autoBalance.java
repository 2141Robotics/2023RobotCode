package frc.robot.commands;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.Arm;
import frc.robot.components.SwerveDrive;
import frc.robot.math.Vec2d;

@Deprecated
public class autoBalance extends CommandBase {
    AHRS gyro;
    SwerveDrive dt;

   public autoBalance(AHRS gyro, SwerveDrive dt) {
   this.gyro = gyro;
   this.dt = dt;
   }

   @Override
   public void execute(){
    this.dt.move(new Vec2d(0,-0.1), 0);
   }

   @Override
   public boolean isFinished(){
    return (Math.abs(this.gyro.getPitch()) < 4);
   }

   @Override
   public void end(boolean interupted){
    this.dt.stop();
    System.out.println("arm moved");
   }


}