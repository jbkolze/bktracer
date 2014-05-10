package bktracer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by brandon on 5/7/14.
 */
public class Camera {
    private Ray cameraRay; // Ray providing the origin and direction of the camera
    private Vector3D upVec; // Vector oriented to what is considered "up" (usually +y direction)
    private Vector3D widthVec; // Unit vector oriented to the "width" of the viewport (right)
    private Vector3D heightVec; // Unit vector oriented to the "height" of the viewport (up)
    private double focalLength; // Distance from camera to middle of viewport
    private double width; // Width of viewport in scene units
    private double height; // Height of viewport in scene units
    private int resWidth; // Width of viewport in pixels
    private int resHeight; // Height of viewport in pixels
    private double pixelWidth; // Width of pixel in scene units
    private double pixelHeight; // Height of pixel in scene units
    private Vector3D viewTopLeft; // Point at the top-left corner of the viewport

    public Camera(Ray cameraRay, double focalLength, double width, double height, int resWidth, int resHeight) {
        this.cameraRay = cameraRay;
        this.focalLength = focalLength;
        this.width = width;
        this.height = height;
        this.resWidth = resWidth;
        this.resHeight = resHeight;

        upVec = new Vector3D(0, 1.0, 0);
        widthVec = cameraRay.getDirection().crossProduct(upVec).unitVector();
        heightVec = widthVec.crossProduct(cameraRay.getDirection()).unitVector();
        viewTopLeft = cameraRay.findT(focalLength).add(heightVec.addScalar(height / 2)).
                        subtract(widthVec.addScalar(width / 2));

        pixelWidth = width / resWidth;
        pixelHeight = height / resHeight;
    }

    public Ray pixelRay(int pixelRight, int pixelDown) {
        Vector3D pixelPoint = viewTopLeft.add(widthVec.addScalar(pixelRight * pixelWidth + pixelWidth / 2))
                                .subtract(heightVec.addScalar(pixelDown * pixelHeight + pixelHeight / 2));
        return new Ray(cameraRay.getOrigin(), pixelPoint.subtract(cameraRay.getOrigin()));
    }

    public void saveImage(Scene scene, String filename) {
        BufferedImage image = new BufferedImage(resWidth, resHeight, BufferedImage.TYPE_INT_RGB);

        for (int down = 0; down < resHeight; down++) {
            for (int right = 0; right < resWidth; right++) {
                Color pixelColor = traceRay(pixelRay(right, down), scene);
                image.setRGB(right, down, pixelColor.getRGB());
            }
        }

        File outFile = new File(filename);
        try {
            ImageIO.write(image, "png", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Color traceRay(Ray ray, Scene scene){
        Primitive closeObject = new Sphere();
        Vector3D closePoint = new Vector3D();

        for (Primitive object : scene.getObjectList()) {
            Vector3D iSect = object.intersect(ray);

            if (!(iSect.getX() == 0 && iSect.getY() == 0 && iSect.getZ() == 0)) {
                if (closePoint.getX() == 0 && closePoint.getY() == 0 && closePoint.getZ() == 0) {
                    closePoint = iSect;
                    closeObject = object;
                } else if (iSect.subtract(ray.getOrigin()).magnitudeSquared() <
                            closePoint.subtract(ray.getOrigin()).magnitudeSquared()) {
                    closePoint = iSect;
                    closeObject = object;
                }
            }
        }

        if (closePoint.getX() == 0 && closePoint.getY() == 0 && closePoint.getZ() == 0) {
            return scene.getBGColor();
        }

        System.out.println("hit object");
        double sumIntensity = 0;

        for (Light light : scene.getLightList()) {
            Vector3D lightVec = light.getPoint().subtract(closePoint).unitVector();
            sumIntensity += light.getIntensity() * lightVec.dotProduct(closeObject.normal(closePoint));
        }

        if(sumIntensity > 1) {sumIntensity = 1;}
        if(sumIntensity < 0) {sumIntensity = 0;}

        int red = (int)(closeObject.getColor().getRed() * sumIntensity);
        int blue = (int)(closeObject.getColor().getGreen() * sumIntensity);
        int green = (int)(closeObject.getColor().getBlue() * sumIntensity);

        return new Color(red, blue, green);
    }
}
