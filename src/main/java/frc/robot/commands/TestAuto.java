package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


public class TestAuto extends SequentialCommandGroup{
    public TestAuto(Command... commands){
        addCommands(commands);
    }
    
}
