package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.components.Claw;

@Deprecated
public class clawCommand extends CommandBase {
    public Claw claw;
    public Boolean open;

   public clawCommand(Claw claw, Boolean open) {
    this.claw = claw;
    this.open = open;
   }

   @Override
   public void execute(){
    if (this.open) {
        this.claw.open();
    } else {
        this.claw.close();
    }
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