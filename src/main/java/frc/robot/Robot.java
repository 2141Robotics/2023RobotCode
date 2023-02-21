package frc.robot;

import com.kauailabs.navx.frc.AHRS;

//importsssssssss
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.components.SwerveDrive;
import frc.robot.components.SwerveModule;
import frc.robot.math.Constants;
import frc.robot.math.Vec2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.commands.move;
import frc.robot.commands.rotate;

/**
 * The main robot class where everything is run.
 * 
 * @author 2141 Spartonics
 */
public class Robot extends TimedRobot
{


	/** The main drivetrain of the robot. */
	private static final SwerveDrive DRIVETRAIN = new SwerveDrive(0.1d, 1d, 0.5d, 
		new AHRS(SPI.Port.kMXP),
		new SwerveModule(2, 1, 11, -3d * Math.PI / 4d, -274.482421875d),
		new SwerveModule(4, 3, 12, -Math.PI / 4d, -228.33984375d),
		new SwerveModule(6, 5, 13, Math.PI / 4d, -272.900390625d),
		new SwerveModule(8, 7, 14, 3d * Math.PI / 4d, -299.091796875d));

	/** Slot 0 controller. */
	private static final XboxController PRIMARY_CONTROLLER = new XboxController(0);

	private static final Prototype prototype = new Prototype();


	@Override
	public void robotInit()
	{
		// Reset the drivetrain and cailbrate the gyroscope.
		DRIVETRAIN.getGyro().calibrate();
		DRIVETRAIN.resetMotors();
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
		prototype.run(PRIMARY_CONTROLLER);

		// Move the drivetrian.
		DRIVETRAIN.move(PRIMARY_CONTROLLER);
	}

	@Override
	public void autonomousInit() {
		CommandScheduler.getInstance().enable();
		testAuto();
	}

	@Override
	public void autonomousPeriodic() {
		CommandScheduler.getInstance().run();
		
	}
	@Override
	public void autonomousExit() {
		CommandScheduler.getInstance().disable();
	}

	public void testAuto(){
		CommandScheduler.getInstance().schedule(new move(DRIVETRAIN, new Vec2d((1*Constants.INCHES_PER_FOOT*Constants.TICKS_PER_INCH),0)).andThen(new rotate(DRIVETRAIN, Math.PI)));
	}
}
