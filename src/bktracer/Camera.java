package bktracer;

/**
 * Created by brandon on 5/7/14.
 */
public class Camera {
    private Ray cameraRay; // Ray providing the origin and direction of the camera
    private Vector3D upVector; // Vector oriented to what is considered "up" (usually +y direction)
    private Vector3D widthVector; // Unit vector oriented to the "width" of the viewport (right)
    private Vector3D heightVector; // Unit vector oriented to the "height" of the viewport (up)
    private double focalLength; // Distance from camera to middle of viewport
    private double width; // Width of viewport in scene units
    private double height; // Height of viewport in scene units
    private int resWidth; // Width of viewport in pixels
    private int resHeight; // Height of viewport in pixels
    private Vector3D viewTopLeft;

    public Camera(){
    }

    public Camera(Ray cameraRay, double focalLength, double width, double height, int resWidth, int resHeight) {
        this.cameraRay = cameraRay;
        this.focalLength = focalLength;
        this.width = width;
        this.height = height;
        this.resWidth = resWidth;
        this.resHeight = resHeight;

        upVector = new Vector3D(0, 1.0, 0);
        widthVector = cameraRay.getDirection().crossProduct(upVector).unitVector();
        heightVector = widthVector.crossProduct(cameraRay.getDirection()).unitVector();
        viewTopLeft = cameraRay.findT(focalLength).add(heightVector.addScalar(height/2)).
                        subtract(widthVector.addScalar(width/2));
    }

    public void testCamera(){
        System.out.println("widthVector = " + widthVector);
        System.out.println("heightVector = " + heightVector);
        System.out.println("viewTopLeft = " + viewTopLeft);
    }


}
