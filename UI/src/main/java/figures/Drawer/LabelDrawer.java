package figures.Drawer;

import canvascomponents.diagram.Diagram;

import java.awt.*;
import java.awt.geom.Point2D;

public class LabelDrawer implements Drawer{
    private static LabelDrawer instance = null;

    private LabelDrawer(){

    }

    public static LabelDrawer getInstance(){
        if(instance == null)
            instance = new LabelDrawer();
        return instance;
    }


    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {

    }
}
