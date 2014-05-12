package bktracer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon on 5/4/14.
 */
public class Ray {
    private Vector3D origin;
    private Vector3D direction; // Direction vector must be normalized
    private List<Intersection> intersectionList = new ArrayList<Intersection>();

    // Construct ray with existing vector objects -- origin and direction
    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction.unitVector();
    }

    public Vector3D getOrigin(){
        return origin;
    }

    public Vector3D getDirection(){
        return direction;
    }

    public List<Intersection> getIntersectionList() {
        return intersectionList;
    }

    public Vector3D findT(double t){
        double x = origin.getX() + direction.getX() * t;
        double y = origin.getY() + direction.getY() * t;
        double z = origin.getZ() + direction.getZ() * t;
        return new Vector3D(x, y, z);
    }

    public void findIntersections(Scene scene){
        intersectionList = new ArrayList<Intersection>();
        for (Primitive object : scene.getObjectList()) {
            intersectionList.addAll(object.intersect(this));
        }
    }

    public Intersection closestIntersect() {
        if (intersectionList.isEmpty()) { return null; }

        Intersection closest = null;

        for (Intersection intersect : intersectionList) {
            if (closest == null) { closest = intersect; }
            else if (intersect.getT() < closest.getT()) {closest = intersect;}
        }

        return closest;
    }

    @Override
    public String toString() {
        return "Origin: (" + origin.getX() + ", " + origin.getY() + ", " + origin.getZ() + "), " +
               "Direction: (" + direction.getX() + ", " + direction.getY() + ", " + direction.getZ() + ")";
    }
}
