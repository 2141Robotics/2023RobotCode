package frc.robot.components;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.math.Vector3D;
public class Limelight
{    /*
     FIELD
     1 2 3
     APRILTAG
     4 5 6
     7 8 9
    */
    public static final Vector3D offset1 = new Vector3D (17d, -24d, 46d);
    public static final Vector3D offset2 = new Vector3D (17d, 0d, 46d);
    public static final Vector3D offset3 = new Vector3D (17d, 24d, 46d);
    public static final Vector3D offset4 = new Vector3D (8.3d, -24d, 17d);
    public static final Vector3D offset5 = new Vector3D (8.3d, 0d, 2d);
    public static final Vector3D offset6 = new Vector3D (8.3d, 24d, 2d);
    public static final Vector3D offset7 = new Vector3D (-8d, -24d, -17.5d);
    public static final Vector3D offset8 = new Vector3D (-8d, 0d, -17.5d);
    public static final Vector3D offset9 = new Vector3D (-8d, 24d, -17.5d);

    Vector3D currentOffset = new Vector3D(0,0,0);

    public Limelight() {

   }
   /// sets the offset between the apriltag and the target in 3d
   public void setTarget(Vector3D vec){
      this.currentOffset = vec;
   }

   public double getNTdoubleAt(String name) {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry(name).getDouble(0);
   }

   public double[] getNT3dtAt(String name){
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry(name).getDoubleArray(new double[6]);

   }

   public Vector3D robotVectorToTarget() {
      double[] targetToRobot = this.getNT3dtAt("botpose_targetspace");
      Vector3D targetToRobotVec = new Vector3D(targetToRobot[0], targetToRobot[2], targetToRobot[3]);
      return this.currentOffset.to(targetToRobotVec);
   }

   
}
