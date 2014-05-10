package bktracer;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Ray camRay = new Ray(new Vector3D(0, 0, 50), new Vector3D(0, 0, -1));
	    Camera testCam = new Camera(camRay, 5, 640, 480, 640, 480);

        Scene testScene = new Scene(new Color(200, 200, 200));
        Sphere testSphere = new Sphere(400, new Vector3D(0, 0, -200), new Color(150, 0, 150));
        testScene.addObject(testSphere);
        testScene.addLight(new Light(new Vector3D(500, 500, -100), 1));

        testCam.saveImage(testScene, "test.png");
    }
}
