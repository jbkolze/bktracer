package bktracer;

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
    private Vector3D viewTopLeft;

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
        System.out.println("pixelPoint(" + pixelRight + ", " + pixelDown + ") = (" + pixelPoint.getX() +
                            "," + pixelPoint.getY() + ", " + pixelPoint.getZ() + ")");
        return new Ray(cameraRay.getOrigin(), pixelPoint.subtract(cameraRay.getOrigin()));
    }

}
