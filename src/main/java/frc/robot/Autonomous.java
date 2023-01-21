package frc.robot;
import com.kauailabs.navx.frc.AHRS;

import frc.robot.components.SwerveDrive;
import frc.robot.components.SwerveModule;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import edu.wpi.first.math.controller.PIDController;

public class Autonomous {
	private SwerveDrive sd;
	public Autonomous(SwerveDrive drivetrain) {
		this.sd = drivetrain;
	}

	static void testAuto(){
		
	}

}
