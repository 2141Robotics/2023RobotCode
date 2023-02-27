package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.SwerveDrive;
import frc.robot.math.Constants;


public class rotate extends CommandBase{
    private final SwerveDrive sd;
    private final double a;

    public rotate(SwerveDrive sd, double a){
        this.sd = sd;
        this.a = a;
        sd.resetGyro();
    }

    @Override
    public void execute(){
        this.sd.turnToAngle(a);
    }

    @Override
    public boolean isFinished(){
        return Math.abs(sd.gettingangle() - a ) <= Constants.ANGLE_PRECISION;
    }

    @Override
    public void end(boolean interupted){
        System.out.println("done");
        sd.resetGyro();
        sd.resetMotors();
        sd.resetDistance();
    }
}
