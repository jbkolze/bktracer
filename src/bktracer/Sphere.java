package bktracer;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by brandon on 5/6/14.
 */
public class Sphere extends Primitive {
    private Color color;
    private Vector3D center;
    private double radius;
    private double radiusSquared;

    public Sphere() {
        radius = 1;
        radiusSquared = 1;
        center = new Vector3D();
        color = new Color(255, 255, 255);
    }

    public Sphere(double radius, Vector3D center, Color color){
        this.radius = radius;
        this.radiusSquared = radius*radius;
        this.center = center;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public List<Intersection> intersect(Ray ray){
        List<Intersection> intersects = new ArrayList<Intersection>();
        Vector3D originToCenter = center.subtract(ray.getOrigin());
        double lengthOCSquared = originToCenter.magnitudeSquared();
        boolean insideSphere = false;
        if (lengthOCSquared < radiusSquared) {
            insideSphere = true;
        }

        double tClosest = originToCenter.dotProduct(ray.getDirection());
        if (tClosest < 0 && !insideSphere) return intersects;

        double tHalfChordSquared = radiusSquared - lengthOCSquared + (tClosest * tClosest);
        if (tHalfChordSquared < 0) return intersects;

        double t;
        if (insideSphere) {
            t = tClosest + Math.sqrt(tHalfChordSquared);
            intersects.add(new Intersection(t, ray.findT(t), this));
        } else {
            t = tClosest - Math.sqrt(tHalfChordSquared);
            intersects.add(new Intersection(t, ray.findT(t), this));
            double t2 = tClosest + Math.sqrt(tHalfChordSquared); // Check for ray tangent to sphere
            if (t2 > t) {intersects.add(new Intersection(t2, ray.findT(t2), this));}
        }

        return intersects;
    }

    public Vector3D normal(Vector3D point) {
        double x = (point.getX() - center.getX()) / radius;
        double y = (point.getY() - center.getY()) / radius;
        double z = (point.getZ() - center.getZ()) / radius;
        return new Vector3D(x, y, z).unitVector();
    }
}
