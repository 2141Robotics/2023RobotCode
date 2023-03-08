package frc.robot.math;

//* stolen from http://groups.csail.mit.edu/graphics/classes/6.837/F01/Project05/Vector3D.java */

// A simple vector class
public class Vector3D {
    public double x, y, z;

    // constructors
    public Vector3D( ) {
    }

    public Vector3D(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }

    public Vector3D(Vector3D v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    // methods

  // Vector from this to B
  public final Vector3D to(Vector3D B) {
    return(new Vector3D(B.x - x, B.y - y, B.z - z));
  }

    public final double dot(Vector3D B) {
        return (x*B.x + y*B.y + z*B.z);
    }

    public final double dot(double Bx, double By, double Bz) {
        return (x*Bx + y*By + z*Bz);
    }

    public static final double dot(Vector3D A, Vector3D B) {
        return (A.x*B.x + A.y*B.y + A.z*B.z);
    }

    public final Vector3D cross(Vector3D B) {
        return new Vector3D(y*B.z - z*B.y, z*B.x - x*B.z, x*B.y - y*B.x);
    }

    public final Vector3D cross(double Bx, double By, double Bz) {
        return new Vector3D(y*Bz - z*By, z*Bx - x*Bz, x*By - y*Bx);
    }

    public final static Vector3D cross(Vector3D A, Vector3D B) {
        return new Vector3D(A.y*B.z - A.z*B.y, A.z*B.x - A.x*B.z, A.x*B.y - A.y*B.x);
    }

    public final double length( ) {
        return (double) Math.sqrt(x*x + y*y + z*z);
    }

    public final static double length(Vector3D A) {
        return (double) Math.sqrt(A.x*A.x + A.y*A.y + A.z*A.z);
    }

    public final void normalize( ) {
        double t = x*x + y*y + z*z;
        if (t != 0 && t != 1) t = (double) (1 / Math.sqrt(t));
        x *= t;
        y *= t;
        z *= t;
    }

    public final static Vector3D normalize(Vector3D A) {
        double t = A.x*A.x + A.y*A.y + A.z*A.z;
        if (t != 0 && t != 1) t = (double)(1 / Math.sqrt(t));
        return new Vector3D(A.x*t, A.y*t, A.z*t);
    }

    public String toString() {
        return new String("["+x+", "+y+", "+z+"]");
    }
}
