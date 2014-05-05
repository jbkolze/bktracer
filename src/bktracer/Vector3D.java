package bktracer;

/**
 * Created by brandon on 5/3/14.
 */

public class Vector3D {
    private double x, y, z;

    public Vector3D() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector3D add(Vector3D addVec) {
        return new Vector3D(x + addVec.x, y + addVec.y, z + addVec.z);
    }

    public Vector3D subtract(Vector3D subVec) {
        return new Vector3D(x - subVec.x, y - subVec.y, z - subVec.z);
    }

    public Vector3D dotProduct(Vector3D dotProd) {
        return new Vector3D(x * dotProd.x, y * dotProd.y, z * dotProd.z);
    }

    public Vector3D crossProduct(Vector3D crossProd) {
        return new Vector3D(y * crossProd.z - z * crossProd.y,
                            z * crossProd.x - x * crossProd.z,
                            x * crossProd.y - y * crossProd.x);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
