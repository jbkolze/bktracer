package bktracer;

import java.util.*;

/**
 * Created by brandon on 5/7/14.
 */

public class Scene {
    private List<Primitive> objectList = new ArrayList<Primitive>();

    public List<Primitive> getObjectList(){
        return objectList;
    }

    public void addObject(Primitive obj){
        objectList.add(obj);
    }
}
