package bktracer;

public class Main {

    public static void main(String[] args) {
        Ray camRay = new Ray(new Vector3D(0, 0, 5), new Vector3D(0, 0, -1));
	    Camera testCam = new Camera(camRay, 5, 640, 480, 640, 480);
        testCam.testCamera();
    }
}
