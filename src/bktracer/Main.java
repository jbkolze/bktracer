package bktracer;

public class Main {

    public static void main(String[] args) {
        Ray camRay = new Ray(new Vector3D(0, 0, 5), new Vector3D(0, 0, -1));
	    Camera testCam = new Camera(camRay, 5, 640, 480, 640, 480);
        Ray testRay = testCam.pixelRay(320, 240);

        Sphere testSphere = new Sphere(100, new Vector3D(0, 0, -200));
        System.out.println(testSphere.intersect(testRay));

    }
}
