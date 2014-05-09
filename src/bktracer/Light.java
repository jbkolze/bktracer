package bktracer;

/**
 * Created by brandon on 5/9/2014.
 */
public class Light {
    private Vector3D point;
    private float intensity;

    public Light(Vector3D point, float intensity) {
        this.point = point;
        this.intensity = intensity;
    }

    public Vector3D getPoint() {
        return point;
    }

    public float getIntensity() {
        return intensity;
    }
}
