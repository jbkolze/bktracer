package bktracer;

import java.awt.*;

/**
 * Created by brandon on 5/5/14.
 */

public abstract class Primitive {
    public abstract Color getColor();
    public abstract Vector3D intersect(Ray ray);
    public abstract Vector3D normal(Vector3D point);
}
