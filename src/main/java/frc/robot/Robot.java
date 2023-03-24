package frc.robot;

import com.kauailabs.navx.frc.AHRS;

//importsssssssss
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.components.Arm;
import frc.robot.components.SwerveDrive;
import frc.robot.components.SwerveModule;
import frc.robot.math.Constants;
import frc.robot.math.Vec2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autoBalance;
import frc.robot.commands.move;
import frc.robot.Robot;
import frc.robot.math.FieldMeasurments;


/**
 * The main robot class where everything is run.
 * 
 * @author 2141 Spartonics
 */
public class Robot extends TimedRobot
{

	/** The main drivetrain of the robot. */
	private static final SwerveDrive DRIVETRAIN = new SwerveDrive(0.1d, 1d, 0.8d, 
		new AHRS(SPI.Port.kMXP),
		new SwerveModule(2, 1, 11, -3d * Math.PI / 4d, -274.482421875d),
		new SwerveModule(4, 3, 12, -Math.PI / 4d, -228.33984375d),
		new SwerveModule(6, 5, 13, Math.PI / 4d, -272.900390625d),
		new SwerveModule(8, 7, 14, 3d * Math.PI / 4d, -299.091796875d));

	/** Slot 0 controller. */
	private static final XboxController PRIMARY_CONTROLLER = new XboxController(0);
	private static final XboxController SECONDARY_CONTROLLER = new XboxController(1);
	private static final Arm arm = new Arm(0, 9, 10, 0);


	@Override
	public void robotInit()
	{
		arm.reset();
		
	}
	@Override
	public void disabledExit()
	{
		// Reset the drivetrain.
		DRIVETRAIN.resetMotors();
	}

	@Override
	public void teleopPeriodic()
	{
		// prototype.run(PRIMARY_CONTROLLER, SECONDARY_CONTROLLER, DRIVETRAIN);
		arm.move(PRIMARY_CONTROLLER, SECONDARY_CONTROLLER);
		// Move the drivetrian.
		DRIVETRAIN.move(PRIMARY_CONTROLLER, SECONDARY_CONTROLLER);
		
	}

	@Override
	public void autonomousInit() {
		DRIVETRAIN.getGyro().calibrate();
		DRIVETRAIN.resetMotors();
		CommandScheduler.getInstance().enable();
		autonomousMiddle();
	}
	@Override
	public void robotPeriodic(){
		CommandScheduler.getInstance().run();
	}

	//______________________________________________________________________
	//This is the beggining of Autonomous Code -Marcus <3

	public void autonomousR1(){
		CommandScheduler.getInstance().schedule(new SequentialCommandGroup(
			new move(DRIVETRAIN, new Vec2d(FieldMeasurments.Back_To_End_Of_Charger, 0))
		));
	}

	public void autonomousR2(){
		CommandScheduler.getInstance().schedule(new SequentialCommandGroup(
			new move(DRIVETRAIN, new Vec2d(FieldMeasurments.Right_Barrier, 0))
		));
	}
	
	public void autonomousMiddle(){
		CommandScheduler.getInstance().schedule(new SequentialCommandGroup(
			new move(DRIVETRAIN, new Vec2d((FieldMeasurments.Back_To_Middle_Of_Charger - 10), 0)),
			new autoBalance(DRIVETRAIN.getGyro(), DRIVETRAIN)
			
		));
	}

	//______________________________________________________________________

	@Override
	public void autonomousPeriodic() {

	}
	@Override
	public void autonomousExit() {

	}

	public void testAuto(){
		CommandScheduler.getInstance().schedule(new SequentialCommandGroup(
			new move(DRIVETRAIN, new Vec2d((5*Constants.INCHES_PER_FOOT*Constants.TICKS_PER_INCH),0))
			//new WaitCommand(2),
			//new move(DRIVETRAIN, new Vec2d((5*Constants.INCHES_PER_FOOT*Constants.TICKS_PER_INCH),0)),
			//new WaitCommand(2)
			
			));
	}
}
