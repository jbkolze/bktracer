package bktracer;

import java.awt.*;
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

    public Color traceRay(Scene scene){

        findIntersections(scene);
        Intersection closest = closestIntersect();

        if (closest == null) { return scene.getBGColor(); }

        double sumIntensity = calcShadows(closest, scene);

        if(sumIntensity > 1) {sumIntensity = 1;}
        if(sumIntensity < 0) {sumIntensity = 0;}

        Color objColor = closest.getObject().getColor();
        int red = (int)(objColor.getRed() * sumIntensity);
        int blue = (int)(objColor.getGreen() * sumIntensity);
        int green = (int)(objColor.getBlue() * sumIntensity);

        return new Color(red, blue, green);
    }

    public double calcShadows(Intersection curIntersect, Scene scene) {
        double sumIntensity = 0;

        for (Light light : scene.getLightList()) {
            Ray toLight = new Ray(curIntersect.getPoint(), light.getPoint().subtract(curIntersect.getPoint()).unitVector());
            toLight.findIntersections(scene);
            Intersection closest = toLight.closestIntersect();

            double lightDist = light.getPoint().subtract(toLight.getOrigin()).magnitude();
            if ((closest != null && lightDist < closest.getT()) || closest == null) {
                sumIntensity += light.getIntensity() *
                        toLight.getDirection().dotProduct(curIntersect.getObject().normal(curIntersect.getPoint()));
            }
        }

        return sumIntensity;
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
