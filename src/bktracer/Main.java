package bktracer;

public class Main {

    public static void main(String[] args) {
	    Vector3D v1 = new Vector3D(3, 5, 7);
        Vector3D v2 = new Vector3D(1, 1, 1);
        System.out.println("Vector v1 = " + v1);
        System.out.println("Vector v2 = " + v2);
        System.out.println("Vectors added = " + v1.add(v2));
    }
}
