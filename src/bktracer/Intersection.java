package bktracer;

/**
 * Created by brandon on 5/11/14.
 */

public class Intersection {
    private double t; // Distance from ray origin to intersection point
    private Vector3D point;
    private Primitive object; // Object that the ray intersects

    public Intersection(double t, Vector3D point, Primitive object) {
        this.t = t;
        this.point = point;
        this.object = object;
    }

    public double getT() {
        return t;
    }

    public Vector3D getPoint() {
        return point;
    }

    public Primitive getObject() {
        return object;
    }
}
