package frc.robot;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.components.SwerveDrive;
import frc.robot.components.SwerveModule;
import frc.robot.math.Constants;

import frc.robot.math.Vec2d;

import java.io.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import edu.wpi.first.math.controller.PIDController;

public class Autonomous {
	private int counter = 0;
	private SwerveDrive sd;
	public Autonomous(SwerveDrive drivetrain) {
		this.sd = drivetrain;
	}

	void testAuto(){
		System.out.println(counter);
		if (counter == 0){
				// rotate 90 degrees
			// this.sd.turnToAngle(Constants.PI_OVER_TWO);
			// move forward 10 feet
			System.out.println("moving");
			this.sd.moveDistance(new Vec2d(Constants.PI_OVER_TWO, (3) * Constants.INCHES_PER_FOOT * Constants.TICKS_PER_INCH));
			//this.sd.move(new  Vec2d(1,1), 0);
			System.out.println("moved");

		}
		counter++;

	}

}
