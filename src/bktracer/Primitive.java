package bktracer;

/**
 * Created by brandon on 5/5/14.
 */

public abstract class Primitive {
    public abstract Vector3D intersect(Ray ray);
    public abstract Ray normal(Vector3D point);
}
