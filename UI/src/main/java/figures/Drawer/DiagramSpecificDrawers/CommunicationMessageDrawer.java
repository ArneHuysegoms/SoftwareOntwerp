package figures.Drawer.DiagramSpecificDrawers;

import figures.Drawer.MessageDrawer;
import figures.diagramFigures.Arrow;

import java.awt.*;
import java.awt.geom.Point2D;

public class CommunicationMessageDrawer extends MessageDrawer {
    private static CommunicationMessageDrawer instance = null;

    private CommunicationMessageDrawer(){

    }

    public static CommunicationMessageDrawer getInstance(){
        if(instance == null)
            instance = new CommunicationMessageDrawer();
        return instance;
    }

    @Override
    public void draw(Graphics graphics, Point2D start, Point2D end, String label) {
        //TODO
    }
}
