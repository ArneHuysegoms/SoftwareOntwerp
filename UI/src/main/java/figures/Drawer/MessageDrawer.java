package figures.Drawer;

import canvascomponents.diagram.Diagram;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class MessageDrawer implements Drawer{
    private static MessageDrawer instance = null;

    private MessageDrawer(){

    }

    public static MessageDrawer getInstance(){
        if(instance == null)
            instance = new MessageDrawer();
        return instance;
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        Arrow a = new Arrow(start, end);
        a.draw(graphics);
    }
}
