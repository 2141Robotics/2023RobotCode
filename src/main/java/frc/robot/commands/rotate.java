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
    }

    @Override
    public void execute(){
        this.sd.turnToAngle(a);
    }

    @Override
    public boolean isFinished(){
        return Math.abs(sd.gettingangle() - this.a ) <= Constants.ANGLE_PRECISION;
    }
}
