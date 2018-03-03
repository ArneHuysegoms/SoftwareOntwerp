package figures.Drawer;

import canvascomponents.diagram.Diagram;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActorDrawer implements Drawer{
    private static ActorDrawer instance = null;

    private ActorDrawer(){

    }

    public static ActorDrawer getInstance(){
        if(instance == null)
            instance = new ActorDrawer();
        return instance;
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {

    }
}
