package bktracer;

/**
 * Created by brandon on 5/4/14.
 */
public class Ray {
    private Vector3D origin;
    private Vector3D direction;

    // Construct ray with new vector objects set to (0, 0, 0)
    public Ray() {
        origin = new Vector3D();
        direction = new Vector3D();
    }

    // Construct ray with existing vector objects -- origin and direction
    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    // Construct ray with coordinates -- xyz of origin and xyz of direction
    public Ray(double x1, double y1, double z1, double x2, double y2, double z2) {
        origin = new Vector3D(x1, y1, z1);
        direction = new Vector3D(x2, y2, z2);
    }

    @Override
    public String toString() {
        return "Origin: (" + origin.getX() + ", " + origin.getY() + ", " + origin.getZ() + "), " +
               "Direction: (" + direction.getX() + ", " + direction.getY() + ", " + direction.getZ() + ")";
    }
}
