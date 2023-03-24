package frc.robot.commands;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.SwerveDrive;
import frc.robot.math.Constants;
import frc.robot.math.Vec2d;

public class autoBalance extends CommandBase {
    AHRS gyro;
    SwerveDrive dt;

    //  Flat = 0
    //  Clockewise = 1,2,3,4
    //  Counter = 360, 359

    public boolean movewithpitch(AHRS gyro, SwerveDrive dt){

        if((this.gyro.getPitch() > 184) ){
            this.dt.move(new Vec2d(0,Constants.TICKS_PER_INCH * 1), 0);
            return false;
        }

        else if(this.gyro.getPitch() < 176){
            this.dt.move(new Vec2d(0,Constants.TICKS_PER_INCH * -1), 0);
            return false;
        }


        if((this.gyro.getPitch() > 356) || (this.gyro.getPitch() < 4)){
            this.dt.stop();
            return true;
        }
        return false;
    }

    public autoBalance(AHRS gyro, SwerveDrive dt) {
        this.gyro = gyro;
        this.dt = dt;
   }

    @Override
    public void execute(){
        movewithpitch(this.gyro, this.dt);
    }

    @Override
    public boolean isFinished(){
        if((this.gyro.getPitch() > 356) || (this.gyro.getPitch() < 4)){
            this.dt.stop();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interupted){
        this.dt.stop();
    System.out.println("arm moved");
   }


}