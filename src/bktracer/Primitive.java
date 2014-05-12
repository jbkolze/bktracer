package bktracer;

import java.awt.*;
import java.util.List;

/**
 * Created by brandon on 5/5/14.
 */

public abstract class Primitive {
    public abstract Color getColor();
    public abstract List<Intersection> intersect(Ray ray);
    public abstract Vector3D normal(Vector3D point);
}
