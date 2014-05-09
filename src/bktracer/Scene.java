package bktracer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brandon on 5/7/14.
 */

public class Scene {
    private List<Primitive> objectList = new ArrayList<Primitive>();
    private List<Light> lightList = new ArrayList<Light>();
    private Color bgColor;

    public Scene() {
        bgColor = new Color(0, 0, 0);
    }

    public Scene(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Color getBGColor() {
        return bgColor;
    }

    public List<Primitive> getObjectList(){
        return objectList;
    }
    public List<Light> getLightList() {
        return lightList;
    }

    public void addObject(Primitive obj){
        objectList.add(obj);
    }
    public void addLight(Light light) {
        lightList.add(light);
    }
}
