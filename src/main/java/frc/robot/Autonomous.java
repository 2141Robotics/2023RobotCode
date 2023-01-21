package frc.robot;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.components.SwerveDrive;
import frc.robot.components.SwerveModule;
import frc.robot.math.Constants;

import frc.robot.math.Vec2d;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import edu.wpi.first.math.controller.PIDController;

public class Autonomous {
	private SwerveDrive sd;
	public Autonomous(SwerveDrive drivetrain) {
		this.sd = drivetrain;
	}

	void testAuto(){
		// rotate 90 degrees
		this.sd.turnToAngle(Constants.PI_OVER_TWO);
		// move forward 10 feet
		this.sd.moveDistance(new Vec2d(Constants.PI_OVER_TWO, 10 * Constants.INCHES_PER_FOOT * Constants.TICKS_PER_INCH));
	}

}
