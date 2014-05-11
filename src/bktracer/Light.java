package bktracer;

/**
 * Created by brandon on 5/9/2014.
 */
public class Light {
    private Vector3D point;
    private double intensity;

    public Light(Vector3D point, double intensity) {
        this.point = point;
        this.intensity = intensity;
    }

    public Vector3D getPoint() {
        return point;
    }

    public double getIntensity() {
        return intensity;
    }
}
