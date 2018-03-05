package figures.Drawer;

import canvascomponents.diagram.Diagram;
import figures.diagramFigures.Box;

import java.awt.*;
import java.awt.geom.Point2D;

public class BoxDrawer implements Drawer{
    private static BoxDrawer instance = null;

    private BoxDrawer(){

    }

    public static BoxDrawer getInstance(){
        if(instance == null)
            instance = new BoxDrawer();
        return instance;
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        new Box(start, end).draw(graphics);
    }
}
